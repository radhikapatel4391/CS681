package hw19;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
	private LinkedList<Observer> observers;
	private boolean change = false;
	private ReentrantLock lockObs = new ReentrantLock();
	
	public Observable() {
		
		observers = new LinkedList<Observer>();
	}
	
	public void addObservers(Observer o) {
		lockObs.lock();
		try {
			if (o == null)
				throw new NullPointerException("can't add null observer");
			observers.add(o);
		}finally {
			lockObs.unlock();
		}
		
	}
	public void deleteObserver(Observer o) {
		lockObs.lock();
		try {
			observers.remove(o);
		}finally {
			lockObs.unlock();
		}
		
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
		lockObs.lock();
		try {
			 return observers.size();
		}finally {
			lockObs.unlock();
		}
	    
	 }
	public  void deleteObservers(){
		lockObs.lock();
		try {
			 observers.clear();
		}finally {
			lockObs.unlock();
		}
	  
	 }
	public void notifyObservers(Object arg) {
		Object[] arrlocal = null;
		lockObs.lock();
		try {
			System.out.println(Thread.currentThread().getId() +"      observers.size:      "+observers.size());
			if(!hasChanged()) {
				System.out.println(Thread.currentThread().getId()+ "no change");
				return;	
			}
						
			arrlocal = observers.toArray();	
			clearChanged();
		}finally {
			lockObs.unlock();
		}
		
		for (int counter = 0; counter < arrlocal.length; counter++)
		{  
			
			
	          ((Observer)arrlocal[counter]).update(this, arg);
	     }  
		
		
	}
}
