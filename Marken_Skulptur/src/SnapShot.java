import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * Hilfsklasse - kann angesteuert werden um LogoFrame als Bild abzuspeichern. Wird von "Weiter" Button angesteuert
 * 
 * @author Jnsness
 *
 */

public class SnapShot extends LogoFrameView {

	public SnapShot() {
	}

	int getSnapShot(JPanel panel, String uuid) {
		BufferedImage bufImg = new BufferedImage(panel.getSize().width,
				panel.getSize().height, BufferedImage.TYPE_INT_RGB);
		panel.paint(bufImg.createGraphics());
		File imageFile = new File("snapshots/" + uuid + ".jpg");
		try {
			imageFile.createNewFile();
			ImageIO.write(bufImg, "jpeg", imageFile);
		} catch (Exception ex) {

		}
		return 1;
	}

}
