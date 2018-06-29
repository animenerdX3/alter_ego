package alter_ego.data;

import java.io.File;
import java.io.FileNotFoundException;

import alter_ego.parser.FileReader;
import alter_ego.parser.WriteToFile;

public class ChangeUserData {

	public ChangeUserData(File userFile, String category, String rewrite) throws FileNotFoundException {
		
		FileReader user = new FileReader(userFile);
		replaceLine(user, category, rewrite);
		rewriteData(user, userFile);
		
	}//end of ChangeUserData
	
	/**
	 * replaces the line of the desired category to the new value
	 * @param user the user file
	 * @param category the category in the user file
	 * @param rewrite the new data
	 */
	public void replaceLine(FileReader user, String category, String rewrite) {
		for(int i = 0; i < user.getFileData().size(); i++) {
			String [] line = user.getFileData().get(i).split("~");
			if(line[0].equalsIgnoreCase(category))
				user.getFileData().set(i, category+"~"+rewrite.substring(0, 1).toUpperCase() + rewrite.substring(1));
		}
		
	}//end of findLine
	
	public void rewriteData(FileReader user, File userFile) {
		
		for(int i = 0; i < user.getFileData().size(); i++) {
			if(i == 0)
				new WriteToFile(userFile, user.getFileData().get(i), true, true);
			else
				new WriteToFile(userFile, user.getFileData().get(i), false, false);

		}
		
	}//end of rewriteData
	
}//end of class
