//package Spitify;
import java.util.*;

public class User {
	 String user_name;
	 String user_password;
	 int num_playlist;
	 List<Playlist> playlists;
	 
	public User(String name, String password) {
		this.user_name = name;
		this.user_password = password;
		this.playlists = new ArrayList<Playlist>();
	}
	
	public String getName() {
		return user_name;
	}
	
	public int getNum() {
		return num_playlist;
	}
	
	public boolean attemptLogin(String password) {
		if(user_password.equals(password)){
			return true;
		}
			return false;
	}
	
	public void addPlaylist(Playlist newPlaylist) {
		//adds the specified playlist to the user's playlists.
		playlists.add(newPlaylist);
		this.num_playlist += 1;
	}
	
	public List<Playlist> getPlaylists(){
		//returns a list of the playlists for this user.
		return playlists;
	}
	
	public void selectPlaylist(String name) {
		// Selects the playlist with the specified name if the user has a playlist by that name and plays it.
		for(Playlist obj: playlists) {
			if(obj.getName().equals(name)) {
				obj.play();
			}
		}
	}
	
	public String toString() {
		// returns a string description of the user. The format is name, numPlaylists playlists.
		return this.user_name + ", " + this.num_playlist + " playlists." ;
	}
}
