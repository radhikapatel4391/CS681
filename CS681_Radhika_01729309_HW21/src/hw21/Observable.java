package hw21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	private ConcurrentLinkedQueue<Observer> observers;
	private boolean change = false;
	private ReentrantLock lockObs = new ReentrantLock();
	
	public Observable() {
		
		observers = new ConcurrentLinkedQueue<Observer>();
	}
	
	public void addObservers(Observer o) {
		
			if (o == null) //o is local variable..
				throw new NullPointerException("can't add null observer");
			observers.add(o);
			//observers.offer(o);
		
		
	}
	public void deleteObserver(Observer o) {
		
			observers.remove(o);
		
		
	}
	
	protected void setChanged() {
		lockObs.lock();
		try {
			change = true;
		}finally {
			lockObs.unlock();
		}
		
	}
	public boolean hasChanged() {
		lockObs.lock();
		try {
			return change;
		}finally {
			lockObs.unlock();
		}
		
	}
	protected void clearChanged() {
		lockObs.lock();
		try {
			change = false;
		}finally {
			lockObs.unlock();
		}
		
	}
	public void notifyObservers() {
		notifyObservers(null);
	}
	public  int countObservers(){
		
			 return observers. size(); //long mappingCount() for hash map if size is larger then int....
		
	    
	 }
	public  void deleteObservers(){
		
			 observers.clear();
		
	  
	 }
	public void notifyObservers(Object arg) {
		
		lockObs.lock();
		try {			
			if(!hasChanged()) {
				System.out.println(Thread.currentThread().getId()+ "no change");
				return;	
			}
		}finally {
			lockObs.unlock();
		}
		/*Iterator<Observer> it = observers.iterator(); //it.forEachRemaining( (Observer k)->{k.update(this, arg);} );
		while(it.hasNext()) {
			it.next().update(this, arg);
		}*/
		
		observers.forEach((Observer k)
				->{k.update(this, arg);} );		
		clearChanged();	
		
	}
}
