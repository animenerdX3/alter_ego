package alter_ego.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import alter_ego.parser.FileReader;

public class Util {

	private ArrayList<String>lexicon;
	private ArrayList<String>responses;
	private String username;
	
	public Util() {
		createLexicon();
		createResponses();
	}

	private void createLexicon() {
		FileReader keywords = null;
		try {
			keywords = new FileReader("data/lexicon.data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.lexicon = keywords.getFileData();
	}//end of createLexicon
	
	private void createResponses() {
		FileReader responses = null;
		try {
			responses = new FileReader("data/response.data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.responses = responses.getFileData();
	}//end of createResponses
	
	/*GETTERS*/
	
	public ArrayList<String> getLexicon() {
		return lexicon;
	}
	
	public ArrayList<String> getResponses() {
		return responses;
	}
	
	public String getUsername() {
		return username;
	}
	
	/*SETTERS*/
	
	public void setLexicon(ArrayList<String> lexicon) {
		this.lexicon = lexicon;
	}
	
	public void setResponses(ArrayList<String> responses) {
		this.responses = responses;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}//end of class
