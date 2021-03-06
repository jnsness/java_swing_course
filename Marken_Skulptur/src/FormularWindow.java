import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Controller f�r Fomrular-Window. Instanziiert die die View
 * (FormularWindowsView) und l�dt danach den LogoFrame
 * 
 * @author Jnsness
 */

public class FormularWindow {

	public static Point frameLocation;
	public static String getSelectedItem;

	public FormularWindow() {

		FormularWindowView view = new FormularWindowView();

		view.btnWeiter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String introText = "Im n�chsten Fenster werden Sie eine Reihe von Rechtecken sehen.\nEin Rechteck ist gr�n und repr�sentiert Sie.\nDie Fl�che darunter hat einen Innen- und Au�enraum.\nOrdnen Sie alle Rechtecke ganz nach Ihrem Belieben an.\nDie Rechtecke k�nnen nah beieinander oder auch weit entfernt gesetzt werden.\nWenn Sie mit dem Anordnen fertig sind, klicken Sie bitte auf save-and-next";

				JOptionPane.showMessageDialog(view.frame, introText);

				// Set Location for new window to appear at the same point
				frameLocation = view.getFrame().getLocation();

				// old screen disappears
				view.getFrame().setVisible(false);

				// new LogoFrame will be instantiated. Data should be loaded
				// when "next" is clicked. Not before that moment
				new GenerateUUID();

				new DbAccess().DbSavePersonalData(GenerateUUID.id.toString(),
						view.txtName.getText(), view.txtNachname.getText(),
						view.txtBeruf.getText(), (Integer) view.getAgeSpinner()
								.getValue(), view.sexComboBox
								.getSelectedIndex());

				new LogoFrame(GenerateUUID.id.toString());

			}

		});
	}

}
