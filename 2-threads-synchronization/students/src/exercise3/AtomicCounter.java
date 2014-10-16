package exercise3;

import java.util.concurrent.atomic.AtomicLong;

import common.Counter;

public class AtomicCounter implements Counter {

	AtomicLong atomic = new AtomicLong();
	
    public void increment() {
    	atomic.incrementAndGet();
    }

    public long getValue() {
        return atomic.longValue();
    }
}
