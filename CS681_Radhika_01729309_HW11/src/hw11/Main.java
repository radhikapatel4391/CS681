package hw11;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1,1000);
		Thread aThread = new Thread(gen);
			System.out.println("----------Main thread started...");
		aThread.start();
		try {
			System.out.println("----------Main sleep 1 milisec before changing intrupt...");
			Thread.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			System.out.println("----------MAIN going to ask for lock");
		gen.getLock().lock();
			System.out.println("----------MAIN locked");
			System.out.println("-------------Note: genprime execution between main method thread get lock object and called lock() method looks one line execution in code but... ");
		aThread.interrupt();
			System.out.println("----------MAIN going to unlock..");
		gen.getLock().unlock();
			System.out.println("----------MAIN released lock...");
		try {
			aThread.join();
		}catch(Exception e){
			System.out.println(e);
		}
			System.out.println("----------MAIN end statement...");
	}

}
