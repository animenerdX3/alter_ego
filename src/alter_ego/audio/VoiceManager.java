package alter_ego.audio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class VoiceManager {

	private Sound [] voices;
	private float volume;
	
	public VoiceManager() {
		//Set default volume
		this.volume = 0.5f;
	}
	
	public VoiceManager(String directory) {
		//Set default volume
		this.volume = 0.5f;
		readVoiceFiles(directory);//Find our voice files
	}

	/**
	 * creates our array of sound effect objects
	 * @param directory
	 */
	public void readVoiceFiles(String directory) {
		File voiceFile = getFile(directory);//Turn into a file type
		try {
			voices = getVoiceFiles(voiceFile);//Get the array of voices
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			}
	}//end of SoundManager
	
	/**
	 * 
	 * @param directory
	 * @return a file object based on our directory
	 */
	public File getFile(String directory) {
		return new File(directory);
	}
	
	/**
	 * finds out how many sound effect objects we actually have
	 * @param soundFile
	 * @return size of the array
	 * 
	 */
	public int checkSize(File soundFile) throws FileNotFoundException {
		int arraysize = 0;
		Scanner scan = new Scanner(soundFile);
		
		while(scan.hasNextLine()) {
			scan.nextLine();
			arraysize++;
		}
		
		scan.close();
		return arraysize;
	}//end of checkSize
	
	/**
	 * Gets the voice paths from the file specified
	 * @param soundFile
	 * @return an array with a bunch of voice files
	 * @throws FileNotFoundException
	 */
	public Sound[] getVoiceFiles(File soundFile) throws FileNotFoundException{
		Scanner scan = new Scanner(soundFile);
		
		//Create temporary voices array to store info in, just in case something goes wrong
		Sound [] voice_files = new Sound[checkSize(soundFile)];
		
		//Create voices array
		for(int i = 0; i < voice_files.length; i++) {
			try {
				voice_files[i] = new Sound("voices/"+scan.nextLine()+".wav");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		scan.close();//Close the scanner
		return voice_files;//Return the files
		
	}//end of getSoundFiles

	/* GETTERS */
	
	/**
	 * 
	 * @return the array of voices
	 */
	public Sound [] getVoices() {
		return voices;
	}
	
	/**
	 * 
	 * @return our voice volume
	 */
	public float getVolume() {
		return volume;
	}
	
	/* SETTERS */

	/**
	 * changes our voices array
	 * @param voices
	 */
	public void setVoices(Sound[] voices) {
		this.voices = voices;
	}
	
	/**
	 * changes our voices volume
	 * @param volume
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
	
}//end of class
