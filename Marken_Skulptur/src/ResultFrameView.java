import java.awt.Color;
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

/**
 * Stellt die GUI des Ergebnis-Fensters dar. Besitzt deshalb zahlreiche Setter, da diese angesteuert werden. 
 * L�dt das Bild der Skulptur in das rechte Panel und stellt die Werte und Tabellen in dem linken Panel da.
 * 
 * @author Jnsness
 *
 */

public class ResultFrameView extends JFrame {
	private JTextField textFieldAvgDistanceEO;
	private JTextField textFieldAvgDistanceToYou;
	private JTextField textFieldGravityPoint;

	private String valueOftextFieldAvgDistanceEO;
	private String valueOftextFieldAvgDistanceToYou;
	private String valueOftextFieldGravityPoint;
	private JTable tableForDistances;
	private JTable tableForGravPointDistances;
	private JTable tableForDistancesEachOther;
	private JTable tableForOutside;
	private JTable tableForUnderAvgEO;
	private JTable tableForOverAvgEO;
	private JTable tableForUnderAvgYou;
	private JTable tableForOverAvgYou;
	
	
	
	public ResultFrameView(String uuid) {
		this.valueOftextFieldAvgDistanceEO = valueOftextFieldAvgDistanceEO;
	}

	public void initGUI(String uuid) {

		
//		Panel Stuff
		
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

		
//		Fill TextField with Values
		
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

		JTabbedPane resultPane = new JTabbedPane(JTabbedPane.TOP);
		resultPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		resultPane.setBounds(10, 124, 395, 506);

		// Add Tabs to the TablePanel (resultPane) - Tables get filled by ResultFrame (which generates Data from HelperClass TableToJTable)

		resultPane.addTab("Distances To You", new JScrollPane(tableForDistances));
		resultPane.addTab("Distances Grav Point", new JScrollPane(tableForGravPointDistances));
		resultPane.addTab("Distances EO",new JScrollPane(tableForDistancesEachOther));
		resultPane.addTab("Outside",new JScrollPane(tableForOutside));
		tableForOutside.setBackground(new Color(255,185,128));
		resultPane.addTab("Under AVG distance EO",new JScrollPane(tableForUnderAvgEO));
		tableForUnderAvgEO.setBackground(new Color(171,255,133));
		resultPane.addTab("Over AVG distance EO",new JScrollPane(tableForOverAvgEO));
		tableForOverAvgEO.setBackground(new Color(255,185,128));
		resultPane.addTab("Under AVG distance to You",new JScrollPane(tableForUnderAvgYou));
		tableForUnderAvgYou.setBackground(new Color(171,255,133));
		resultPane.addTab("Over AVG distance to You",new JScrollPane(tableForOverAvgYou));
		tableForOverAvgYou.setBackground(new Color(255,185,128));
		
		panel.add(resultPane);

	}

//	Setters for TextField Values
	
	public void setValueOftextFieldAvgDistanceEO(
			String valueOftextFieldAvgDistanceEO) {
		this.valueOftextFieldAvgDistanceEO = valueOftextFieldAvgDistanceEO;
	}

	public void setValueOftextFieldAvgDistanceToYou(
			String valueOftextFieldAvgDistanceToYou) {
		this.valueOftextFieldAvgDistanceToYou = valueOftextFieldAvgDistanceToYou;
	}

	public void setValueOftextFieldGravityPoint(String valueOftextFieldGravityPoint) {
		this.valueOftextFieldGravityPoint = valueOftextFieldGravityPoint;
	}

	public void setTableForDistances(JTable tableForDistances) {
		this.tableForDistances = tableForDistances;
	}

	public void setTableForGravPointDistances(JTable tableForGravPointDistances) {
		this.tableForGravPointDistances = tableForGravPointDistances;
	}

	public void setTableForDistancesEachOther(JTable tableForDistancesEachOther) {
		this.tableForDistancesEachOther = tableForDistancesEachOther;
	}

	public void setTableForOutside(JTable tableForOutside) {
		this.tableForOutside = tableForOutside;
	}

	public void setTableForUnderAvgEO(JTable tableForUnderAvgEO) {
		this.tableForUnderAvgEO = tableForUnderAvgEO;
	}

	public void setTableForOverAvgEO(JTable tableForOverAvgEO) {
		this.tableForOverAvgEO = tableForOverAvgEO;
	}

	public void setTableForUnderAvgYou(JTable tableForUnderAvgYou) {
		this.tableForUnderAvgYou = tableForUnderAvgYou;
	}

	public void setTableForOverAvgYou(JTable tableForOverAvgYou) {
		this.tableForOverAvgYou = tableForOverAvgYou;
	}
	

}
