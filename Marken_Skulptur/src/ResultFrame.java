import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ResultFrame extends JFrame {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	public ResultFrame(String uuid) {

		ResultFrameView view = new ResultFrameView(uuid);



	}
}
