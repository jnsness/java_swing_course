import javax.swing.*;
import javax.swing.table.*;

import java.sql.*;

/**
 * This class create JTable from Database table. User program needs to specify
 * database connection and corresponding atable name.
 * 
 * @author Hemraj
 * adapted from http://www.programmingforfuture.com/2010/05/generating-jtable-from-database-table.html
 */

// adapted from http://www.programmingforfuture.com/2010/05/generating-jtable-from-database-table.html

public class TableToJTable extends JTable {
	private Connection con;

	public TableToJTable() {
		this.con = con;
	}

	public JTable getTable(String query) {
		JTable t1 = new JTable();

		try {

			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		try {

			DefaultTableModel dm = new DefaultTableModel();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			// Coding to get columns-
			int cols = rsmd.getColumnCount();
			String c[] = new String[cols];
			for (int i = 0; i < cols; i++) {
				c[i] = rsmd.getColumnName(i + 1);
				dm.addColumn(c[i]);
			}
			// get data from rows
			Object row[] = new Object[cols];
			while (rs.next()) {
				for (int i = 0; i < cols; i++) {
					row[i] = rs.getString(i + 1);
				}
				dm.addRow(row);
			}
			t1.setModel(dm);
			con.close();

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t1;

	}

}