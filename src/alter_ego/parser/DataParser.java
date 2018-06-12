package alter_ego.parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DataParser {

	private String userData;
	private String [] words;
	private ArrayList<String>lexicon;
	
	public DataParser(String userData){
		this.userData = userData;
		this.words = userData.split(" ");
	}
	
	public void startParser() {
		FileReader keywords = null;
		try {
			keywords = new FileReader("data/lexicon.data");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		lexicon = keywords.getFileData();
		
	}//end of startParser
	
	public String getUserData(){
		return userData;
	}
	
	public String[] getWords(){
		return words;
	}
	
}//end of class
