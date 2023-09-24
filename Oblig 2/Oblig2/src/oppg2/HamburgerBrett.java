package oppg2;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HamburgerBrett {

	private int kapasitet;
	public Queue<Hamburger> hamburgere;
	private int antall;

	public HamburgerBrett(int kapasitet) {
		this.kapasitet = kapasitet;
		this.hamburgere = new LinkedList<>();
		this.antall = 1;
	}

	public boolean isFull() {
		return hamburgere.size() > kapasitet;
	}

	public synchronized void leggPaa(String kokk) {
		Hamburger brg = new Hamburger(antall++, kokk);

		if(hamburgere.size() < kapasitet) {
			this.hamburgere.add(brg);
			System.out.println(brg.lagetAv + " (kokk) legger på hamburger (" + brg.getNummer() + "). Brett: " + prettyPrint());
			notifyAll();
		} else if(antall == kapasitet){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Interrupt exception in HamburgerBrett");
			}
		}

		notifyAll();
	}

	public synchronized void taAv(String servitor) {

		if(!hamburgere.isEmpty()) {
			Hamburger brg = hamburgere.poll();
			System.out.println(servitor + " (servitør) tar av hamburger (" + brg.getNummer() + "). Brett: " + prettyPrint());
			notifyAll();
		} else {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("Interrupt exception in HamburgerBrett");
			}
		}
	}

	public String prettyPrint() {
		String line = "[";

		int index = 0;

		for (Hamburger brg : hamburgere) {
			if (index == 0) {
				line += brg.getNummer();
			} else {
				line += ", " + brg.getNummer();
			}

			index++;
		}

		line += "]";

		return line;
	}
}
