import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class FormularWindow {

	private JFrame frame;
	private static JComboBox catComboBox;

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

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(86, 402, 46, 14);
		frame.getContentPane().add(lblCategory);

		btnWeiter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// old screen disappears
				frame.setVisible(false);

				// new LogoFrame will be instantiated. Data should be loaded
				// when "next" is clicked. Not before that moment
				new LogoFrame();

			}

		});
	}

	public static String collectValues() {
		String valueCatComboBox = (String) catComboBox.getSelectedItem();
		return valueCatComboBox;
	}

}