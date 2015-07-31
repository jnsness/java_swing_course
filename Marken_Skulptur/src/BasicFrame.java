import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BasicFrame extends JFrame {

	public static JFrame frame;
	public static JPanel dragPanel;

	public BasicFrame() {
		frame = new JFrame("Photo Album");
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		dragPanel = new JPanel();
		dragPanel.setLayout(null);
		frame.add(dragPanel, BorderLayout.CENTER);

		/*
		 * Is good create a Thread to manipulate Forms and Files. In this
		 * particular case an <b>invokeLater</b> is needed becaouse all Forms
		 * graphics operations needs to be elaborated after pending events are
		 * processed
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				loadLogos();
			}
		});
	}

	/**
	 * Generate names of files
	 */
	public static void loadLogos() {
		dragPanel.removeAll();

		// Array to give every image from folder

		String s = "images";
		File f = new File(s);
		File[] fa = f.listFiles();

		for (int i = 0; i <= 29; i++) {
			String fileName = String.valueOf(fa[i]);
			addNewLogo(fileName, i);
		}

		dragPanel.repaint();
	}

	public static void addNewLogo(String fileName, int i) {
		// Get resources from Directory or Jar file
		Image img = Toolkit.getDefaultToolkit().createImage(fileName);

		// Creates a draggableImageComponent and adds loaded image
		DraggableLogoComponent logo = new DraggableLogoComponent();
		dragPanel.add(logo);// Adds this component to main container
		logo.setImage(img);// Sets image
		logo.setAutoSize(true);// The component get ratio w/h of source image
		logo.setOverbearing(true);// On click ,this panel gains lowest z-buffer

		logo.setSize(50, 50);

//	Calculation for manual float design. Every Column can have 10 logos, then it will break to next column
		
		int columnWidht;
		if (i >= 0 && i <= 9) {
			columnWidht = 0*50;
		} 
		else if (i> 9 && i < 20)
		{
			columnWidht = 1* 50;
			i = i-10;
		}
		else {
			columnWidht = 2*50;
			i=i-20;
		}
			logo.setLocation(columnWidht, i*50);
			System.out.println(i);

		dragPanel.repaint();
	}
}