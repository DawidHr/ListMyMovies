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

public class addFrame extends JPanel {
JFrame frame;
BazaSqlLite db;

JPanel panelUp = new JPanel();
JPanel panelCenter = new JPanel();
JPanel panelDown = new JPanel();

JLabel textLabel = new JLabel("Dodawanie Serialu");
JLabel text2Label = new JLabel("Tytu³: ");
JLabel text3Label = new JLabel("Ostatni obejrzany odcinek:");

JTextField movieNameField = new JTextField(20);
JTextField movieLastEpisodField = new JTextField(20);
JButton save = new JButton("Zapisz");

	addFrame() {
		
	}
	addFrame(JFrame frame) {
		this.frame=frame;
		main();
		frame.setSize(400, 200);
	}
	addFrame(JFrame frame, BazaSqlLite db) {
		this.frame=frame;
		this.db = db;
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
		panelCenter.add(movieLastEpisodField, c);
	}
	
	private void setPanelDown() {
		panelDown.add(save);
		save.addActionListener(listener);
	}
	
	ActionListener listener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String lastEpisodString = movieLastEpisodField.getText();
			db.dodawanieRekordu(movieNameField.getText(), lastEpisodString);
			panelUp.setVisible(false);
			panelCenter.setVisible(false);
			panelDown.setVisible(false);
			MainPanel main = new MainPanel(frame, db);
		}
	};
}
