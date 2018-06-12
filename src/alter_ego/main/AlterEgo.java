package alter_ego.main;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import alter_ego.audio.VoiceManager;
import alter_ego.parser.DataParser;

public class AlterEgo extends BasicGameState implements ComponentListener {

	//Instance Variables
	private Image [] emotion;
	private int currentEmotion;
	private VoiceManager voice;
	private Sound [] voiceFiles;
	private int currentLine;
	private String userName;
	
	//Player Controllers
	private TextField data;
	private String dataValue;
	
	//Alter Ego Controllers
	private String alterEgoValue;
	private int dialogCounter;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		//Create emotion UI
		emotion = new Image[8];
		for(int i = 0; i < emotion.length; i++)
			emotion[i] = new Image("ui/ego_"+i+".png");
		
		//Create voices
		voice = new VoiceManager("voices/voices.chr");
		voiceFiles = voice.getVoices();
		
		currentEmotion = 1;
		currentLine = 0;
		
		//Create textbox
		Font font = new Font("OCR A Extended", Font.ITALIC, 20);
		TrueTypeFont ttf = new TrueTypeFont(font, true);
		data = new TextField(gc, ttf, 0, 421, Launcher.WIDTH, 30, this);
		
		alterEgoValue = "What Is Your Name?";
		voiceFiles[currentLine].play(1f, voice.getVolume());
		
	}//end of init

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//Draw Alter Ego
		g.drawImage(emotion[currentEmotion], 0, 0);
		
		//Draw Alter Ego Text
		g.drawString(alterEgoValue, 10, 391);
		
		//Draw Text Box
		data.render(gc, g);
		data.setBorderColor(Color.black);
		data.setFocus(true);
		data.setTextColor(Color.white);
		
	}//end of render

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		menuFunctions(gc);
		
	}//end of update

	private void menuFunctions(GameContainer gc) {
		Input input = gc.getInput();
		
		//If esc key is pressed, close the program
		if(input.isKeyPressed(Input.KEY_ESCAPE))
			Launcher.appgc.exit();
		
	}//end of menuFunctions
	
	public void componentActivated(AbstractComponent component) {
		
		if(component == data){
			if(!data.getText().isEmpty()){
				dataValue = data.getText();
				new DataParser(dataValue);
			}
			checkData();
		}
		
	}//end of componentActivated
	
	public void checkData(){
		if(dialogCounter == 0){
			changeEgo("Umm...I don't understand that yet.", 25, 7);
		}
		if(dialogCounter == 1){
			changeEgo("This is test data!", 23, 6);
		}
		dialogCounter++;
	}
	
	public void changeEgo(String message, int currentVoiceLine, int emotion){
		alterEgoValue = message;
		voiceFiles[currentLine].stop();
		currentLine = currentVoiceLine;
		currentEmotion = emotion;
		voiceFiles[currentLine].play(1f, voice.getVolume());
	}
	
	public int getID() {
		return 0;
	}//end of getID

}//end of class
