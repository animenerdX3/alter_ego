package alter_ego.parser;

import java.util.ArrayList;
import java.util.Random;

import alter_ego.main.Program;

public class DataParser {

	private String userData;
	private String [] words;
	private ArrayList<String>lexicon;
	private ArrayList<String>responses;
	private String [] response;
	
	public DataParser(String userData){
		this.userData = userData;
		this.words = userData.split(" ");
		this.lexicon = Program.utilities.getLexicon();
		this.responses = Program.utilities.getResponses();
		startParser();
	}
	
	public void startParser() {
		int responseGiven = 0;
		for(int i = 0; i < lexicon.size(); i++) {
			String [] phrase = lexicon.get(i).split(" ");
			responseGiven = phraseFound(phrase);
			if(responseGiven > 0)
				break;
		}
		
		interpretResponse(responseGiven);
	}//end of startParser
	
	/**
	 * checks to see if the words inputted by the user match any of the phrases kept in Alter Ego's lexicon
	 * @param phrase - current phrase in Alter Ego's lexicon (ex: "my name is")
	 */
	public int phraseFound(String [] phrase) {
		
		int counter = 0;
		int phraseChecks = 0;
		
		//Parse through the whole input from the user
		for(int x = 0; x < words.length && counter < phrase.length - 1; x++) {
			//Parse through each word in the current phrase
				if(words[x].equalsIgnoreCase(phrase[counter])) {
					counter++;
					if(counter == phrase.length - 1)
						return phraseChecks + 1;
				}
				else {
					phraseChecks++;//Check next phrase
					counter = 0;//Set word counter to 0
				}
		}
		
		return 0;//No matches were found in the lexicon
		
	}//end of phraseFound
	
	public void interpretResponse(int responseGiven) {
		
		ArrayList<String[]>choiceResponses = new ArrayList<String[]>();
		
		for(int i = 0; i < responses.size(); i++) {
			String [] splitData = responses.get(i).split("~");
			String [] feedback = splitData[1].split("/");
			if(Integer.parseInt(feedback[0]) == responseGiven) {
				String [] generateResponse = {splitData[0], feedback[1], feedback[2]};
				choiceResponses.add(generateResponse);
			}
		}
		
		chooseResponse(choiceResponses);
		
	}//end of interpretResponse
	
	public void chooseResponse(ArrayList<String[]>choiceResponses) {
		
		Random ran = new Random();
		int selectResponse = ran.nextInt(choiceResponses.size());
		this.response = choiceResponses.get(selectResponse);
		
	}//end of chooseResponse
	
	public String getUserData(){
		return userData;
	}
	
	public String[] getWords(){
		return words;
	}
	
	public String[] getResponse() {
		return response;
	}
	
}//end of class
