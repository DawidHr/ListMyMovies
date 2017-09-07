package ListMyMovies;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class editFrame extends JPanel {
JFrame frame;
BazaSqlLite db;
movie movie1;

JPanel panelUp = new JPanel();
JPanel panelCenter = new JPanel();
JPanel panelDown = new JPanel();

JLabel textLabel = new JLabel("Edycja Serialu");
JLabel text2Label = new JLabel("Tytu³: ");
JLabel text3Label = new JLabel("Ostatni Odcinek:");

JLabel movieNameField = new JLabel();
JTextField movieLastEpisodWatchField = new JTextField(20);
JButton save = new JButton("Zapisz");




	editFrame() {
		
	}
	editFrame(JFrame frame) {
		this.frame=frame;
		main();
		frame.setSize(400, 200);
	}
	editFrame(JFrame frame, BazaSqlLite db, String name) {
		this.frame=frame;
		this.db = db;
		movie1 = db.getSelectedMovie(name);
		if(movie1==null) System.exit(0);
		main();
	}
	
	private void main() {
		setPanelUp();
		setPanelCenter();
		setPanelDown();
		frame.add(panelUp, BorderLayout.NORTH);
		frame.add(panelCenter, BorderLayout.CENTER);
		frame.add(panelDown, BorderLayout.SOUTH);
	}
	private void setPanelUp() {
		panelUp.add(textLabel, BorderLayout.CENTER);
	}
	
	private void setPanelCenter() {
		movieNameField.setText(movie1.getTitle());
		movieLastEpisodWatchField.setText((movie1.getLastWatchEpisod()+""));
		panelCenter.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.fill =GridBagConstraints.BOTH;
		panelCenter.add(text2Label, c);
		c.gridx=1;
		panelCenter.add(movieNameField, c);
		c.gridx=0;
		c.gridy=1;
		panelCenter.add(text3Label, c);
		c.gridx=1;
		panelCenter.add(movieLastEpisodWatchField, c);
	}
	
	private void setPanelDown() {
		panelDown.add(save);
		save.addActionListener(listener);
	}
	
	ActionListener listener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String title1 = movie1.getTitle();
			String lastEpisod = movieLastEpisodWatchField.getText();
			db.editRow(title1, lastEpisod);
			
			panelUp.setVisible(false);
			panelCenter.setVisible(false);
			panelDown.setVisible(false);
			MainPanel main = new MainPanel(frame, db);
		}
	};
	
	
	
/*
public boolean showDialog(Component parent, String title) {
	ok=false;
	Frame owner;
	if(parent instanceof Frame) 
	owner = (Frame) parent;
	else
	owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
	
	if(dialog == null || dialog.getOwner() != owner) {
		dialog = new JDialog(owner, true);
		dialog.add(this);
		dialog.getRootPane().setDefaultButton(save);
		dialog.pack();
	}
	
	dialog.setTitle(title);
	dialog.setVisible(true);
	return ok;
}*/
}
