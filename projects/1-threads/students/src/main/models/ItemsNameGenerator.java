package main.models;

import java.util.concurrent.locks.ReentrantLock;

public class ItemsNameGenerator {

	private static final String[] colors = {"Black", "White", "Green", "Red", "Blue", "Yellow", "Gold"};
	private static final String[] sizes = {"Tiny", "Small", "Medium", "Large", "Big", "Monstrual", "Giant"};
	private static final String[] kinds = {"Chair", "Table", "Bed", "Car", "Picture", "Ship", "Dog", "Bike", "Kettle",
											"Laptop", "Shoes", "Glass"};
	
	private static ReentrantLock locker = new ReentrantLock();;
	private static int colorIt = 0;
	private static int sizeIt = 0;
	private static int kindIt = 0;
	private static boolean endNames = false;
	
	public static String generateName() {
		locker.lock();
		if(endNames) {
			locker.unlock();
			return "Names limit reached!";
		}
		String name = colors[colorIt] + " " + sizes[sizeIt] + " " + kinds[kindIt];
		if(kinds.length <= kindIt + 1) {
			kindIt = 0;
			if(sizes.length <= sizeIt + 1) {
				sizeIt = 0;
				if(colors.length <= colorIt + 1) {
					System.out.println("There is no free names!!");
					endNames = true;
				} else {
					colorIt++;
				}
			} else {
				sizeIt++;
			}
		} else {
			kindIt++;
		}
		locker.unlock();
		return name;
		
	}
	
	public static void reset() {
		endNames = false;
		colorIt = 0;
		sizeIt = 0;
		kindIt = 0;
	}
	
	
}
