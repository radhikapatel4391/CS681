package hw1;

import java.util.HashSet;
import java.util.Set;

public class DJIAQuoteObservable extends Observable {
	
	private float quote;
	private Set<Float> data = new HashSet<Float>();
	
	public Set<Float> getData() {
		return data;
	}

	public void changeQuote(float q)
	{
		this.quote = q;
		data.add(q);
		setChanged();
		notifyObservers(new DJIAEvent(q));		
				
	}

}
