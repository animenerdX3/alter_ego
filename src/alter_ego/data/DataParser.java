package alter_ego.data;

public class DataParser {

	private String userData;
	private String [] words;
	
	public DataParser(String userData){
		this.userData = userData;
		this.words = userData.split(" ");
		System.out.println(this.userData);
	}
	
	public String getUserData(){
		return userData;
	}
	
	public String[] getWords(){
		return words;
	}
	
}//end of class
