import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoFrame extends JFrame {

	private TimeCounter elapsedTime;
	private LogoFrameView dPanel;
	private JPanel buttonPanel;
	private JButton btnRedo;
	private JButton btnSave;
	public static ArrayList<DraggableLogoComponent> logolist = new ArrayList<DraggableLogoComponent>();
	public static JLabel lblYou;
	
	
	public LogoFrame(String uuid) {

		elapsedTime = new TimeCounter();
		getContentPane().setLayout(new BorderLayout());

		setVisible(true);
		setSize(1200, 900);
		setResizable(false);

		// Set Location to Location of previous Window
		setLocation(FormularWindow.frameLocation);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// dragPanel Configuration
		dPanel = new LogoFrameView();
		dPanel.setLayout(null);
		dPanel.setPreferredSize(new Dimension(900, 0));
		dPanel.setBorder(BorderFactory.createEtchedBorder());

		// buttonPanel Configuration

		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(290, 0));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());
		buttonPanel.setLayout(null);

		btnRedo = new JButton();
		btnRedo.setBounds(39, 819, 75, 41);
		btnRedo.setText("Shuffle");
		buttonPanel.add(btnRedo);

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logolist.clear();
				loadLogos();

			}
		});

		btnSave = new JButton();
		btnSave.setBounds(124, 818, 129, 42);
		btnSave.setText("Save and Next");
		buttonPanel.add(btnSave);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				GetLocations();
				new SnapShot().getSnapShot(dPanel, uuid);
				new DbAccess().DbSaveTimeData(uuid,
						elapsedTime.setEndTime(System.currentTimeMillis()));
				new DbAccess().DbSaveLogoData(uuid, logolist);
				new DbAccess().DbCalculateDistances(uuid);
				new ResultFrame(uuid);
				new DbAccess().DbCalculateAVGDistanceFromYou(uuid);
			}
		});

		// add Panels to Frame

		getContentPane().add(buttonPanel, BorderLayout.EAST);
		getContentPane().add(dPanel, BorderLayout.WEST);

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
	public void loadLogos() {
		dPanel.removeAll();

		// Array to give every image from folder

		String imageUrl = "images/" + FormularWindow.getImageCategory();
		File f = new File(imageUrl);
		File[] fa = f.listFiles();

		for (int i = 0; i <= 10; i++) {
			String fileName = String.valueOf(fa[i]);
			addNewLogo(fileName, i);
		}

		// Load "youLogo" | Personal image
		ImageIcon icon = new ImageIcon("images/you_point.png");
		lblYou = new JLabel("You");
		lblYou.setBounds(425, 399, 90, 90);
		lblYou.setIcon(icon);
		dPanel.add(lblYou);
		dPanel.repaint();

	}

	public void addNewLogo(String fileName, int i) {
		// Get resources from Directory or Jar file
		Image img = Toolkit.getDefaultToolkit().createImage(fileName);

		// Creates a draggableImageComponent and adds loaded image
		DraggableLogoComponent logo = new DraggableLogoComponent(fileName);
		dPanel.add(logo);// Adds this component to main container

		logolist.add(logo);

		logo.setImage(img);// Sets image
		logo.setAutoSize(true);// The component get ratio w/h of source image
		logo.setOverbearing(true);// On click ,this panel gains lowest z-buffer

		logo.setSize(75, 75);

		// randomize location of logos
		logo.setLocation((int) (Math.random() * 830),
				(int) (Math.random() * 810));

		dPanel.repaint();

	}

	public static void GetLocations() {
		for (DraggableLogoComponent i : logolist) {
			Point location = i.getLocation();
			String name = i.getFileName();
		}

	}

	public static JLabel getLblYou() {
		return lblYou;
	}

	public void setLblYou(JLabel lblYou) {
		this.lblYou = lblYou;
	}

	
	
}