package exercise2.equipment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Paints {

	BlockingQueue<String> array = new ArrayBlockingQueue<>(100);
	
	public Paints() {
		for(int i = 0; i < 12; i++) {
			try {
				array.put("Paint nr " + (i + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
    public String takePaint() throws InterruptedException {
        return array.take();
    }

    public void returnPaint(String paint) {
    	try {
			array.put(paint);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
