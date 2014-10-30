package exercise7.equipment;

public class Brushes {

    private int available = 3;

    public synchronized void takeBrush() throws InterruptedException {
        if (available == 0) {
            throw new IllegalStateException("There are no more brushes!");
        }
        available -= 1;
        if (available > 0)
        	notifyAll();
    }

    public synchronized void returnBrush() {
        available += 1;
        notifyAll();
    }
}
