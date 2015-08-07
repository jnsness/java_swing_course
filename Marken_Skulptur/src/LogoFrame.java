import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class LogoFrame extends JFrame {

	private static JPanel dragPanel;
	private JPanel buttonPanel;
	private JButton btnRedo;
	private JButton btnSave;

	public LogoFrame() {
		
		getContentPane().setLayout(new BorderLayout());
		
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		
//		Set Location to Location of previous Window
		setLocation(FormularWindow.frameLocation);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
// dragPanel Configuration
		dragPanel = new JPanel();
		dragPanel.setLayout(null);
		dragPanel.setPreferredSize(new Dimension(600,0));
		dragPanel.setBorder(BorderFactory.createEtchedBorder());
		
//		buttonPanel Configuration
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension(180,0));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		
		btnRedo = new JButton();
		btnRedo.setText("Redo");
		buttonPanel.add(btnRedo);
		
		btnSave = new JButton();
		btnSave.setText("Save");
		buttonPanel.add(btnSave);
		

		
//		add Panels to Frame
		
		getContentPane().add(buttonPanel, BorderLayout.EAST);
		getContentPane().add(dragPanel, BorderLayout.WEST);
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadLogos();
			}
		});

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

		String imageUrl = "images/" + FormularWindow.collectValues();
		System.out.println(imageUrl);
		File f = new File(imageUrl);
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

		// Calculation for manual float design. Every Column can have 10 logos,
		// then it will break to next column

		int columnWidht;
		if (i >= 0 && i <= 9) {
			columnWidht = 0 * 50;
		} else if (i > 9 && i < 20) {
			columnWidht = 1 * 50;
			i = i - 10;
		} else {
			columnWidht = 2 * 50;
			i = i - 20;
		}
		logo.setLocation(columnWidht, i * 50);

		dragPanel.repaint();
	}
}