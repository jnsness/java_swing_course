import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;


public class FormWindow extends JFrame {
	private JTextField txtName;
	private JTextField txtNachname;
	public FormWindow() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridwidth = 2;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		getContentPane().add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setText("Vorname");
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 2;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 4;
		gbc_txtName.gridy = 1;
		getContentPane().add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		txtNachname = new JTextField();
		txtNachname.setText("Nachname");
		GridBagConstraints gbc_txtNachname = new GridBagConstraints();
		gbc_txtNachname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNachname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNachname.gridx = 7;
		gbc_txtNachname.gridy = 1;
		getContentPane().add(txtNachname, gbc_txtNachname);
		txtNachname.setColumns(10);
		
		JLabel lblGeschlecht = new JLabel("Geschlecht");
		GridBagConstraints gbc_lblGeschlecht = new GridBagConstraints();
		gbc_lblGeschlecht.anchor = GridBagConstraints.EAST;
		gbc_lblGeschlecht.gridwidth = 2;
		gbc_lblGeschlecht.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeschlecht.gridx = 1;
		gbc_lblGeschlecht.gridy = 2;
		getContentPane().add(lblGeschlecht, gbc_lblGeschlecht);
		
		JRadioButton rdbtnMnnlich = new JRadioButton("M\u00E4nnlich");
		GridBagConstraints gbc_rdbtnMnnlich = new GridBagConstraints();
		gbc_rdbtnMnnlich.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMnnlich.gridwidth = 3;
		gbc_rdbtnMnnlich.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMnnlich.gridx = 4;
		gbc_rdbtnMnnlich.gridy = 2;
		getContentPane().add(rdbtnMnnlich, gbc_rdbtnMnnlich);
		
		JRadioButton rdbtnWeiblich = new JRadioButton("weiblich");
		GridBagConstraints gbc_rdbtnWeiblich = new GridBagConstraints();
		gbc_rdbtnWeiblich.anchor = GridBagConstraints.WEST;
		gbc_rdbtnWeiblich.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnWeiblich.gridx = 7;
		gbc_rdbtnWeiblich.gridy = 2;
		getContentPane().add(rdbtnWeiblich, gbc_rdbtnWeiblich);
		
		JLabel lblAlter = new JLabel("Alter");
		GridBagConstraints gbc_lblAlter = new GridBagConstraints();
		gbc_lblAlter.anchor = GridBagConstraints.EAST;
		gbc_lblAlter.gridwidth = 2;
		gbc_lblAlter.insets = new Insets(0, 0, 5, 5);
		gbc_lblAlter.gridx = 1;
		gbc_lblAlter.gridy = 3;
		getContentPane().add(lblAlter, gbc_lblAlter);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(18, 1, 99, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.anchor = GridBagConstraints.WEST;
		gbc_spinner.gridwidth = 2;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 4;
		gbc_spinner.gridy = 3;
		getContentPane().add(spinner, gbc_spinner);
		
		JButton btnWeiter = new JButton("Weiter");
		GridBagConstraints gbc_btnWeiter = new GridBagConstraints();
		gbc_btnWeiter.insets = new Insets(0, 0, 5, 5);
		gbc_btnWeiter.gridx = 10;
		gbc_btnWeiter.gridy = 8;
		getContentPane().add(btnWeiter, gbc_btnWeiter);
	}

}