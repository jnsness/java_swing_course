import java.awt.Point;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DbQueryBuilder {

	private Connection con;
	private PreparedStatement statement;

	public DbQueryBuilder() {
	}

	public double getavgDistanceAs(String uuid) {

		double avgDistanceAs = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");

			statement = con
					.prepareStatement("Select avg_distance_as, UUID from avg_distance where UUID = ?");
			statement.setString(1, uuid);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				avgDistanceAs = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return avgDistanceAs;
	}

	public double getavgDistanceToYou(String uuid) {

		double avgDistanceToYou = 0;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");

			statement = con
					.prepareStatement("Select avg_distance_to_you, UUID from avg_distance where UUID = ?");
			statement.setString(1, uuid);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				avgDistanceToYou = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return avgDistanceToYou;
	}

	public Point getGravPoint(String uuid) {

		Point gravPoint = new Point();

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");

			statement = con
					.prepareStatement("Select gravityPointX, gravityPointY, UUID from gravityPoint where UUID = ?");
			statement.setString(1, uuid);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				gravPoint.setLocation(rs.getDouble(1), rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gravPoint;
	}


}
