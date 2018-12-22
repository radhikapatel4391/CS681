package hw20;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	static AccessCounter instance = null;
	static ReentrantLock lock = new ReentrantLock();
	ConcurrentHashMap<Path, AtomicInteger> ac = new ConcurrentHashMap<Path, AtomicInteger>();
	private AccessCounter() {

	}
	public static AccessCounter getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				System.out.println("I read null............");
				instance = new AccessCounter();
			}
		} finally {
			lock.unlock();
		}
		return instance;
	}

	public void increment(Path path) {
		
		ac.putIfAbsent(path, new AtomicInteger(0));
		ac.get(path).incrementAndGet();		
		
	}

	AtomicInteger getCount(Path path) {		
			return ac.get(path);
	}

}
