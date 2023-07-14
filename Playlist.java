//package Spitify;
import java.util.*;

public class Playlist {
	private String playlist_name;
	private List<Song> playlist_contents;
	
	public Playlist(String name) {
		// constructs a new instance of the Playlist class with the specified name. 
		this.playlist_name = name;
		this.playlist_contents = new ArrayList<Song>();
	}
	
	public Playlist(String name, List<Song> contents) {
		// constructs a new instance of the Playlist class with the specified name and songs.
		this.playlist_name = name;
		this.playlist_contents = contents;
	}
	
	public String getName() {
		// returns the name of the playlist.
		return this.playlist_name;
	}
	
	public void addSong(Song song) {
		// adds the specified song to the playlist.
		this.playlist_contents.add(song);
	}
	
	public void play() {
		//playing the playlist by printing each song. Remember that a song has its own toString() method.
		for(Song song: this.playlist_contents) {
			song.play();
		}
	}
	
	public void removeSong(Song song) {
		// remove song from the playlist if it exists in the playlist.  
		this.playlist_contents.remove(song);
	}
}
