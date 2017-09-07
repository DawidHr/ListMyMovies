package ListMyMovies;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class frame extends JFrame {
	BazaSqlLite db;
	
frame() {
	super();
	setTitle("Lista Ogl¹danych Seriali");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setSize(700, 200);
	db = new BazaSqlLite();
	main();
}

public void main() {
	MainPanel panel = new MainPanel(frame.this, db);
}
}
