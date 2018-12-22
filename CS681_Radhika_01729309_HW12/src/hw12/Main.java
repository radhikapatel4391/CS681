package hw12;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunnableCancellableInterruptiblePrimeGenerator gen = new RunnableCancellableInterruptiblePrimeGenerator(1, 1000);
		Thread aThread = new Thread(gen);
		aThread.start();
		try {
			System.out.println("Main sleep (100) before making intrupt other thread so that thread can reach to sleep point..");
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Main going to make it intrupt..");
		aThread.interrupt();
		System.out.println("going to set done..");
		gen.setDone();
		
		try {
			aThread.join();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Main end stamement....");
		
	}

}
