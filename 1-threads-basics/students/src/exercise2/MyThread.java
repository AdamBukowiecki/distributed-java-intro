package exercise2;

public class MyThread extends Thread {
	
	private String name;
	
	public MyThread(String name) {
		super(name);
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.print(name + " ");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}			
		System.out.println();
	}
}
