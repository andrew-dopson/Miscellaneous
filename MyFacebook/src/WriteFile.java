import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/*
 * This class is used to allow the program to write text to a given file
 */
public class WriteFile {
	private String path;
	private boolean addText = false;
	
	//Constructor that overwrites the existing file if there is one.
	public WriteFile(String file_path){
		path = file_path;
	}
	
	//Constructor that adds to existing file
	public WriteFile(String file_path, boolean add){
		path = file_path;
		addText = add;
	}
	
	//Method that writes the given text to the WriteFile object 
	public void writeToFile(String text) throws IOException{
		FileWriter write = new FileWriter(path, addText);
		PrintWriter printLine = new PrintWriter(write);
		printLine.println(text);
		printLine.close();
		addText = true;
	}
	
}
