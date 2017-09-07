package ListMyMovies;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MainPanel {
	JFrame frame;
	BazaSqlLite db;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	JButton addButton = new JButton("Dodaj");
	JButton editButton = new JButton("Edytuj");
	JButton deleteButton = new JButton("Kasuj");
	
	ArrayList<movie> rekordy = new ArrayList<>();
	TableModel tabela;
	JTable tab;
	String[] description = { "Tytu³", "Odcinek" };
	
	MainPanel() {
		super();
	}
	MainPanel(JFrame frame) {
		super();
		this.frame = frame;
		main();
	}
	MainPanel(JFrame frame, BazaSqlLite db) {
		super();
		this.frame = frame;
		this.db = db;
		main();
		frame.pack();
	}

	private void main() {
		setPanel1();
		setPanel2();
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.SOUTH);
	}
	
	private void setPanel1() {
		rekordy = db.getMovie();		
		tabela = new TableMovies(2, rekordy);
		tab = new JTable(tabela);
		tab.setRowHeight(90);
		tab.setPreferredScrollableViewportSize(new Dimension(600, 300));
		tab.setAutoCreateRowSorter(true);
		JScrollPane pane = new JScrollPane(tab);
		panel1.add(pane);
	}
	
	private void setPanel2() {
		panel2.setLayout(new FlowLayout());
		panel2.add(addButton);
		panel2.add(editButton);
		panel2.add(deleteButton);
		
		addButton.addActionListener(listener);
		editButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
	}
	
	ActionListener listener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String operation = e.getActionCommand();
			if(operation.equalsIgnoreCase("Dodaj")) {
				panel1.setVisible(false);
				panel2.setVisible(false);
				addFrame n = new addFrame(frame, db);
			}
			else if(operation.equalsIgnoreCase("Edytuj")) {
				if(tab.getSelectedRow() == -1) {
					System.out.println("Nie wybrano co Edytowaæ");
				}
				else{
					String title = tab.getModel().getValueAt(tab.getSelectedRow(), 0).toString();
					panel1.setVisible(false);
					panel2.setVisible(false);
					editFrame editFrameStart = new editFrame(frame, db, title);
				}
				
			}
			else {
				if(tab.getSelectedRow() == -1) {
					System.out.println("Nie wybrano co Kasowaæ");
				}
				else {
					String title = tab.getModel().getValueAt(tab.getSelectedRow(), 0).toString();
					System.out.println(title);
					db.deleteSelectedGame(title);
					panel1.setVisible(false);
					panel1.removeAll();
					rekordy.removeAll(rekordy);
					panel1.setVisible(true);
					setPanel1();
					
				}
				
			
			}
		}
		
		
	};
}