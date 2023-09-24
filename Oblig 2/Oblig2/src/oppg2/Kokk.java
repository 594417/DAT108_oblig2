package oppg2;

public class Kokk extends Thread {
	public String navn;
	private final HamburgerBrett brett;

	public Kokk(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.navn = navn;
	}

	@Override
	public void run() {
		while(true) {
			if (brett.isFull()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep((long)(Math.random() * 6000 - 2000 + 1) + 2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				synchronized (brett) {
					brett.leggPaa(this.navn);
				}
			}
	}
}
}