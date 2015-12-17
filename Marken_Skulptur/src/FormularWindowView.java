import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;

/**
 * View des FormularWindows - beinhaltet viele Textflächen, Buttons und natürlich Getters
 * @author Jnsness
 *
 */
public class FormularWindowView {

	private JFrame frame;
	public static JComboBox catComboBox;
	public JTextField txtName;
	public JTextField txtNachname;
	public JTextField txtBeruf;
	public JSpinner ageSpinner;
	public JComboBox sexComboBox;
	public JTextPane txtpnWillkommenImMarkenbewertungstool;
	public JButton btnWeiter;

	public FormularWindowView() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 86, 86, 154, 140, 140, 30,
				128, 20 };
		gridBagLayout.rowHeights = new int[] { 30, 20, 20, 20, 20, 20, 30, 20,
				30, 30, 33, 20, 30, 30, 30, 30, 30, 30 };
		frame.getContentPane().setLayout(gridBagLayout);

		// Labels

		JLabel lblVorname = new JLabel("Vorname");
		GridBagConstraints gbc_lblVorname = new GridBagConstraints();
		gbc_lblVorname.anchor = GridBagConstraints.NORTH;
		gbc_lblVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVorname.insets = new Insets(0, 0, 5, 5);
		gbc_lblVorname.gridx = 1;
		gbc_lblVorname.gridy = 1;
		frame.getContentPane().add(lblVorname, gbc_lblVorname);

		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.NORTH;
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 1;
		frame.getContentPane().add(txtName, gbc_txtName);
		txtName.setColumns(10);

		txtpnWillkommenImMarkenbewertungstool = new JTextPane();
		txtpnWillkommenImMarkenbewertungstool.setEditable(false);
		txtpnWillkommenImMarkenbewertungstool
				.setText("Willkommen im Markenbewertungs-Tool der Hochschule Mainz!\r\n\r\nBitte tragen Sie hier Ihre pers\u00F6nlichen Daten ein. \r\n\r\nJe nach Kategorie werden Sie auf dem n\u00E4chsten Bildschirm verschiedene Logos sehen.\r\n\r\nSoweit Sie soweit sind - dr\u00FCcken Sie auf \"next\"");
		GridBagConstraints gbc_txtpnWillkommenImMarkenbewertungstool = new GridBagConstraints();
		gbc_txtpnWillkommenImMarkenbewertungstool.gridwidth = 3;
		gbc_txtpnWillkommenImMarkenbewertungstool.gridheight = 6;
		gbc_txtpnWillkommenImMarkenbewertungstool.insets = new Insets(0, 0, 5,
				5);
		gbc_txtpnWillkommenImMarkenbewertungstool.fill = GridBagConstraints.BOTH;
		gbc_txtpnWillkommenImMarkenbewertungstool.gridx = 4;
		gbc_txtpnWillkommenImMarkenbewertungstool.gridy = 1;
		frame.getContentPane().add(txtpnWillkommenImMarkenbewertungstool,
				gbc_txtpnWillkommenImMarkenbewertungstool);

		JLabel lblNachname = new JLabel("Nachname");
		GridBagConstraints gbc_lblNachname = new GridBagConstraints();
		gbc_lblNachname.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNachname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNachname.gridx = 1;
		gbc_lblNachname.gridy = 2;
		frame.getContentPane().add(lblNachname, gbc_lblNachname);

		txtNachname = new JTextField();
		GridBagConstraints gbc_txtNachname = new GridBagConstraints();
		gbc_txtNachname.anchor = GridBagConstraints.NORTH;
		gbc_txtNachname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNachname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNachname.gridx = 2;
		gbc_txtNachname.gridy = 2;
		frame.getContentPane().add(txtNachname, gbc_txtNachname);
		txtNachname.setColumns(10);

		JLabel lblBeruf = new JLabel("Beruf");
		GridBagConstraints gbc_lblBeruf = new GridBagConstraints();
		gbc_lblBeruf.anchor = GridBagConstraints.WEST;
		gbc_lblBeruf.insets = new Insets(0, 0, 5, 5);
		gbc_lblBeruf.gridx = 1;
		gbc_lblBeruf.gridy = 3;
		frame.getContentPane().add(lblBeruf, gbc_lblBeruf);

		txtBeruf = new JTextField();
		GridBagConstraints gbc_txtBeruf = new GridBagConstraints();
		gbc_txtBeruf.anchor = GridBagConstraints.NORTH;
		gbc_txtBeruf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBeruf.insets = new Insets(0, 0, 5, 5);
		gbc_txtBeruf.gridx = 2;
		gbc_txtBeruf.gridy = 3;
		frame.getContentPane().add(txtBeruf, gbc_txtBeruf);
		txtBeruf.setColumns(10);

		JLabel lblAlter = new JLabel("Alter");
		GridBagConstraints gbc_lblAlter = new GridBagConstraints();
		gbc_lblAlter.anchor = GridBagConstraints.WEST;
		gbc_lblAlter.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlter.gridx = 1;
		gbc_lblAlter.gridy = 4;
		frame.getContentPane().add(lblAlter, gbc_lblAlter);

		ageSpinner = new JSpinner();
		ageSpinner.setModel(new SpinnerNumberModel(30, 1, 99, 1));
		GridBagConstraints gbc_ageSpinner = new GridBagConstraints();
		gbc_ageSpinner.anchor = GridBagConstraints.NORTHWEST;
		gbc_ageSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_ageSpinner.gridx = 2;
		gbc_ageSpinner.gridy = 4;
		frame.getContentPane().add(ageSpinner, gbc_ageSpinner);

		JLabel lblGeschlecht = new JLabel("Geschlecht");
		GridBagConstraints gbc_lblGeschlecht = new GridBagConstraints();
		gbc_lblGeschlecht.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGeschlecht.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeschlecht.gridx = 1;
		gbc_lblGeschlecht.gridy = 5;
		frame.getContentPane().add(lblGeschlecht, gbc_lblGeschlecht);

		sexComboBox = new JComboBox();
		sexComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Weiblich", "Maennlich" }));
		GridBagConstraints gbc_sexComboBox = new GridBagConstraints();
		gbc_sexComboBox.anchor = GridBagConstraints.NORTHWEST;
		gbc_sexComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_sexComboBox.gridx = 2;
		gbc_sexComboBox.gridy = 5;
		frame.getContentPane().add(sexComboBox, gbc_sexComboBox);

		JLabel lblCategory = new JLabel("Category");
		GridBagConstraints gbc_lblCategory = new GridBagConstraints();
		gbc_lblCategory.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategory.gridx = 1;
		gbc_lblCategory.gridy = 9;
		frame.getContentPane().add(lblCategory, gbc_lblCategory);

		catComboBox = new JComboBox();
		catComboBox.setModel(new DefaultComboBoxModel(new String[] { "cars",
				"fashion" }));
		GridBagConstraints gbc_catComboBox = new GridBagConstraints();
		gbc_catComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_catComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_catComboBox.gridx = 2;
		gbc_catComboBox.gridy = 9;
		frame.getContentPane().add(catComboBox, gbc_catComboBox);

		btnWeiter = new JButton("Next");
		GridBagConstraints gbc_btnWeiter = new GridBagConstraints();
		gbc_btnWeiter.gridheight = 2;
		gbc_btnWeiter.gridwidth = 2;
		gbc_btnWeiter.insets = new Insets(0, 0, 0, 5);
		gbc_btnWeiter.fill = GridBagConstraints.BOTH;
		gbc_btnWeiter.gridx = 5;
		gbc_btnWeiter.gridy = 16;
		frame.getContentPane().add(btnWeiter, gbc_btnWeiter);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JComboBox getCatComboBox() {
		return catComboBox;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtNachname() {
		return txtNachname;
	}

	public JTextField getTxtBeruf() {
		return txtBeruf;
	}

	public JSpinner getAgeSpinner() {
		return ageSpinner;
	}

	public JComboBox getSexComboBox() {
		return sexComboBox;
	}

	public JTextPane getTxtpnWillkommenImMarkenbewertungstool() {
		return txtpnWillkommenImMarkenbewertungstool;
	}

	public void setTxtpnWillkommenImMarkenbewertungstool(
			JTextPane txtpnWillkommenImMarkenbewertungstool) {
		this.txtpnWillkommenImMarkenbewertungstool = txtpnWillkommenImMarkenbewertungstool;
	}

	public static String getImageCategory() {
		String valueCatComboBox = (String)catComboBox.getSelectedItem();
		System.out.println(valueCatComboBox);
		return valueCatComboBox;
	}

}
