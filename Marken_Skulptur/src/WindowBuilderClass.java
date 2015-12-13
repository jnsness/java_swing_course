import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class WindowBuilderClass extends JFrame{
	
	
	private JTextField textFieldAvgDistanceEO;
	private JTextField textFieldAvgDistanceToYou;
	private JTextField textFieldGravityPoint;

	private String valueOftextFieldAvgDistanceEO;
	private String valueOftextFieldAvgDistanceToYou;
	private String valueOftextFieldGravityPoint;
	private JTable tableForDistances;
	private JTable tableForGravPointDistances;
	private JTable tableForDistancesEachOther;

	public WindowBuilderClass (String uuid) {
	
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
	lblNewLabel.setBounds(106, 30, 192, 14);
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

	
//	Fill TextField with Values
	
	textFieldAvgDistanceEO = new JTextField();
	textFieldAvgDistanceEO.setEditable(false);

	textFieldAvgDistanceEO.setText(valueOftextFieldAvgDistanceEO);

	textFieldAvgDistanceEO.setBounds(308, 27, 70, 20);
	panel.add(textFieldAvgDistanceEO);
	textFieldAvgDistanceEO.setColumns(10);

	JLabel lblAvgDistanceTo = new JLabel("Avg Distance to you");
	lblAvgDistanceTo.setBounds(106, 60, 192, 14);
	panel.add(lblAvgDistanceTo);

	
	
	textFieldAvgDistanceToYou = new JTextField();
	textFieldAvgDistanceToYou.setEditable(false);
	textFieldAvgDistanceToYou.setText(valueOftextFieldAvgDistanceToYou);
	
	textFieldAvgDistanceToYou.setBounds(308, 57, 70, 20);
	panel.add(textFieldAvgDistanceToYou);
	textFieldAvgDistanceToYou.setColumns(10);

	JLabel lblGravitypoint = new JLabel("GravityPoint");
	lblGravitypoint.setBounds(106, 91, 192, 14);
	panel.add(lblGravitypoint);

	textFieldGravityPoint = new JTextField();
	textFieldGravityPoint.setEditable(false);
	textFieldGravityPoint.setText(valueOftextFieldGravityPoint);

	
	textFieldGravityPoint.setBounds(308, 88, 70, 20);
	panel.add(textFieldGravityPoint);
	textFieldGravityPoint.setColumns(10);

	// TabbedPanel Result with Tables

	JTabbedPane resultPane = new JTabbedPane(JTabbedPane.LEFT);
	resultPane.setBounds(10, 124, 395, 506);

	// Add Tabs to the TablePanel (resultPane) - Tables get filled by ResultFrame (which generates Data from HelperClass TableToJTable)

	resultPane.addTab("Distances To You", new JScrollPane(tableForDistances));
	resultPane.addTab("From Grav Point", new JScrollPane(tableForGravPointDistances));
	resultPane.addTab("Distances Each Other",new JScrollPane(tableForDistancesEachOther));
	
	panel.add(resultPane);
	}

}
