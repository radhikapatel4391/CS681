package hw10;

public class Singleton {
	private Singleton() {
	};

	private static Singleton instance = null;

// Factory method to return the singleton instance
	public static Singleton getInstance() {
		System.out.println("locked");
		if (instance == null)
		{	
			System.out.println("I read null ");
			new Singleton();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			instance = new Singleton();
			System.out.println("instance: "+instance);
		}
		
		System.out.println("unlocked");
		return instance;
	}
}