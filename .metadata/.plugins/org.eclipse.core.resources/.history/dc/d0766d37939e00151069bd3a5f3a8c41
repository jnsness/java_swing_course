import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ResultFrame extends JFrame {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	public ResultFrame(String uuid) {

		ResultFrameView view = new ResultFrameView();
		
		// Format Double Average Distance into better format and put it in
		// textField

		String averageDistanceDouble = new java.text.DecimalFormat("0.00")
				.format(new DbAccess().DbCalculateAVGDistance(uuid));

		String[] columns = { "Brand Name", "Compare Name", "Distance" };
		Object[][] data = { { "Audi", "BMW", new Integer(10) },
				{ "Mercedes", "Tesla", new Integer(15) } };

	}
}
