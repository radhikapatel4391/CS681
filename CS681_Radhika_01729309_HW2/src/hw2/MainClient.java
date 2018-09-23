package hw2;

//client
public class MainClient {

	public static void main(String[] args) {
		 RemoteControl control = new RemoteControl();
		    Light light = new Light();
		    Command lightsOn = () -> {
		    	 light.switchOn();
		    };
		    Command lightsOff = () -> {
		    	 light.switchOff();
		    };
		    //switch on
		    control.setCommand(lightsOn);
		    control.pressButton();
		    //switch off
		    control.setCommand(lightsOff);
		    control.pressButton();
	}

}
