//package Spitify;
import java.util.*;

public class Song {
	String song_title;
	Integer play_time;
	String song_artist;
	public Song(String title, String artist) {
	// construct a new instance of the Song class with the specified song title and artist.
		this.song_title = title;
		this.song_artist = artist;
		this.play_time = 0;
	}

	public String getTitle() {
	//return the title of the song.
		return this.song_title;
	}
	
	public String getArtist() {
	// return the artist of  the song.
		return this.song_artist;
	}
	
	public int getTimesPlayed() {
		// return the number of times this song has been played.
		return this.play_time;
		
	}
	
	public void play() {
		// play the song by printing a description of it and increment the number of times it has been played.
		System.out.println(toString());
		this.play_time += 1;
	}
	
	public String toString() {
		// return a string description of this song. It should be of the format: title by artist, timesPlayed play(s). 
		// So if you have the song God's Plan by Drake 
		// and it has been played 27 times, it should return "God's Plan by Drake, 27 play(s)"
		return this.song_title + " by " + this.song_artist + ", " + String.valueOf(play_time) + " play(s)"; 
	}
}
