package alter_ego.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import alter_ego.utils.Util;

public class Program extends StateBasedGame{

	public static final double version = 1.00;
	public static final int WIDTH = 676;
	public static final int HEIGHT = 451;
	public static final int FPS = 60;
	public static AppGameContainer appgc;
	public static Util utilities;
	
	public Program(String name) {
		super(name);
		this.addState(new AlterEgo());
	}//end of Launcher
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.enterState(0);
	}//end of initStatesList
	
	public static void main(String [] args) {
		try {
			utilities = new Util();
			appgc = new AppGameContainer(new Program("Alter Ego - Version "+version));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);//Set size of our window
			appgc.setAlwaysRender(true); // Constant Rendering 
			appgc.setTargetFrameRate(FPS);//Set framerate
			appgc.setShowFPS(false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}//end of main

}//end of class
