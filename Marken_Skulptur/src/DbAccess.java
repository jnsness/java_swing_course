import java.awt.List;
import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTextField;

public class DbAccess {

	private Connection c = null;
	private Statement stmt = null;


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

				// Calculate Distance to you

				int youX = LogoFrame.getYouLogo().getX();
				int youY = LogoFrame.getYouLogo().getY();

				int distanceX = youX - x;
				int distanceY = youY - y;
				int distanceSquareRoot = (int) Math
						.sqrt((distanceX * distanceX) + (distanceY * distanceY));

				String sql = "INSERT INTO logo_info (UUID,logo_name,category,brandName,xcord,ycord,innerFrame,distance_from_you) VALUES ("
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
						+ innerFrame + "," + distanceSquareRoot + ");";
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

		// SQL Query only selects data which fits to current uuid (process)

		String sqlQuery = "Select UUID, brandName, xcord, ycord from logo_info where UUID ="
				+ "'" + uuid + "';";

		ArrayList<QueryObjectsForCalculationDistances> loopCache = new ArrayList<QueryObjectsForCalculationDistances>();

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

				QueryObjectsForCalculationDistances objectForList = new QueryObjectsForCalculationDistances(
						uuidFromQuery, brandName, x, y);

				loopCache.add(objectForList);

			}

			// Looping for buildung a whole table to cross-connect every brand
			// with each other

			for (int i = 0; i < loopCache.size(); i++) {
				for (int j = 0; j < loopCache.size(); j++) {

					// If-statement for removing same-value-pairs (e.g. Audi and
					// Audi)

					if (!loopCache.get(i).getBrandName()
							.equals(loopCache.get(j).getBrandName())) {
						String identifier = loopCache.get(i).getUuidFromQuery();
						String sourceBrand = loopCache.get(i).getBrandName();
						int sourceX = loopCache.get(i).getX();
						int sourceY = loopCache.get(i).getY();
						String compareBrand = loopCache.get(j).getBrandName();
						int compareX = loopCache.get(j).getX();
						int compareY = loopCache.get(j).getY();

						// Vector-Distance-Calculation

						int distanceX = sourceX - compareX;
						int distanceY = sourceY - compareY;
						int distanceSquareRoot = (int) Math
								.sqrt((distanceX * distanceX)
										+ (distanceY * distanceY));

						String sqlInsert = "INSERT INTO compare_brandDistances (UUID,sourceBrandName,sourceX,sourceY,compareBrandName,compareX,compareY,distance) VALUES ("
								+ "'"
								+ identifier
								+ "'"
								+ ","
								+ "'"
								+ sourceBrand
								+ "'"
								+ ","
								+ "'"
								+ sourceX
								+ "'"
								+ ","
								+ "'"
								+ sourceY
								+ "'"
								+ ","
								+ "'"
								+ compareBrand
								+ "'"
								+ ","
								+ "'"
								+ compareX
								+ "'"
								+ ","
								+ "'"
								+ compareY
								+ "'"
								+ ","
								+ "'"
								+ distanceSquareRoot + "'" + ");";

						stmt.executeUpdate(sqlInsert);
					}
				}

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

	public double DbCalculateAVGDistance(String uuid) {
		double avgDistanceAs = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		String sqlQuery = "select AVG(distance) from compare_brandDistances where UUID ="
				+ "'" + uuid + "';";

		try {

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				avgDistanceAs = rs.getDouble("AVG(distance)");
				String sql = "INSERT INTO avg_distance (UUID,avg_distance_as) VALUES ("
						+ "'"
						+ uuid
						+ "'"
						+ ","
						+ "'"
						+ avgDistanceAs
						+ "'"
						+ ");";

				stmt.executeUpdate(sql);

				c.setAutoCommit(false);
				stmt.close();
				c.commit();
				c.close();
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avgDistanceAs;
	}

	public void DbCalculateAVGDistanceFromYou(String uuid) {
		double avgDistanceFromYou = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		String sqlQuery = "select AVG(distance_from_you) from logo_info where UUID ="
				+ "'" + uuid + "';";

		try {

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				avgDistanceFromYou = rs.getDouble("AVG(distance_from_you)");
				String sql = "Update avg_distance SET avg_distance_to_you ="
						+ avgDistanceFromYou + " where UUID =" + "'" + uuid
						+ "';";

				stmt.execute(sql);

				c.setAutoCommit(false);
				stmt.close();
				c.commit();
				c.close();
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Point DbCalculateCentreOfGravity(String uuid) {
		Point gravityPoint = new Point();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		// find GravityPoint with Sum / Amount ("arithmetisches Mittel") in
		// coordinates. "You" button is not in list and has to be counted in
		// manually (+1)
		int youX = LogoFrame.YouLogo.getX();
		int youY = LogoFrame.YouLogo.getY();
		String sqlQuery = "select ((sum(xcord)+"
				+ youX
				+ ")/(count(xcord))+1) as gravityPointX, ((sum(ycord)+"
				+ youY
				+ ")/count((ycord))+1) as gravityPointY from logo_info where UUID ="
				+ "'" + uuid + "';";

		try {

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {
				gravityPoint.setLocation(rs.getInt("gravityPointX"),
						rs.getInt("gravityPointY"));
				int x = (int) gravityPoint.getX();
				int y = (int) gravityPoint.getY();

				String sql = "INSERT INTO gravityPoint (UUID,gravityPointX,gravityPointY) VALUES ("
						+ "'"
						+ uuid
						+ "'"
						+ ","
						+ "'"
						+ x
						+ "'"
						+ ","
						+ "'"
						+ y + "'" + ");";

				stmt.execute(sql);

				c.setAutoCommit(false);
				stmt.close();
				c.commit();
				c.close();
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gravityPoint;

	}

	public void DbCalculateDistanceFromCentreOfGravity(String uuid,
			Point gravityPoint) {

		ArrayList<QueryObjectsForCalculationGravPointDistances> distancesToGravPoint = new ArrayList<QueryObjectsForCalculationGravPointDistances>();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		int gravYouX = (int) gravityPoint.getX();
		int gravYouY = (int) gravityPoint.getY();
		String sqlQuery = "select xcord, ycord, brandName from logo_info where UUID ="
				+ "'" + uuid + "';";

		try {

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while (rs.next()) {

				int xcord = rs.getInt("xcord");
				int ycord = rs.getInt("ycord");
				String brandName = rs.getString("brandName");

				// Vector-Calculation for Distance from Points to gravPoint

				int distanceX = gravYouX - xcord;
				int distanceY = gravYouY - ycord;

				int distanceFromGravPoint = (int) Math
						.sqrt((distanceX * distanceX) + (distanceY * distanceY));

				// Generate new Object for in HelperClass
				// QueryObjectsForCalculationGravPointDistances to create an
				// Array to later fill DB gradually
				QueryObjectsForCalculationGravPointDistances object = new QueryObjectsForCalculationGravPointDistances(
						distanceFromGravPoint, brandName);

				distancesToGravPoint.add(object);

			}
			c.setAutoCommit(false);
			stmt.close();

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// another stmt because to inserts in one is obviously not possible -
		// this looks WAY overloaded but it is necassary to
		try {
			stmt = c.createStatement();
			c.setAutoCommit(false);

			for (QueryObjectsForCalculationGravPointDistances object : distancesToGravPoint) {

				int distance = object.getDistanceFromGravPoint();
				String brandName = object.getBrandName();

				String sql = "Update logo_info SET distance_from_gravPoint ="
						+ distance + " where UUID =" + "'" + uuid
						+ "' AND brandName = '" + brandName + "'   ;";

				stmt.execute(sql);
			}

			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
