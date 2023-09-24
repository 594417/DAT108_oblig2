package oppg2;

public class Servitor extends Thread {
	private HamburgerBrett brett;
	private String navn;

	public Servitor(HamburgerBrett brett, String navn) {
		this.brett = brett;
		this.navn = navn;
	}

	@Override
	public void run() {
		while(true) {
			if (brett.hamburgere.isEmpty()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				synchronized (brett) {
					brett.taAv(this.navn);
				}
			}
		}
	}
}
