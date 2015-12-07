import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;

public class DbAccess {

	public Connection c = null;
	public Statement stmt = null;

	public DbAccess() {}
	
	public void DbSavePersonalData (String uuid,String txtName, String txtNachname, String txtBeruf, int ageSpinner, int sexCombo) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		try {
			stmt = c.createStatement();
			String sql = 
					"INSERT INTO personal_info (UUID,Vorname,Nachname,Beruf,Age,Geschlecht) VALUES ("+"'"+uuid+"'"+","+"'"+txtName+"'"+","+"'"+txtNachname+"'"+","+"'"+txtBeruf+"'"+","+ ageSpinner +","+ sexCombo +");";
					
			c.setAutoCommit(false);
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DbSaveLogoData(String uuid, ArrayList<DraggableLogoComponent> logolist) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		try {
			stmt = c.createStatement();
			c.setAutoCommit(false);
			
			for (DraggableLogoComponent logo : logolist) {
			
			int x = logo.getX();
			int y = logo.getY();
			int innerFrame = 0;
			
			if (x > 60 && x < 770 && y > 50 && y < 740) {
				innerFrame = 1;
			}
			
			String logoname = logo.getFileName();
			
			String sql = 
					"INSERT INTO logo_info (UUID,logo_name,xcord,ycord,innerFrame) VALUES ("+"'"+uuid+"'"+","+"'"+logoname+"'"+","+ x +","+ y +","+ innerFrame +");";
			stmt.executeUpdate(sql);
			}
			
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void DbSaveTimeData (String uuid, double elapsedSeconds) {
		
		Date date = new Date();
		String dateString = date.toString();
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		try {
			stmt = c.createStatement();
			String sql = 
					"INSERT INTO time_info (UUID,elapsedTime,date) VALUES ("+"'"+uuid+"'"+","+"'"+elapsedSeconds+"'"+","+"'"+dateString+"'"+");";
					
			c.setAutoCommit(false);
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	
	
}
