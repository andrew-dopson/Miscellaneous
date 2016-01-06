import java.util.ArrayList;


/*
 * This class is used for the creation of individual lists.  Each list
 * has a name and a corresponding list of members.
 */
public class Listt {
	public String name = null;
	ArrayList<String> theList = new ArrayList<String>();
	
	//Constructor that provides the list name
	public Listt(String listName){
		name = listName;
	}
	
	//Adds specified friend to the list if it is not already 
	public int addFriend(String friend){
		if(theList.contains(friend)){
			return 1;
		}
		theList.add(friend);
		return 0;
	}
	
	//Getter for the list name
	public String getName(){
		return name;
	}
	
	/*
	 * This method compares the specified name to each name in the list
	 * @return true if equal
	 */
	public boolean containsFriend(String name){
		for (String s : theList){
			if(s.equals(name)){
				return true;
			}
		}
		return false;
	}
	
}
