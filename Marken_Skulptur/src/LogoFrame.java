import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogoFrame extends JFrame {

	private static JPanel dragPanel;
	private JPanel buttonPanel;
	private JButton btnRedo;
	private JButton btnSave;
	public static ArrayList<DraggableLogoComponent> logolist = new ArrayList<DraggableLogoComponent>();
	
	public LogoFrame() {

		getContentPane().setLayout(new BorderLayout());

		setVisible(true);
		setSize(800, 600);
		setResizable(false);

		// Set Location to Location of previous Window
		setLocation(FormularWindow.frameLocation);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// dragPanel Configuration
		dragPanel = new JPanel();
		dragPanel.setLayout(null);
		dragPanel.setPreferredSize(new Dimension(600, 0));
		dragPanel.setBorder(BorderFactory.createEtchedBorder());

		// buttonPanel Configuration

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setPreferredSize(new Dimension(180, 0));
		buttonPanel.setBorder(BorderFactory.createEtchedBorder());

		btnRedo = new JButton();
		btnRedo.setText("Redo");
		buttonPanel.add(btnRedo);

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadLogos();
			}
		});

		btnSave = new JButton();
		btnSave.setText("Save");
		buttonPanel.add(btnSave);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetLocations();
				writeToCSV(logolist);
			}
		});

		// add Panels to Frame

		getContentPane().add(buttonPanel, BorderLayout.EAST);
		getContentPane().add(dragPanel, BorderLayout.WEST);

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

		String imageUrl = "images/" + FormularWindow.getImageCategory();
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
		DraggableLogoComponent logo = new DraggableLogoComponent(fileName);
		dragPanel.add(logo);// Adds this component to main container
		
		logolist.add(logo);
		
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

	public static void GetLocations() {
		for(DraggableLogoComponent i : logolist){
			Point location = i.getLocation();
			String name = i.getFileName();
		}
		
	}
	
	private static void writeToCSV(ArrayList<DraggableLogoComponent> logolist) {
		try
		{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("results/test.csv"), "UTF-8"));
			
			bw.write("first name, name, occupation, age, logos, location");
			bw.newLine();
			
			for (DraggableLogoComponent logo : logolist)
			{
				//Collect personal values from Formular Window
				StringBuffer oneLine = new StringBuffer();
				oneLine.append((String)FormularWindow.getTxtName().getText()); 
				oneLine.append(",");
				oneLine.append((String)FormularWindow.getTxtNachname().getText()); 
				oneLine.append(",");
				oneLine.append((String)FormularWindow.getTxtBeruf().getText()); 
				oneLine.append(",");
				oneLine.append((Integer)FormularWindow.getAgeSpinner().getValue());
				oneLine.append(",");
				oneLine.append((String)FormularWindow.getSexComboBox().getSelectedItem());
				oneLine.append(",");
				
				//Collect logo Name and location
				oneLine.append(logo.getFileName());
				oneLine.append(",");
				oneLine.append(logo.getLocation());
				bw.write(oneLine.toString());
				bw.newLine();
			}
			bw.flush();
            bw.close();
		}
		catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
		
	}

}