package hw9;

public class Main {

	public static void main(String[] args) {
		System.out.println("Running Main..........");
		File file = new File();
		Editor editor = new Editor(file);
		AutoSaver autoSave = new AutoSaver(file); 
		Thread threadEditor = new Thread(editor);
		Thread threadAutoSaver = new Thread(autoSave);
		threadEditor.start();
		threadAutoSaver.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main After 2s Sleep");
		editor.setDone();
		autoSave.setDone();// explicit thread termination..
	}

}
