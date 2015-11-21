import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

public class DbUpdatePersonalData {

	public Connection c = null;
	public Statement stmt = null;

	public DbUpdatePersonalData(String id,String txtName, String txtNachname, String txtBeruf, int ageSpinner, int sexCombo) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		try {
			stmt = c.createStatement();
			String sql = 
					"INSERT INTO personal_info (ID,Vorname,Nachname,Beruf,Age,Geschlecht) VALUES ("+"'"+id+"'"+","+"'"+txtName+"'"+","+"'"+txtNachname+"'"+","+"'"+txtBeruf+"'"+","+ ageSpinner +","+ sexCombo +");";
					
			c.setAutoCommit(false);
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			System.out.println("ERFOLGREICH!");
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}