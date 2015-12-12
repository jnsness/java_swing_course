import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;

public class ResultFrameView extends JFrame {
	private JTextField textFieldAvgDistanceEO;
	private JTextField textFieldAvgDistanceToYou;
	private JTextField textFieldGravityPoint;

	public ResultFrameView(String uuid) {

		setVisible(true);
		setSize(1200, 900);
		setResizable(false);
		setLocation(FormularWindow.frameLocation);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 415, 641);
		panel.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Avg Distance (each other)");
		lblNewLabel.setBounds(106, 30, 126, 14);
		panel.add(lblNewLabel);

		// Set board to the right panel of resultFrame

		JPanel panelRight = new JPanel();
		panelRight.setBounds(432, 0, 732, 641);
		panelRight.setBorder(new TitledBorder(null, "Image",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelRight);

		ImageIcon icon = new ImageIcon(new ImageIcon("snapshots/" + uuid
				+ ".jpg").getImage().getScaledInstance(600, 580,
				Image.SCALE_DEFAULT));
		JLabel label = new JLabel(icon, JLabel.CENTER);
		panelRight.add(label);

		textFieldAvgDistanceEO = new JTextField();
		textFieldAvgDistanceEO.setEditable(false);
		
		
		new DbQueryBuilder().getavgDistanceAs(uuid);
		textFieldAvgDistanceEO.setText(String.valueOf((int)new DbQueryBuilder().getavgDistanceAs(uuid)));
		
		
		textFieldAvgDistanceEO.setBounds(308, 27, 70, 20);
		panel.add(textFieldAvgDistanceEO);
		textFieldAvgDistanceEO.setColumns(10);

		JLabel lblAvgDistanceTo = new JLabel("Avg Distance to you");
		lblAvgDistanceTo.setBounds(106, 60, 97, 14);
		panel.add(lblAvgDistanceTo);

		textFieldAvgDistanceToYou = new JTextField();
		textFieldAvgDistanceToYou.setBounds(308, 57, 70, 20);
		panel.add(textFieldAvgDistanceToYou);
		textFieldAvgDistanceToYou.setColumns(10);

		JLabel lblGravitypoint = new JLabel("GravityPoint");
		lblGravitypoint.setBounds(106, 91, 126, 14);
		panel.add(lblGravitypoint);

		textFieldGravityPoint = new JTextField();
		textFieldGravityPoint.setBounds(308, 88, 70, 20);
		panel.add(textFieldGravityPoint);
		textFieldGravityPoint.setColumns(10);

		// TabbedPanel Result with Tables

		JTabbedPane resultPane = new JTabbedPane(JTabbedPane.TOP);
		resultPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		resultPane.setBounds(10, 124, 395, 506);

//		Data for Table
		
		String[][] rowData = { { "Japan", "245" }, { "USA", "240" },
				{ "Italien", "220" }, { "Spanien", "217" },
				{ "T�rkei", "215" }, { "England", "214" },
				{ "Frankreich", "190" }, { "Griechenland", "185" },
				{ "Deutschland", "180" }, { "Portugal", "170" } };
		
		 String[] columnNames =  {
			      "Land", "Durchschnittliche Fernsehdauer pro Tag in Minuten"
			    };
		 
		 
		 JTable table1 = new JTable(rowData,columnNames);
		 table1.setFillsViewportHeight(true);

		 
		JTable table2 = new JTable();

		resultPane.addTab("Erster", new JScrollPane(table1));
		resultPane.addTab("Zweiter", table2);

		panel.add(resultPane);

	}
}
