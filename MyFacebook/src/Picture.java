import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * This class is used to create individual pictures that
 * are posted onto the profile.
 */
public class Picture {
	String name = null;
	String owner = null;
	String list = "nil";
	String permissions = "rw -- --";
	WriteFile pictureFile = null;
	String filePath = null;
	
	//Constructor that sets the picture's name and owner of the picture.
	//Also creates a file for the picture that contains all of its comments
	public Picture(String pictureName, String ownerName) throws IOException{
		name = pictureName;
		owner = ownerName;
		pictureFile = new WriteFile("/Users/adopson/Documents/workspaceCS465/MyFacebook/src/" + name);
		//pictureFile = new WriteFile(name);
		pictureFile.writeToFile(name);
		filePath = "/Users/adopson/Documents/workspaceCS465/MyFacebook/src/" + name;
	
	}
	
	//Getter for the picture name
	public String getName(){
		return name;
	}
	
	//Getter for the owner
	public String getOwner(){
		return owner;
	}
	
	/*
	 * This method changes the name of the owner to the specified name
	 */
	public void changeOwner(String ownerName){
		owner = ownerName;
	}
	
	//Changes the permissions to the specified string 'perm'
	public void changePermissions(String perm){
		permissions = perm;
	}
	
	//This method changes the name of the list to the specified name
	public void changeList(String listName){
			list = listName;
	}
	
	//Writes the specified text to the picture file
	public void writeComment(String comment) throws IOException{
		pictureFile.writeToFile(comment);
	}

	//Getter for the text in the picture file
	public String getContents(String viewName) throws IOException {
		String comments = "Friend " + viewName + " reads " + name + " as:";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		//BufferedReader br = new BufferedReader(new FileReader(name));
		String line = null;
		
		while((line = br.readLine()) != null){
			comments = comments + "\n" + line;
		}
		br.close();
		return comments;
	}

	//Getter for the picture permissions
	public String getPerms() {
		return permissions;
	}
	
	//Getter for the list name associated with the picture
	public String getList(){
		return list;
	}	
}
