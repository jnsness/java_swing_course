import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class LogoFrameView extends JPanel {

	public String mousePosition;
	private int PaintControl = 0;
	private ArrayList<DraggableLogoComponent> logolist;
	private Point gravityPoint;

	public LogoFrameView() {

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				mousePosition = e.getPoint().toString();
				setToolTipText(mousePosition);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.GRAY);
		g.drawLine(90, 90, 90, 780);
		g.drawLine(90, 780, 810, 780);
		g.drawLine(810, 780, 810, 90);
		g.drawLine(810, 90, 90, 90);

		if (PaintControl == 1) {
			for (DraggableLogoComponent logo : logolist) {
				g.drawLine((int)logo.getCentrePoint().getX(),(int)logo.getCentrePoint().getY(), (int) gravityPoint.getX(),
						(int) gravityPoint.getY());
				g.drawLine((int)LogoFrame.YouLogo.getCentrePoint().getX(), (int)LogoFrame.YouLogo.getCentrePoint().getY(), (int) gravityPoint.getX(),
						(int) gravityPoint.getY());
			}

		}

	}

	public void setPaintControl(int paintControl) {
		PaintControl = paintControl;
	}

	public void setLogolist(ArrayList<DraggableLogoComponent> logolist) {
		this.logolist = logolist;
	}

	public void setGravityPoint(Point gravityPoint) {
		this.gravityPoint = gravityPoint;
	}

}
