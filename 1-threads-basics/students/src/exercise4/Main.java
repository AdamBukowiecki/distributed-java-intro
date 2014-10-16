package exercise4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

    	ExecutorService executor = Executors.newFixedThreadPool(4);
    	List<MyRunnable> list = new ArrayList<MyRunnable>();
    	list.add(new MyRunnable("Run_1"));
    	list.add(new MyRunnable("Run_2"));
    	list.add(new MyRunnable("Run_3"));
    	list.add(new MyRunnable("Run_4"));
    	for(MyRunnable run: list)
    		executor.execute(run);
    	
    	
    }
}
