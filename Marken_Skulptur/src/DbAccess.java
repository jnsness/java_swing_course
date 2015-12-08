import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JTextField;

public class DbAccess {

	public Connection c = null;
	public Statement stmt = null;

	public DbAccess() {
	}

	public void DbSavePersonalData(String uuid, String txtName,
			String txtNachname, String txtBeruf, int ageSpinner, int sexCombo) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO personal_info (UUID,Vorname,Nachname,Beruf,Age,Geschlecht) VALUES ("
					+ "'"
					+ uuid
					+ "'"
					+ ","
					+ "'"
					+ txtName
					+ "'"
					+ ","
					+ "'"
					+ txtNachname
					+ "'"
					+ ","
					+ "'"
					+ txtBeruf
					+ "'"
					+ ","
					+ ageSpinner + "," + sexCombo + ");";

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

	public void DbSaveLogoData(String uuid,
			ArrayList<DraggableLogoComponent> logolist) {

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

				// Change "/" in Filename to "+" because of escaping Crazyness
				// of Java for further regex

				String logonameCleaned = logoname.replace("\\", "+");

				// Regex Split now Folder+Category+File.JPG in seperate Values -
				// separated in \ and .

				String[] regexArrayLogoname = logonameCleaned.split("\\+|\\.");

				String sql = "INSERT INTO logo_info (UUID,logo_name,category,brandName,xcord,ycord,innerFrame) VALUES ("
						+ "'"
						+ uuid
						+ "'"
						+ ","
						+ "'"
						+ logoname
						+ "'"
						+ ","
						+ "'"
						+ regexArrayLogoname[1]
						+ "'"
						+ ","
						+ "'"
						+ regexArrayLogoname[2]
						+ "'"
						+ ","
						+ x
						+ ","
						+ y
						+ ","
						+ innerFrame + ");";
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

	public void DbSaveTimeData(String uuid, double elapsedSeconds) {

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
			String sql = "INSERT INTO time_info (UUID,elapsedTime,date) VALUES ("
					+ "'"
					+ uuid
					+ "'"
					+ ","
					+ "'"
					+ elapsedSeconds
					+ "'"
					+ ","
					+ "'" + dateString + "'" + ");";

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

	public void DbCalculateDistances(String uuid) {

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		// SQL Query only gives data which fits to current uuid (process)

		String sqlQuery = "Select UUID, brandName, xcord, ycord from logo_info where UUID ="
				+ "'" + uuid + "';";

		ArrayList<QueryObjectsForCalculation> listFromQuery = new ArrayList<QueryObjectsForCalculation>();

		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {

				String uuidFromQuery = rs.getString("UUID");
				String brandName = rs.getString("brandName");
				int x = rs.getInt("xcord");
				int y = rs.getInt("ycord");

				// Creating a new object for arraylist
				// "QueryObjectsForCalculation

				QueryObjectsForCalculation objectForList = new QueryObjectsForCalculation(
						uuidFromQuery, brandName, x, y);
				listFromQuery.add(objectForList);


			}

			for (QueryObjectsForCalculation objectsForList : listFromQuery) {
				
				System.out.println(objectsForList.getBrandName());
				
			}
			
			
			c.setAutoCommit(false);
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
