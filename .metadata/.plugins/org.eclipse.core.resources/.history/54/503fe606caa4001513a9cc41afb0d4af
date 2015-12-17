import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ResultFrame extends JFrame {

	public ResultFrame(String uuid) {

		ResultFrameView view = new ResultFrameView(uuid);
		new DbQueryBuilder().getavgDistanceAs(uuid);

		// fill View with values from SQL Query

		view.setValueOftextFieldAvgDistanceEO(String
				.valueOf((int) new DbQueryBuilder().getavgDistanceAs(uuid)));
		view.setValueOftextFieldAvgDistanceToYou(String
				.valueOf((int) new DbQueryBuilder().getavgDistanceToYou(uuid)));
		view.setValueOftextFieldGravityPoint(String
				.valueOf((int) new DbQueryBuilder().getGravPoint(uuid).getX())
				+ " | "
				+ String.valueOf((int) new DbQueryBuilder().getGravPoint(uuid)
						.getY()));

		String query1 = "select brandName, distance_from_you from logo_info where UUID = '"
				+ uuid + "' order by distance_from_you ASC;";
		view.setTableForDistances(new TableToJTable().getTable(query1));
		
		String query2 = "select brandName, distance_from_gravPoint from logo_info where UUID = '"
				+ uuid + "' order by distance_from_gravPoint ASC;";
		view.setTableForGravPointDistances(new TableToJTable().getTable(query2));
		
		String query3 = "select sourceBrandNAme, distance, compareBrandName from compare_brandDistances where UUID = '"
				+ uuid + "' order by distance ASC;";
		view.setTableForDistancesEachOther(new TableToJTable().getTable(query3));
		
		String query4 = "select brandName from logo_info where innerFrame = 0 and UUID = '"
				+ uuid + "';";
		view.setTableForOutside(new TableToJTable().getTable(query4));

		String query5 = "select A.sourceBrandName, A.compareBrandName, A.distance from compare_brandDistances AS A JOIN avg_distance AS B ON A.UUID = B.UUID where A.distance < B.avg_distance_as and A.UUID = '"
				+ uuid + "' order by A.distance DESC;";
		view.setTableForUnderAvgEO(new TableToJTable().getTable(query5));
	
		String query6 = "select A.sourceBrandName, A.compareBrandName, A.distance from compare_brandDistances AS A JOIN avg_distance AS B ON A.UUID = B.UUID where A.distance > B.avg_distance_as and A.UUID = '"
				+ uuid + "' order by A.distance ASC;";
		view.setTableForOverAvgEO(new TableToJTable().getTable(query6));

		String query7 = "select A.brandName, A.distance_from_you from logo_info AS A JOIN avg_distance AS B ON A.UUID = B. UUID where A.distance_from_you < B.avg_distance_to_you and A.UUID =  '"
				+ uuid + "'; order by A.distance_from_you DESC";
		view.setTableForUnderAvgYou(new TableToJTable().getTable(query7));
		
		String query8 = "select A.brandName, A.distance_from_you from logo_info AS A JOIN avg_distance AS B ON A.UUID = B. UUID where A.distance_from_you > B.avg_distance_to_you and A.UUID =  '"
				+ uuid + "'; order by A.distance_from_you ASC";
		view.setTableForOverAvgYou(new TableToJTable().getTable(query8));

		view.initGUI(uuid);

	}
}
