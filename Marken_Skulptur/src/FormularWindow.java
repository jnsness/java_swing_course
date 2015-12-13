import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularWindow {

	public static Point frameLocation;
	public static String getSelectedItem;
	
	public FormularWindow() {

		FormularWindowView view = new FormularWindowView();
		
		view.btnWeiter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

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
