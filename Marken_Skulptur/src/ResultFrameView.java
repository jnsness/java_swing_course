import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ResultFrameView extends JFrame {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;

	public ResultFrameView() {

		setVisible(true);
		setSize(1200, 900);
		setResizable(false);
		setLocation(FormularWindow.frameLocation);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 415, 641);
		panel.setBorder(new TitledBorder(null, "Title", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 116, 70, 70, 70 };
		gbl_panel.rowHeights = new int[] { 20, 20, 20, 20, 20 };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Avg Distance (each other)");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JPanel panelRight = new JPanel();
		panelRight.setBounds(432, 0, 732, 641);
		panelRight.setBorder(new TitledBorder(null, "Image",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelRight);

		textField = new JTextField("");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblAvgDistanceTo = new JLabel("Avg Distance to you");
		GridBagConstraints gbc_lblAvgDistanceTo = new GridBagConstraints();
		gbc_lblAvgDistanceTo.anchor = GridBagConstraints.WEST;
		gbc_lblAvgDistanceTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvgDistanceTo.gridx = 2;
		gbc_lblAvgDistanceTo.gridy = 1;
		panel.add(lblAvgDistanceTo, gbc_lblAvgDistanceTo);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 5;
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		panel.add(table, gbc_table);
	}

}
