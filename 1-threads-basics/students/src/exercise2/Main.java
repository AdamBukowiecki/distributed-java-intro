package exercise2;

public class Main {

    public static void main(String[] args) {
    	
    	Thread[] array = new MyThread[4];
    	int i = 0;
    	for(Thread thread: array) {
    		thread = new MyThread("new" + (i++));
    		thread.start();
    	}
    
    }
}
