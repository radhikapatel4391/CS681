package hw14;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
	private Address address; // Shared variable
	ReentrantLock lock = new ReentrantLock();
	public Customer(Address addr){ 
		this.address = addr; 
	}

	public Address getAddress() {
				//unsafe code....
				/*System.out.println("latest "+address.toString()+"             by "+Thread.currentThread().getName());
				return address;*/
				//safe code
		lock.lock();
		try {
			System.out.println("latest "+address.toString()+"             by "+Thread.currentThread().getName());
			return address;
		}finally {
			lock.unlock();
		}
	}


	public void setAddress(Address address) {
				//unsafe code.........
				//System.out.println("Customer Old address "+this.address.toString()+" by "+Thread.currentThread().getName());
				/*this.address = address;
				System.out.println("new "+this.address.toString()+" by "+Thread.currentThread().getName());
				*///safe code..
		lock.lock();
		try {			
			//System.out.println("Customer Old address "+this.address.toString()+" by "+Thread.currentThread().getName());
			this.address = address;
			System.out.println("new "+address.toString()+"             by "+Thread.currentThread().getName());
		}finally {
			lock.unlock();
		}
	}


	
}
	
