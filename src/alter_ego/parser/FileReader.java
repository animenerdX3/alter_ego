package alter_ego.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {

	ArrayList<String> fileData;
	
	public FileReader(String fileName) throws FileNotFoundException {
		
		File fileObject = getFile(fileName);
		this.fileData = new ArrayList<String>();
		fileToArrayList(fileObject);
	}
	
	/**
	 * Create a file object
	 * @param directory - the directory of the file
	 * @return
	 */
	private File getFile(String directory) {
		
		return new File(directory);
		
	}//end of getFile
	
	/**
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	private void fileToArrayList(File file) throws FileNotFoundException {
		
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine())
			this.fileData.add(scan.nextLine());
		
		scan.close();
		
	}//end of fileToArrayList
	
	public ArrayList<String> getFileData(){
		return fileData;
	}
	
	public void setFileData(ArrayList<String>fileData) {
		this.fileData = fileData;
	}
	
}//end of class
