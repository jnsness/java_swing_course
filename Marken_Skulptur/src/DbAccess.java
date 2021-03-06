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

/**
 * Klasse mit verschiedenen Methoden zum Speichern von Daten in die Datenbank
 * Sqlite
 * 
 * @author Jnsness
 *
 */
public class DbAccess {

	private Connection c = null;
	private Statement stmt = null;

	public DbAccess() {
	}

	/**
	 * Abspeichern der Personen-Informationen in Tabelle personal_info
	 * 
	 * 
	 * @param uuid
	 *            - Forein Key jeder Tabelle
	 * @param txtName
	 *            - Vorname aus FormularWindow
	 * @param txtNachname
	 *            - Nachname aus FormularWindow
	 * @param txtBeruf
	 *            - Beruf aus FormularWindow
	 * @param ageSpinner
	 *            - Alter aus FormularWindow
	 * @param sexCombo
	 *            - Geschlecht aus Formularwindow
	 */
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

	/**
	 * Abspeichern der Logo-Informationen in Tabelle time_info
	 * 
	 * Solange Logos in Liste enthalten sind werden Koordinaten, X,Y,
	 * Mittelpunkt-X, Mittelpunkt-Y vorbereitet Verzweigung ob Logos hinter der
	 * Au�en-Linie liegen �ber regul�re Ausdr�cke wird die URL
	 * /Category/image.jpg (Filename) in Kategorie und Logoname aufgeteilt.
	 * Zerschnitten wird bei den / /. Abspeichern erneut in Array �ber
	 * Vektorberechnung wird Distanz zum YouLogo errechnet = Quadratwurzel
	 * (SkalarproduktX� + SkalarproduktY�)
	 * 
	 * @param uuid
	 *            - Forein Key jeder Tabelle
	 * @param logolist
	 *            - Arraylist, welche alle Logo-Informationen beinhaltet
	 */

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
				int centreX = (int) logo.getCentrePoint().getX();
				int centreY = (int) logo.getCentrePoint().getY();

				if (centreX > 90 && centreX < 810 && centreY > 90
						&& centreY < 780) {
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

				String sql = "INSERT INTO logo_info (UUID,logo_name,category,brandName,xcord,ycord,centreX,centreY,innerFrame,distance_from_you) VALUES ("
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
						+ centreX
						+ ","
						+ centreY
						+ ","
						+ innerFrame
						+ ","
						+ distanceSquareRoot + ");";
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

	/**
	 * Abspeichern der vergangenen Zeit
	 * 
	 * @param uuid
	 *            - Forein Key aller Tabellen
	 * @param elapsedSeconds
	 *            Zeit zwischen Laden des LogoFrames und klick auf Next
	 */

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

	/**
	 * Ausrechnen und Abspeichern aller Abst�nde zueinander in
	 * compare_brandDistances
	 * 
	 * Abfrage �ber Query aller notwendigen Daten der gleichen UUID Hilfsklasse
	 * QueryObjectsForCalculationDistances dient dieser Abspeicherung um die
	 * passenden Daten zu haben loopCache dient als Cache, da keine zwei
	 * Resultsets ineinander ablaufen k�nnen wie eine Schleife Somit werden im
	 * ResultSet passende Objekte QueryObjectsForCalculationDistances
	 * instanziiert und der LoopCache Arraylist hinzugef�gt Daraufhin l�uft For
	 * Schleife innerhlab einer For Schleife ab um alle Objekte miteianender zu
	 * verkn�pfen (A-B, A-C, A-D...) Sofern Objekt identisch ist (A-A), wird
	 * dies �bersprungen Vektorberechnungn errechnet Distanz zwischen den beiden
	 * Objekten - Abspeichern in Datenbank
	 * 
	 * @param uuid
	 *            - Forein Key aller Tabellen
	 */

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

	/**
	 * Berechnung und Abspeichern der Duchschnittsdistanz aller Logos zueinander
	 * in avg_distance
	 * 
	 * @param uuid
	 *            - Forein Key f�r jede Tabelle
	 * @return - Durchschnittsdistanz aller Logos untereinander
	 */

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

	/**
	 * Berechnung und Abspeichern der Duchschnittsdistanz aller Logos zum
	 * youLogo in avg_distance
	 * 
	 * @param uuid
	 *            Forein Key f�r jede Tabelle
	 */

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

	/**
	 * Errechnen und Abspeichern des Schwerpunkts aller Logos + YouLogo in
	 * Tabelle gravityPoint Berechnung folgt dem arithmetischen Mittel: Summe
	 * aller Mittelpunkte der Logos durch Anzahl der Mittelpunkte der Logos
	 * Summe wird um Mittelpunkt des You-Logos erg�nzt und Anzahl + 1, da
	 * YouLogo nicht in Tabelle logo_info enthalten ist (da urspr�nglich nicht
	 * in arraylist aufgenommen), dies sollte aber mit einkalkuliert werden
	 * 
	 * @param uuid
	 *            Forein Key f�r jede Tabelle
	 * @return - 
	 */

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
		int youX = (int) LogoFrame.YouLogo.getCentrePoint().getX();
		int youY = (int) LogoFrame.YouLogo.getCentrePoint().getY();
		String sqlQuery = "select ((sum(centreX)+"
				+ youX
				+ ")/(count(centreX))+1) as gravityPointX, ((sum(centreY)+"
				+ youY
				+ ")/count((centreY))+1) as gravityPointY from logo_info where UUID ="
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

	/**
	 * Abspeichern der Distanz zum Schwerpunkt jedes Logos �ber Update in
	 * logo_info Hilfsklasse QueryObjectsForCalculationGravPointDistances wurde
	 * erstellt um passende Daten als Objekt zu erstellen Erst wird �ber Query
	 * alle Informationen aus Logo-Info mit gleicher UUID geholt und in
	 * Arraylist als QueryObjectsForCalculationGravPointDistances
	 * zwischengespeichert (Hintergrund: zwei Resultsets nicht m�glich in einem
	 * Statement)
	 * Anschlie�end lief Arraylist durch und updatete den Wert aus Vektorberechnung jedes Logos zum Schwerpunkt
	 * 
	 * 
	 * @param uuid
	 *            - Foreign Key aller Tabellen
	 * @param gravityPoint
	 *            - Errechnet aus Methode DbCalculateCentreOfGravity
	 */

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
