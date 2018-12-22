import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Cart {
	private ArrayList<Product> inCartItems;
	private float subtotal;
	private ReentrantLock lockI;
	private ReentrantLock lockST;
	private Cart cart;
	private volatile boolean done = false;

	public Cart() {
		cart = this;
		lockI = new ReentrantLock();
		lockST = new ReentrantLock();
		inCartItems = new ArrayList<Product>();
		subtotal = 0;
	}

	public void setDone() {
		done = true; // thread safe because done is volatile
	}

	public ArrayList<Product> getItems() {
		lockI.lock();
		try {
			// System.out.println(Thread.currentThread().getId() + " lockI obtained");
			return inCartItems;
		} finally {
			lockI.unlock();
			// System.out.println(Thread.currentThread().getId() + "LockI released");
		}

	}

	public float getSubTotal() {
		lockST.lock();
		try {
			// System.out.println(Thread.currentThread().getId() + " lockST obtained");
			return subtotal;
		} finally {
			lockST.unlock();
			// System.out.println(Thread.currentThread().getId() + "LockST released");
		}
	}

	public void addItem(Product item) {
		float temp = item.getPrice(); // Open Call..............
		lockI.lock();
		try {
			// System.out.println(Thread.currentThread().getId() + " lockI obtained");
			lockST.lock();
			try {

				inCartItems.add(item); // WRITE
				subtotal += temp;// WRITE
				System.out.println(Thread.currentThread().getId() + "   lockST obtained added price: " + temp);

			} catch (Exception exception) {
				exception.printStackTrace();
			} finally {
				lockST.unlock();
				// System.out.println(Thread.currentThread().getId() + "Lock released");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			lockI.unlock();
			// System.out.println(Thread.currentThread().getId() + "Lock released");
		}
	}

	public void removeItem(int productIndex) {
		Product p;
		lockI.lock();
		try {
			if(inCartItems.size()>productIndex) {
				p = inCartItems.get(productIndex);
			}else {
				return;
			}
				
			

		} finally {
			lockI.unlock();
		}

		float temp = p.getPrice(); // Open Call..............

		lockI.lock();
		try {
			// System.out.println(Thread.currentThread().getId() + " lockI obtained");
			lockST.lock();
			try {

				// inCartItems.remove(productIndex);// WRITE
				
				if (inCartItems.remove(p)) {
					subtotal -= temp;
					System.out.println(Thread.currentThread().getId() + "   lockST obtained removed price: " + temp);
				}else {
					System.out.println("..................Item not exist.........................................................");
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			} finally {
				lockST.unlock();
				// System.out.println(Thread.currentThread().getId() + "Lock released");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			lockI.unlock();
			// System.out.println(Thread.currentThread().getId() + "Lock released");
		}

	}

	private float makeTotal() { // just for testing....never call out side..
		float total = 0;
		for (Product item : inCartItems) {
			total += item.getPrice();
		}
		return total;
	}

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<Thread>(6);
		Cart c1 = new Cart();

		for (int i = 0; i < 3; i++) {
			Thread t1 = new Thread(c1.new AddItemRunnable());
			threads.add(t1);
			t1.start();			
			Thread t2 = new Thread(c1.new RemoveItemRunnable());
			threads.add(t2);
			t2.start();
			
		}

		try {
			Thread.sleep(5);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		c1.setDone();
		for (int i = 0; i < 6; i++) {
			try {
				threads.get(i).join();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println(Thread.currentThread().getId() + "  Subtotal is: " + c1.getSubTotal());
		System.out.println("Total of item price is: "+c1.makeTotal()); //testing purpose.....

	}

	public class AddItemRunnable implements Runnable {
		public void run() {
			while (!done) {
				float t = (float) (Math.random() * 100);
				Product p = new Product(t);// thread safe because done is volatile
				cart.addItem(p);// two thread might concurently execute this statement but it as expected..
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getId() + " done = true");

		}
	}

	public class RemoveItemRunnable implements Runnable {
		public void run() {
			while (!done) { // thread safe because done is volatile
				if (!(cart.getItems().isEmpty()))// not thread safe...................
					cart.removeItem(0);  // not thread safe.............
				
			}
			System.out.println(Thread.currentThread().getId() + " done = true");

		}
	}
}
