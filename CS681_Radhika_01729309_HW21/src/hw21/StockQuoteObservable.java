package hw21;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
	private ReentrantLock lockst = new ReentrantLock();
	
	
	private HashMap<String, Float> map;
	
	public HashMap<String, Float> getMap() {
		
		lockst.lock();
		try {
			return map;
		}finally {
			lockst.unlock();
		}
		
	}

	public StockQuoteObservable() {
		map = new HashMap<>();
	}
	
	public void changeQuote(String t,float q) {
		lockst.lock();
		try {
			this.map.put(t,q);
			setChanged();
			notifyObservers(new StockEvent(t,q));
		}finally {
			lockst.unlock();
		}
		
		
	}

}
