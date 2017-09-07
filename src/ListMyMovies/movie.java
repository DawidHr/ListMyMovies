package ListMyMovies;

public class movie {
private int id;
private String title;
private String lastWatchEpisod;
public movie(int id, String title, String lastWatchEpisod) {
	super();
	this.id = id;
	this.title = title;
	this.lastWatchEpisod = lastWatchEpisod;
}
public movie(String title, String lastWatchEpisod) {
	super();
	this.title = title;
	this.lastWatchEpisod = lastWatchEpisod;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getLastWatchEpisod() {
	return lastWatchEpisod;
}
public void setLastWatchEpisod(String lastWatchEpisod) {
	this.lastWatchEpisod = lastWatchEpisod;
}

}
