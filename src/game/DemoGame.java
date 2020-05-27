package game;

import java.io.FileNotFoundException;

public class DemoGame {

	public static void main(String[] args) throws FileNotFoundException{
		
		Controller control = new Controller();
		while (true) {
			control.newGame();
		}
			
	}
}
