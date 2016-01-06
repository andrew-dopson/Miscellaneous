import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class access {
	public static String auditLine = null;
	public static String pathName = "/Users/adopson/Documents/workspaceCS465/MyFacebook/src/";
	public static WriteFile auditFile = new WriteFile(pathName + "audit.txt");
	public static WriteFile friendsFile = new WriteFile(pathName + "friends.txt");
	public static WriteFile listsFile = new WriteFile(pathName + "lists.txt");
	public static WriteFile picturesFile = new WriteFile(pathName + "pictures.txt");
	public static boolean first = true;
	public static boolean ownerFlag = false;
	public static String profileOwner = null;
	public static boolean someoneViewing = false;
	public static String viewingName = null;
	public static boolean overWriteFriends = true;
	public static ArrayList<Listt> listOfLists = new ArrayList<Listt>();
	public static ArrayList<Picture> pictures = new ArrayList<Picture>();
	
	//main method that takes instruction file as an argument to use for execution
	public static void main(String[] args) throws IOException{
		File fileName = new File(pathName + "test2.txt");
		readInstructions(fileName);
	}

	/*
	 * This method will read the instructions from the input file one by one and
	 * execute each instruction.
	 */
	public static void readInstructions(File fileName) throws IOException {
		BufferedReader reader = null;
		
		try {
			String newLine;
			//Buffer created to read lines of input file
			reader = new BufferedReader(new FileReader(fileName));
			while((newLine = reader.readLine()) != null){
				execute(newLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(reader != null) reader.close();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}	
	}

	/*
	 * This method writes all of the Pictures and their data to the "pictures.txt" file
	 * at the end of execution
	 */
	private static void printPictures() throws IOException {
		//iterates through each picture and writes the data to the file
		for(Picture p : pictures){
			picturesFile.writeToFile(p.getName() + ": " + p.getOwner() + " " + p.getList() + " " + p.getPerms() + "\n");
		}
	}

	/*
	 * This method writes all of the lists and their data to the "lists.txt" file
	 */
	private static void printLists() throws IOException {
		//iterates through each list
		for(Listt list : listOfLists){
			listsFile.writeToFile(list.getName() + ":");
			//iterates through each member in the list
			for(String s : list.theList){
				listsFile.writeToFile("   " + s);
			}
			listsFile.writeToFile("\n");
		}
	}
	
	/*
	 * This method is used to add a friend to the list of friends
	 */
	private static void friendadd(String newLine) throws IOException {
		//Checks if this is first instruction
		if(first == true || ownerFlag == true){
			String name = newLine.split(" ", 2)[1];
			name.trim();
			//Checks if the name meets specifications
			if(name.length() <= 30){
				Scanner scanner = new Scanner(name);
				String result = scanner.findInLine("[\\t\\r\\n\\v\\/\\:\\s]");
				
				/*
				 * If the name is valid, has not yet been entered, and the profile owner
				 * is logged on, the name is written to the friends file.
				 */
				if(result == null){
					if(overWriteFriends == true || !(compareName(new FileReader(pathName + "friends.txt"), name)) ){
					//if(overWriteFriends == true || !(compareName(new FileReader("friends.txt"), name)) ){
						friendsFile.writeToFile(name);
						if(first == true){
							profileOwner = name;
						}
						auditLine = "Friend " + name + " added.";
						System.out.println(auditLine);
						overWriteFriends = false;
					}else{
						auditLine = "The name, " + name + " is already in the friends list.";
						System.err.println(auditLine);
					}
					writeAudit(auditLine);
				}
			}	
		}
	}

	/*
	 * This method compares a friend name to the entire list of friends and returns
	 * true if it is found in the list.
	 */
	private static boolean compareName(FileReader reader, String name) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
		while (line != null){
			if (line.equals(name)){
				br.close();
				return true;
			}
			line = br.readLine();
		}
		br.close();
		return false;
	}

	/*
	 * This method terminates the program
	 */
	public static void terminate() throws IOException {
		//writes data to files before terminating
		printLists();
		printPictures();
		System.exit(0);
	}

	/*
	 * Every time an instruction is executed, this method is used to log the
	 * instruction that was executed and save a record of it into the audit.txt
	 * file.
	 */
	public static void writeAudit(String message) throws IOException {
		auditFile.writeToFile(message);
	}

	/*
	 * This method is used to execute each individual instruction
	 */
	public static void execute(String instruction) throws IOException {
		//Checks to make sure first instruction is to add the profile owner
		if (first == true){
			if(instruction.startsWith("friendadd")){
				friendadd(instruction);
				first = false;
			}else{
				auditLine = "friendadd must be the first instruction.";
				System.err.println(auditLine);
				writeAudit(auditLine);
				terminate();
			}
		}else if(instruction.startsWith("friendadd")){			//FRIENDADD command		
			if(ownerFlag == true){			
				friendadd(instruction);
			}else{
				auditLine = "Only the owner can add friends.";
				System.err.println(auditLine);
				writeAudit(auditLine);
			}
		}else if(instruction.startsWith("viewby")){				//VIEWBY command
			viewby(instruction.split(" ", 2)[1]);
		}else if(instruction.startsWith("logout")){				//LOGOUT command
			logout();
		}else if(instruction.startsWith("friendlist")){ 		//FRIENDLIST command
			friendlist(instruction.split(" ", 3)[1], instruction.split(" ", 3)[2]);
		}else if(instruction.startsWith("listadd")){			//LISTADD command
			listAdd(instruction.split(" ", 2)[1]);
		}else if(instruction.startsWith("postpicture")){		//POSTPICTURE command
			postPicture(instruction.split(" ", 2)[1]);
		}else if(instruction.startsWith("chlst")){				//CHLST command
			chlst(instruction.split(" ", 3)[1], instruction.split(" ", 3)[2]);
		}else if(instruction.startsWith("chmod")){				//CHMOD command
			chmod(instruction.split(" ", 3)[1], instruction.split(" ", 3)[2]);
		}else if(instruction.startsWith("chown")){				//CHOWN command
			chown(instruction.split(" ", 3)[1], instruction.split(" ", 3)[2]);
		}else if(instruction.startsWith("readcomments")){		//READCOMMENTS command
			read(instruction.split(" ", 2)[1]);
		}else if(instruction.startsWith("writecomments")){		//WRITECOMMENTS command
			write(instruction.split(" ", 3)[1], instruction.split(" ", 3)[2]);
		}else if(instruction.startsWith("end")){				//END command
			terminate();
		}else{
			System.err.println("Invalid command");
		}
		
	}

	/*
	 * This method writes the specified comment to the given picture, only if the user has
	 * the required permission.
	 */
	private static void write(String pictureName, String text) throws IOException {
		pictureName = pictureName.trim();
		text = text.trim();
		String perm = null;
		//Someone needs to be viewing the profile
		if(someoneViewing){
			//search list of pictures
			for(Picture p : pictures){
				//find picture with specified name
				if(p.getName().equals(pictureName)){
					//if user is profile owner, write the comment (profile owner has rw privileges for all pictures)
					if(ownerFlag){
						p.writeComment(text);
						auditLine = "Friend " + viewingName + " wrote a comment to " + pictureName + ":\n" + text;
						System.out.println(auditLine);
						writeAudit(auditLine);
						return;
					}
					perm = p.getPerms();
					//if the user is the owner of the picture, and has write privileges (w on first rw parameter)
					if(p.getOwner().equals(viewingName)){
						if(perm.charAt(1) == 'w'){
							p.writeComment(text);
							auditLine = "Friend " + viewingName + " wrote a comment to " + pictureName + ":\n" + text;
							System.out.println(auditLine);
							writeAudit(auditLine);
							return;
						}
					}
					//if user is not the owner, but is a member of the list
					//associated with the picture, and has write privileges (w on second rw parameter)
					for(Listt list : listOfLists){	
						if(p.getList().equals(list.getName())){
							if(list.containsFriend(viewingName)){
								perm = p.getPerms();
								if(perm.charAt(4) == 'w'){
									p.writeComment(text);
									auditLine = "Friend " + viewingName + " wrote a comment to " + pictureName + ":\n" + text;
									System.out.println(auditLine);
									writeAudit(auditLine);
									return;
								}
							}
							break;
						}
					}
					perm = p.getPerms();
					//if user is not the owner and also is not a member of the list associated
					//with the picture, but has write privileges in the third rw parameter
					if(perm.charAt(7) == 'w'){
						p.writeComment(text);
						auditLine = "Friend " + viewingName + " wrote a comment to " + pictureName + ":\n" + text;
						System.out.println(auditLine);
						writeAudit(auditLine);
						return;
					}
					//If none of the above, deny access to write.
					auditLine = "Friend " + viewingName + " denied write access to " + p.getName();
					System.err.println(auditLine);
					writeAudit(auditLine);
					return;
				}
			}
			auditLine = "There is no picture with the name: " + pictureName;
			System.err.println(auditLine);
			writeAudit(auditLine);
			return;
		}else{
			auditLine = "No one is currently viewing the profile.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}		
	}

	/*
	 * This method is used to read comments from the specified picture
	 */
	private static void read(String pictureName) throws IOException {
		pictureName = pictureName.trim();
		String perm = null;
		//Someone needs to be viewing the profile
		if(someoneViewing){
			//search list of pictures
			for(Picture p : pictures){
				//find picture with given name
				if(p.getName().equals(pictureName)){
					//if user is the profile owner, read access granted
					if(ownerFlag){
						auditLine = p.getContents(viewingName);
						System.out.println(auditLine);
						writeAudit(auditLine);
						return;
					}
					perm = p.getPerms();
					//If user is the owner of the picture and has read privileges (r on first parameter)
					if(p.getOwner().equals(viewingName)){
						if(perm.charAt(0) == 'r'){
							auditLine = p.getContents(viewingName);
							System.out.println(auditLine);
							writeAudit(auditLine);
							return;
						}
					}
					//If user is not the owner, but is a member of the list associated
					//with the picture and has read privileges on the 2nd rw parameter
					for(Listt list : listOfLists){	
						if(p.getList().equals(list.getName())){
							if(list.containsFriend(viewingName)){
								perm = p.getPerms();
								if(perm.charAt(3) == 'r'){
									auditLine = p.getContents(viewingName);
									System.out.println(auditLine);
									writeAudit(auditLine);
									return;
								}
							}
							break;
						}
					}
					perm = p.getPerms();
					//If the user is not the owner, is not a member of the list associated
					//with the picture, and has read privileges on the 3rd rw parameter
					if(perm.charAt(6) == 'r'){
						auditLine = p.getContents(viewingName);
						System.out.println(auditLine);
						writeAudit(auditLine);
						return;
					}
					//If none of the above, deny read access
					auditLine = "Friend " + viewingName + " denied read access to " + p.getName();
					System.err.println(auditLine);
					writeAudit(auditLine);
					return;
				}
			}
			auditLine = "There is no picture with the name: " + pictureName;
			System.err.println(auditLine);
			writeAudit(auditLine);
			return;
		}else{
			auditLine = "No one is currently viewing the profile.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}	
	}

	/*
	 * This method changes the owner of the specified picture.
	 * This only works if the profile owner is viewing the profile.
	 * The outcome is logged in the audit file.
	 */
	private static void chown(String pictureName, String friendName) throws IOException {
		pictureName = pictureName.trim();
		friendName = friendName.trim();
		
		//If user is viewing profile and is the profile owner
		if(someoneViewing && ownerFlag){
			//search list of pictures
			for(Picture p : pictures){
				//if name of picture equals the specified picture name
				if(p.getName().equals(pictureName)){
					//if friendName is in the list of friends
					if(compareName(new FileReader(pathName + "friends.txt"), friendName)){
					//if(compareName(new FileReader("friends.txt"), friendName)){
						p.changeOwner(friendName);
						auditLine = "Owner of picture: " + pictureName + " changed to: " + friendName;
						System.out.println(auditLine);
						writeAudit(auditLine);
						return;
					}else{
						auditLine = friendName + " is not in the list of friends.";
						System.err.println(auditLine);
						writeAudit(auditLine);
						return;
					}
				}		
			}
			auditLine = "There is no picture with the name: " + pictureName;
			System.err.println(auditLine);
			writeAudit(auditLine);
			return;
		}else{
			auditLine = "Only the profile owner can change picture ownership.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}
	}
	
	/*
	 * This method changes the permissions associated with the specified picture.
	 */
	private static void chmod(String pictureName, String perms) throws IOException {
		pictureName = pictureName.trim();
		perms = perms.trim();
		
		//created a pattern to match permission syntax
		String pattern = "[r-][w-] [r-][w-] [r-][w-]";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(perms);
		
		//checks if permission syntax is correct
		if(m.matches()){
			//someone needs to be viewing the profile
			if(someoneViewing){
				//searches list of pictures
				for(Picture p : pictures){
					//finds picture with specified name
					if(p.getName().equals(pictureName)){
						//only change permissions if current user is the picture owner or profile owner
						if(p.getOwner().equals(viewingName) || ownerFlag){
							p.changePermissions(perms);
							auditLine = "Permissions for: " + pictureName + " set to: " + perms + " by: " + viewingName;
							System.out.println(auditLine);
							writeAudit(auditLine);
							return;
						}
					}
				}
				auditLine = "No picture with name: " + pictureName;
				System.err.println(auditLine);
				writeAudit(auditLine);
				return;
			}else{
				auditLine = "No one is viewing the profile";
				System.err.println(auditLine);
				writeAudit(auditLine);
			}
		}else{
			auditLine = "Incorrect permission syntax";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}
	}
	
	/*
	 * This method changes the list associated with a specified picture.
	 */
	private static void chlst(String pictureName, String listName) throws IOException {
		pictureName = pictureName.trim();
		listName = listName.trim();
		
		//someone needs to be viewing the profile
		if(someoneViewing){
			//search list of pictures
			for(Picture p : pictures){
				//find picture with specified name
				if(p.getName().equals(pictureName)){
					//if the owner of the picture or the profile owner is viewing the profile 
					if(ownerFlag || p.getOwner().equals(viewingName)){
						//search list of lists
						for(Listt list : listOfLists){
							//find list with specified name
							if (list.getName().equals(listName)){
								//change list only if the owner is a member of that list (or profile owner)
								if(list.containsFriend(viewingName) || ownerFlag){
									p.changeList(listName);
									auditLine = pictureName + "'s list changed to: " + listName;
									System.out.println(auditLine);
									writeAudit(auditLine);
									return;
								}else{
									auditLine = viewingName + " does not have permission to edit picture list.";
									System.err.println(auditLine);
									writeAudit(auditLine);
									return;
								}
							}
						}
						auditLine = "There is no list with the name: " + listName;
						System.err.println(auditLine);
						writeAudit(auditLine);
						return;
					}
				}
			}
			auditLine = "There are no pictures with the name: " + pictureName;
			System.err.println(auditLine);
			writeAudit(auditLine);
			return;
		}
		auditLine = "No one is viewing the profile";
		System.err.println(auditLine);
		writeAudit(auditLine);
		return;
	}

	/*
	 * This method is used to post a new picture to the profile. This can be done by any friend of the profile.
	 */
	private static void postPicture(String pictureName) throws IOException {
		pictureName = pictureName.trim();
		//A friend needs to be viewing the profile to execute
		if(someoneViewing){
			//search list of pictures
			for(Picture p : pictures){
				//checks if there is already a picture with the given name
				if(p.getName().equals(pictureName)){
					auditLine = "There is already a picture with the name: " + pictureName;
					System.err.println(auditLine);
					writeAudit(auditLine);
					return;
				}
			}
			//if all pictures have been searched and no picture has the same name, add the new picture
			pictures.add(new Picture(pictureName, viewingName));
			auditLine = "Picture: " + pictureName + ", added with owner: " + viewingName + ", and default permissions added";
			System.out.println(auditLine);
			writeAudit(auditLine);
		}else{
			auditLine = "A friend needs to be viewing the profile to add a picture.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}
		
	}

	/*
	 * This method adds a list with the specified name to the list of lists.
	 * The outcome is then logged in the audit file.
	 */
	private static void listAdd(String listName) throws IOException {
		listName = listName.trim();
		if(ownerFlag = true){
			for(Listt list : listOfLists){
				if(list.getName().equals(listName)){
					auditLine = "There is already a list named: " + listName;
					System.err.println(auditLine);
					writeAudit(auditLine);
					return;
				}
			}
			//A new list is created and added only if the profile owner is logged on and 
			//the list with that name doesn't already exist.
			listOfLists.add(new Listt(listName));
			auditLine = "List, " + listName + ", was added to the list of lists.";
			System.out.println(auditLine);
			writeAudit(auditLine);
		}else{
			auditLine = "Only the profile owner can add lists.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}
		
	}

	/*
	 * This method is used to add a friend to a specified list. This can only be done
	 * by the profile owner.  
	 */
	private static void friendlist(String friendName, String listName) throws IOException {
		friendName = friendName.trim();
		listName = listName.trim();
		
		if(ownerFlag == true){
			//Iterates through the list of lists
			for(Listt list : listOfLists){
				//If the name of the list is equal to the name of the list trying to be accessed
				if(list.getName().equals(listName)){
					int i = list.addFriend(friendName);
					//Above method returns 1 if the friend is already in the specified list
					if(i == 1){
						auditLine = friendName + " is already a member of this list.";
						System.err.println(auditLine);
						writeAudit(auditLine);
						return;
					}else{
						auditLine = friendName + " was added to the list: " + listName;
						System.out.println(auditLine);
						writeAudit(auditLine);
						return;
					}
				}
			}
			//Execution only gets to this point if a list with the given name is not found.
			auditLine = "The list: " + listName + " was not found.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}else{
			auditLine = "The profile owner is not currently viewing.";
			System.err.println(auditLine);
			writeAudit(auditLine);
		}
		
	}
	
	/*
	 * This method is used to log the current user out, where no one 
	 * is viewing the profile. This is also logged in the audit file.
	 */
	private static void logout() throws IOException {
		someoneViewing = false;
		ownerFlag = false;
		auditLine = viewingName + " has logged out";
		System.out.println(auditLine);
		writeAudit(auditLine);
		viewingName = null;
	}

	/*
	 * This method is used to give access to the owner or a friend.
	 * If the member is not in the list of friends, an error message is printed
	 * and that person is not given access to any commands.  If someone else is currently
	 * viewing the profile, an error message is printed as well and that person is still 
	 * not given access. Otherwise, the member is given access. 
	 */
	private static void viewby(String string) throws FileNotFoundException, IOException {
		string = string.trim();
		if(someoneViewing == false){
			if(compareName(new FileReader(pathName + "friends.txt"), string)){
			//if(compareName(new FileReader("friends.txt"), string)){
				someoneViewing = true;
				viewingName = string;
				//if the profile owner is trying to log in, raise the owner flag 
				if(string.equals(profileOwner)){
					ownerFlag = true;
				}
				auditLine = string + " is now viewing the profile.";
				System.out.println(auditLine);
			}else{
				auditLine = string + " is not in the list of friends.";
				System.err.println(auditLine);
			}
		}else{
			auditLine = "Someone else is currently viewing the profile.";
			System.err.println(auditLine);
		}
		//log the outcome of the command
		writeAudit(auditLine);
	}

}
