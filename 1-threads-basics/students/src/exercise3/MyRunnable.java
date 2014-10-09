package exercise3;

public class MyRunnable implements Runnable {

	private String name;
	
	public MyRunnable(String name) {
		this.name = name;
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.print(name);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
