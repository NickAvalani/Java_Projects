//package Spitify;
import java.util.*;

public class Library {	
	List<Song> song_library;
	public Library() {
		// constructs a new instance of this class.  
		song_library = new ArrayList<Song>();
	}
	
	public Song getSong(String title) {
		//returns the Song associated with the Stringtitle if it is in the library, or null if it is not.
		for(Song track: song_library){
			String track_title = track.getTitle();
			if(track_title.equals(title)){
				return track;
			}
		}
		return null;
	}
	
	public void addSong(Song song) {
		// adds song to the library.
		song_library.add(song);
		song_library.sort(Comparator.comparing(Song::getTitle));
	}
	
	public void removeSong(Song song) {
		// removes song from the library if it exists in the library.
		song_library.remove(song);
	}
	
	public String toString() {
		//  returns a string representation of this library. 
		//The format is to have the string representation of each song on its own line. 
		//So you want to add the string representation of a song, then a newline for each song. 
		//Songs must be printed in alphabetical order by song name.
		String str = "";
		for(Song track: song_library) {
			str += track.toString() + "\n";	
		}
		return str;
	}
}

