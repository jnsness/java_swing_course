import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;


public class ResultFrame extends JFrame {
	private JTextField textField;

	public ResultFrame(String uuid) {

		setVisible(true);
		setSize(1200, 900);
		setResizable(false);
		setLocation(FormularWindow.frameLocation);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 432, 641);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(140, 53, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Avg Distance");
		lblNewLabel.setBounds(116, 28, 70, 14);
		panel.add(lblNewLabel);
		
//		Format Double Average Distance into better format and put it in textField
		
		String averageDistanceDouble = new java.text.DecimalFormat("0.00").format(new DbAccess().DbCalculateAVGDistance(uuid));
		
		textField = new JTextField(averageDistanceDouble);
		textField.setBounds(221, 25, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panelRight = new JPanel();
		panelRight.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRight.setBounds(452, 11, 732, 641);
		getContentPane().add(panelRight);
		
	}
}
