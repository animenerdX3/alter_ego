package alter_ego.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

	public WriteToFile(File file, String message, boolean deleteFile, boolean firstLine) {
		writeData(file, message, deleteFile, firstLine);
	}
	
	/**
	 * writes data to a file
	 * @param file the name of the file being written on
	 * @param message the message being written onto the file
	 * @param deleteFile overwrite the file?
	 * @param firstLine is this the first line of the file?
	 */
	public static void writeData(File file, String message, boolean deleteFile, boolean firstLine) {
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			
			if(!file.exists())//if the file does not exist, make a new file
				file.createNewFile();
			else if(deleteFile) {//(assumes the file already exists) if the file wants to be overwritten, delete the file data
				file.delete();
				file.createNewFile();
			}
			
				fw = new FileWriter(file.getAbsoluteFile(), true);
				bw = new BufferedWriter(fw);
				
				if(!firstLine)//if this is not the first line, move to the next one
					bw.newLine();
				
				bw.write(message);//write to file
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//close bufferedwriter and filewriter
		finally {
			try {
				if(bw != null)
					bw.close();//Close bufferedwriter
				if(fw != null)
					fw.close();//Close filewriter
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}//end of writeData
	
}//end of class
