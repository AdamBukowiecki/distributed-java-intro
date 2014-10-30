package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Brushes {

	BlockingQueue<String> array = new ArrayBlockingQueue<>(100);
	
	public Brushes() {
		for(int i = 0; i < 12; i++) {
			try {
				array.put("Brush nr " + (i + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
    public String takeBrush() throws InterruptedException {
        return array.take();
    }

    public void returnBrush(String brush) {
    	try {
			array.put(brush);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
