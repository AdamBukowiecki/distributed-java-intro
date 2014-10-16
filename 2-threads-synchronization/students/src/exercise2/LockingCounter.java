package exercise2;

import java.util.concurrent.locks.ReentrantLock;

import common.Counter;

public class LockingCounter implements Counter {

	private long value = 0;
	ReentrantLock lock = new ReentrantLock();
	
    public void increment() {
    	lock.lock();
    	try {
    		value++;
    	} finally {
    		lock.unlock();
    	}
    }

    public long getValue() {
        return value;
    }
}
