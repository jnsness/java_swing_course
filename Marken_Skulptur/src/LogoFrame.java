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
	public ArrayList<DraggableLogoComponent> logolist;
	public static DraggableLogoComponent YouLogo;

	public LogoFrame(String uuid) {

		logolist = new ArrayList<DraggableLogoComponent>();
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
				loadYouImage();
			}
		});

		btnSave = new JButton();
		btnSave.setBounds(124, 818, 129, 42);
		btnSave.setText("Save and Next");
		buttonPanel.add(btnSave);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

				new DbAccess().DbSaveTimeData(uuid,
						elapsedTime.setEndTime(System.currentTimeMillis()));
				new DbAccess().DbSaveLogoData(uuid, logolist);
				new DbAccess().DbCalculateDistances(uuid);
				
				

				Point gravityPoint = new DbAccess()
						.DbCalculateCentreOfGravity(uuid);

				dPanel.setPaintControl(1);
				dPanel.setLogolist(logolist);
				dPanel.setGravityPoint(gravityPoint);

				new SnapShot().getSnapShot(dPanel, uuid);
				new ResultFrame(uuid);

				
				new DbAccess().DbCalculateAVGDistanceFromYou(uuid);
				new DbAccess().DbCalculateDistanceFromCentreOfGravity(uuid, gravityPoint);
				
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
				loadYouImage();
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

		for (int i = 0; i <= 9; i++) {
			String fileName = String.valueOf(fa[i]);
			addNewLogo(fileName);
		}

	}

	public void addNewLogo(String fileName) {
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

		if (fileName.equals("images/you_point.png")) {
			YouLogo = logo;
			logo.setSize(125, 125);
		}

	}

	public void loadYouImage() {
		// Load "youLogo" | Personal image

		String urlYouLogo = "images/you_point.png";
		addNewLogo(urlYouLogo);

		// First added to logoList to benefit from Method "addnewLogo" functions
		// - then deleted it to not get written to DB with the arraylist
		logolist.remove(logolist.size() - 1);

	}

	public static DraggableLogoComponent getYouLogo() {
		return YouLogo;
	}

}