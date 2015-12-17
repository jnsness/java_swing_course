import javax.swing.SwingUtilities;


/**
 * 
 * @author Jnsness
 *Start-Klasse - instanziiert ein FormularWindow
 */

public class Application {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				
				new FormularWindow();
				
			}
		});
	}

}