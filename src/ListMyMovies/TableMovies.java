package ListMyMovies;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TableMovies extends AbstractTableModel {
	int rowCount;
	ArrayList<movie> description;

	public TableMovies() {
		// TODO Auto-generated constructor stub
	}

	public TableMovies(int rowCount, ArrayList<movie> description) {
		this.rowCount = rowCount;
		this.description = description;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return description.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		movie description1 = description.get(arg0);
		String wyswietl;
		if (arg1 == 0) {
			wyswietl = description1.getTitle();
		} else {
			wyswietl = description1.getLastWatchEpisod()+"";
		}
	//	System.out.println(description1.getTitle() + " " + arg1);
		return wyswietl;
	}

	public String getColumnName(int c) {
		if (c == 0)
			return "Tytu³";
		else
			return "Ostatni obejrzany ocinek";
	}

}
