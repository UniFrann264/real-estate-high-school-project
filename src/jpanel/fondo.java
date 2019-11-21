package jpanel;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class fondo extends JPanel {
	public fondo() {
	}
	
	/**
	 * Create the panel.
	 */

	private URL url = getClass().getResource("/img/fondo.jpg");
	private Image image = new ImageIcon(url).getImage();

	@Override
    public void paint(Graphics g){
		
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
        
    }
	
}
