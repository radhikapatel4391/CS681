package hw1;

import java.util.LinkedList;

public abstract class Observable {
	private LinkedList<Observer> observers;
	private boolean change = false;
	
	
	public Observable() {
		
		observers = new LinkedList<Observer>();
	}
	
	public void addObservers(Observer o) {
		if (o == null)
			throw new NullPointerException("can't add null observer");
		observers.add(o);
	}
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	
	protected void setChanged() {
		change = true;
	}
	public boolean hasChanged() {
		return change;
	}
	protected void clearChanged() {
		change = false;
	}
	public void notifyObservers() {
		notifyObservers(null);
	}
	public  int countObservers(){
	     return observers.size();
	 }
	public  void deleteObservers(){
	   observers.clear();
	 }
	public void notifyObservers(Object arg) {
		System.out.println(Thread.currentThread().getId() +"      observers.size:      "+observers.size());
		if(!hasChanged()) {
			System.out.println(Thread.currentThread().getId()+ "no change");
			return;	
		}
			for (int counter = 0; counter < observers.size(); counter++)
			{ 		      
				
				System.out.println("\n");
		          observers.get(counter).update(this, arg);
		     }  
		
		clearChanged();
	}
}
