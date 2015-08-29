import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class FormularWindow {

	public static Point frameLocation;

	private JFrame frame;
	private static JComboBox catComboBox;
	private static JTextField txtName;
	private static JTextField txtNachname;
	private static JTextField txtBeruf;
	private static JSpinner ageSpinner;
	private static JComboBox sexComboBox;
	

	public FormularWindow() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnWeiter = new JButton("Next");
		btnWeiter.setBounds(634, 517, 128, 33);
		frame.getContentPane().add(btnWeiter);

		catComboBox = new JComboBox();
		catComboBox.setModel(new DefaultComboBoxModel(new String[] { "cars",
				"fashion" }));
		catComboBox.setBounds(200, 399, 128, 20);
		frame.getContentPane().add(catComboBox);



		txtName = new JTextField();
		txtName.setBounds(200, 42, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtNachname = new JTextField();
		txtNachname.setBounds(200, 73, 86, 20);
		frame.getContentPane().add(txtNachname);
		txtNachname.setColumns(10);

		txtBeruf = new JTextField();
		txtBeruf.setBounds(200, 104, 86, 20);
		frame.getContentPane().add(txtBeruf);
		txtBeruf.setColumns(10);

		ageSpinner = new JSpinner();
		ageSpinner.setModel(new SpinnerNumberModel(18, 1, 99, 1));
		ageSpinner.setBounds(200, 135, 86, 20);
		frame.getContentPane().add(ageSpinner);

		sexComboBox = new JComboBox();
		sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"Maennlich", "Weiblich"}));
		sexComboBox.setBounds(200, 175, 86, 20);
		frame.getContentPane().add(sexComboBox);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(86, 45, 71, 14);
		frame.getContentPane().add(lblVorname);

		JLabel lblNachname = new JLabel("Nachname");
		lblNachname.setBounds(86, 76, 86, 14);
		frame.getContentPane().add(lblNachname);

		JLabel lblBeruf = new JLabel("Beruf");
		lblBeruf.setBounds(86, 107, 46, 14);
		frame.getContentPane().add(lblBeruf);

		JLabel lblAlter = new JLabel("Alter");
		lblAlter.setBounds(86, 138, 46, 14);
		frame.getContentPane().add(lblAlter);
		
		JLabel lblGeschlecht = new JLabel("Geschlecht");
		lblGeschlecht.setBounds(86, 178, 86, 14);
		frame.getContentPane().add(lblGeschlecht);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(86, 402, 71, 14);
		frame.getContentPane().add(lblCategory);
		

		btnWeiter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// Set Location for new window to appear at the same point
				frameLocation = frame.getLocation();

				// old screen disappears
				frame.setVisible(false);

				// new LogoFrame will be instantiated. Data should be loaded
				// when "next" is clicked. Not before that moment
				new LogoFrame();

			}

		});
	}

	public static String getImageCategory() {
		String valueCatComboBox = (String) catComboBox.getSelectedItem();
		return valueCatComboBox;
	}

	public static JTextField getTxtName() {
		return txtName;
	}

	public static JTextField getTxtNachname() {
		return txtNachname;
	}

	public static JTextField getTxtBeruf() {
		return txtBeruf;
	}

	public static JSpinner getAgeSpinner() {
		return ageSpinner;
	}

	public static JComboBox getSexComboBox() {
		return sexComboBox;
	}
	
	
}
