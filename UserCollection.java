//package Spitify;
import java.util.*;

public class UserCollection {
	List<User> users;
	List<String> str_users;
	String str;
	
	public UserCollection() {
		// Creates a new instance of the UserCollection class. 
		this.users = new ArrayList<User>();
		this.str_users = new ArrayList<String>();
		this.str = "";
	}
	
	public boolean userExists(String username) {
		// returns true if a user with the specified username exists.
		for(User elem : users) {
			if(elem.getName().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public User login(String username, String password) {
		// returns the User associated with the login credentials if a valid login or null if the login was invalid.
		for (User user: users) {
			if(user.getName().equals(username)) {
				if(user.attemptLogin(password)) {
					return user;
				}
				return null;
			}
		}
		return null;
	}
	
	public void addUser(User add) {
		//adds this user to the collection of all users.
		this.users.add(add);
	}
	
	public String toString() {
		// returns a string description of all the users in alphabetical order.
		users.sort(Comparator.comparing(User::getName));
		for(User user: users) {
			str_users.add(user.getName() + ": " + user.getNum() + " playlists" );
		}
		for(int i = 0; i < str_users.size(); i++) {
			if(i == str_users.size() - 1) {
				str += str_users.get(i) + "}";
			}
			str += "{";
			for(int j = 0; j < str_users.size(); j ++) {
				str += str_users.get(j) + ",";
			}
		}	
		return str;
	}
}
