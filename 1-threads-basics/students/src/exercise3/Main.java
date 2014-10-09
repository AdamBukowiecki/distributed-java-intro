package exercise3;

public class Main {

	
    public static void main(String[] args) {

    	Thread[] array = new Thread[4];
    	for(int i = 0; i < array.length; i++)
    		array[i] = new Thread(new MyRunnable("runnable"+i), "new"+i);
    	
    	for(Thread thread: array) {
    		
    		thread.start();
    	
    	}
    	for(Thread thread: array) {
    		try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	/*
    	boolean still = true;
    	while(still) {
    		still = false;
    		for(Thread thread: array)
    			if(thread.isAlive())
    				still = true;
    	}
    	*/
    	System.out.println("FINISHED");
    	
    	
    }
}
