package alter_ego.data;

import java.io.File;
import java.io.FileNotFoundException;

import alter_ego.main.Program;
import alter_ego.parser.FileReader;
import alter_ego.parser.WriteToFile;

public class GenerateUser {

	private String username;
	
	public GenerateUser(String username) {
		this.username = username;
		makeUser();
	}//end of GenerateUser
	
	public void makeUser() {
	
		FileReader temp = null;
		try {
			temp = new FileReader("data/characters/template.chr");//get template file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < temp.getFileData().size(); i++) {
			if(i == 0)//if the first iteration, this is the first line in the file
				new WriteToFile(new File("data/characters/"+this.username+".chr"), temp.getFileData().get(i), false, true);
			else
				new WriteToFile(new File("data/characters/"+this.username+".chr"), temp.getFileData().get(i), false, false);
		}
		
		try {
			new ChangeUserData(new File("data/characters/"+this.username+".chr"),"Name",this.username);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Program.utilities.setUsername(this.username);
		
	}//end of makeUser
	
	/*GETTERS*/
	
	public String getUsername() {
		return username;
	}
	
}//end of class