package hw8;

public class RunnableCancellablePrimeGenerator	extends CancellablePrimeGenerator
												implements Runnable {
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100000000);
		Thread thread = new Thread(gen);
		thread.start();
		try {
			System.out.println("----------------Main--------------------------");
			System.out.println("Main thred sleeping for 10ms then will do setDone");
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------Main---------------------------");
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
