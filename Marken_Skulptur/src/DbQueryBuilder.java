import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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


			System.out.println(uuid);
			statement = con.prepareStatement("Select avg_distance_as, UUID from avg_distance where UUID = ?");
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

}