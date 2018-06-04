package alter_ego.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

	public static void writeData(File file, String message) {
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			
			if(!file.exists())
				file.createNewFile();
			
				fw = new FileWriter(file.getAbsoluteFile(), true);
				bw = new BufferedWriter(fw);
				bw.write(message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
