import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;


public class LogoFrameView extends JPanel {

	public String mousePosition;
	
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

    }
	
}