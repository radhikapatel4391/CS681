package hw19;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class DJIAQuoteObservable extends Observable {
	
	private float quote;
	private Set<Float> data = new HashSet<Float>();
	private ReentrantLock lockDJ = new ReentrantLock();
	public Set<Float> getData() {
		
		lockDJ.lock();
		try {
			return data;
		}finally {
			lockDJ.unlock();
		}
	}

	public void changeQuote(float q)
	{
		lockDJ.lock();
		try {
				
			this.quote = q;
			data.add(q);
			setChanged();
			notifyObservers(new DJIAEvent(q));	
		}finally {
			lockDJ.unlock();
		}
		
		
	}

}
