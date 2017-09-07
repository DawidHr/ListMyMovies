package ListMyMovies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BazaSqlLite {

	
	public static final String DRIVER = "org.splite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:mySerialListDB.sqlite";
	private Statement stat;
	


	Connection conn;

	public BazaSqlLite() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Brak sterownika JDBC");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL);
			stat = conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Problem z otwarciem Polaczenia");
			e.printStackTrace();
		}
	}

	public void dodawanieRekordu(String tytul, String opis) {

		try {
			System.out.println("tytul: "+tytul+" opis:"+opis );
			String dodaj = "INSERT INTO movie(title, lastEpisod) VALUES (?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(dodaj);
			preparedStatement.setString(1, tytul);
			preparedStatement.setString(2, opis);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
	}
	
	public ArrayList<movie> getMovie() {

		ArrayList<movie> description = new ArrayList<>();
		try {
			String zapytanie = "select * from movie";
			Statement stat1 = conn.createStatement();
			ResultSet rs = stat1.executeQuery(zapytanie);
			while (rs.next()) {
				int int0 = rs.getInt("id");
				String title1 = rs.getString("title");
				String lastwatch1 = rs.getString("lastEpisod");
				System.out.println("id:"+int0+" title:"+title1+" last"+lastwatch1);
				description.add(new movie(int0, title1, lastwatch1));			
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem z pobraniem gier");
		}
		return description;
	}
	
public void deleteSelectedGame(String name) {
try {
	String query = "Delete from movie where title = ?";
	PreparedStatement prepareStatement = conn.prepareStatement(query);
	prepareStatement.setString(1, name);
	prepareStatement.executeUpdate();
	
}
catch(Exception e) {
	e.printStackTrace();
}

}



public movie getSelectedMovie(String name) {
	try {
		String query = "select * from movie where  title = ?";
		PreparedStatement prepareStatement1 = conn.prepareStatement(query);
		prepareStatement1.setString(1, name);
		ResultSet rs = prepareStatement1.executeQuery();
		return new movie(rs.getInt("id"), rs.getString("title"), rs.getString("lastEpisod"));
	}
	catch(Exception e) {
		e.printStackTrace();
		return null;
	}
}

public void editRow(String title1, String name) {
	try {
		String query = "Update movie SET  lastEpisod = ? where title = ?";
		PreparedStatement s = conn.prepareStatement(query);
		s.setString(1, name);
		s.setString(2, title1);
		s.executeUpdate();	
		Statement ss = conn.createStatement();
		ResultSet rs = ss.executeQuery("select * from movie");
		while(rs.next()) {
		
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

}
