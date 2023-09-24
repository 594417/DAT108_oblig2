package oppg1;

import javax.swing.JOptionPane;

public class Oppg1 {
	private static String message = "Hallo verden!";
	private static boolean running = true;

	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread() {
			public void run() {
				while (running) {
					if (message == null) {
						running = false;
						System.out.println("Unexpected message: Cannot be null");
					}

					System.out.println(message);

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						running = false;
						System.out.println(e.getStackTrace());
					}
				}
			}
		};

		Thread t2 = new Thread() {
			public void run() {

				while(running) {
					message = JOptionPane.showInputDialog("Skriv inn tekst: ");
					if(message.equals("quit")) {
						running = false;
					}
				}
			}

		};
		t1.start();
		t2.start();
	}

}