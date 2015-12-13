import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ResultFrame extends JFrame {

	
	
	public ResultFrame(String uuid) {

		ResultFrameView view = new ResultFrameView(uuid);
		new DbQueryBuilder().getavgDistanceAs(uuid);

//		fill View with values from SQL Query
		
		view.setValueOftextFieldAvgDistanceEO(String.valueOf((int) new DbQueryBuilder().getavgDistanceAs(uuid)));
		view.setValueOftextFieldAvgDistanceToYou(String.valueOf((int) new DbQueryBuilder().getavgDistanceToYou(uuid)));
		view.setValueOftextFieldGravityPoint(String.valueOf((int)new DbQueryBuilder().getGravPoint(uuid).getX())+" | "+String.valueOf((int)new DbQueryBuilder().getGravPoint(uuid).getY()));
		
		String query1 = "select brandName, distance_from_you from logo_info where UUID = '" + uuid + "' order by distance_from_you ASC;";
		view.setTableForDistances(new TableToJTable().getTable(query1));
		String query2 = "select brandName, distance_from_gravPoint from logo_info where UUID = '" + uuid + "' order by distance_from_gravPoint ASC;";
		view.setTableForGravPointDistances(new TableToJTable().getTable(query2));
		String query3 = "select sourceBrandNAme, distance, compareBrandName from compare_brandDistances where UUID = '" + uuid + "' order by distance ASC;";
		view.setTableForDistancesEachOther(new TableToJTable().getTable(query3));

		
		
		view.createPanel(uuid);
		

	}
}
