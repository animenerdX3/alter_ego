package alter_ego.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import alter_ego.data.GenerateUser;
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
		String [] phrase = null;
		for(int i = 0; i < lexicon.size(); i++) {
			phrase = lexicon.get(i).split(" ");
			responseGiven = phraseFound(phrase);
			if(responseGiven > 0)
				break;
		}
		
		//if the response given is the first one found, this is the name of the user
		if(responseGiven == 1) {
			new GenerateUser(this.words[words.length - 1].toLowerCase());
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
				generateResponse[0] = checkForData(generateResponse[0]);//Check if the response has any data in it
				choiceResponses.add(generateResponse);
			}
		}
		
		chooseResponse(choiceResponses);
		
	}//end of interpretResponse
	
	public String checkForData(String response) {
		FileReader userData = null;
		String []  newwords = response.split(" ");
		
		for(int i = 0; i < newwords.length; i++) {
			if(newwords[i].contains("username@")) {
				try {
					userData = new FileReader(new File("data/characters/"+Program.utilities.getUsername()+".chr"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String [] caseCheck = newwords[i].split("@");
				caseCheck[1] = removePunctuation(caseCheck[1]);
				
				
				for(int x = 0; x < userData.getFileData().size(); x++) {
					String [] userValues = userData.getFileData().get(x).split("~");
					if(userValues[0].equalsIgnoreCase(caseCheck[1])) {
						newwords[i] = userValues[1];
					}
				}
			}
		}
		
		String temp = newwords[0];
		
		for(int s = 1; s < newwords.length; s++)
			temp = temp.concat(" "+newwords[s]);
		
		
		return temp;
		
	}//end of checkForData
	
	/**
	 * 
	 * @param text
	 * @return the text without punctuation
	 */
	public String removePunctuation(String text) {
		
		text = text.toLowerCase();
		char [] splitWord = text.toCharArray();
		
		ArrayList<Character>stringBuilder = new ArrayList<Character>();
		
		for(int i = 0; i < splitWord.length; i++) {
			if(Character.isLetter(splitWord[i]))
				stringBuilder.add(splitWord[i]);
		}
		
		StringBuilder builder = new StringBuilder(stringBuilder.size());
		
		for(Character ch: stringBuilder) {
		        builder.append(ch);
		}

		return builder.toString();

	}//end of removePunctuation
	
	public void chooseResponse(ArrayList<String[]>choiceResponses) {
		
		Random ran = new Random();
		int selectResponse = ran.nextInt(choiceResponses.size());
		this.response = choiceResponses.get(selectResponse);
		
	}//end of chooseResponse
	
	public void changeEmotion() {
		
	}
	
	/*GETTERS*/
	
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
