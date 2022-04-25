import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HowToPlayPanel extends JPanel{

	JButton btnMainMenu;
	BufferedImage instructionsImage;
	MainFrame mainFrame;
	
	HowToPlayPanel(MainFrame obj)
	{
		this.setSize(1300,650);
		this.setFocusable(true);
        this.requestFocusInWindow();
		this.mainFrame=obj;
		try {
			instructionsImage=ImageIO.read(getClass().getResource("/BackgroundImages/HowToPlay.png"));
		} catch (IOException e) {
				System.out.print("Couldnt laod how to play image");
		}
		this.addComponent();

	}
	
	public void addComponent()
	{
		this.btnMainMenu=new JButton();
		this.btnMainMenu.setBounds(50, 320, 210, 100);
		this.btnMainMenu.setBackground(Color.WHITE);
		this.btnMainMenu.setOpaque(false);
		this.btnMainMenu.setBorderPainted(false);
		this.btnMainMenu.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.howToPlayPanel.remove(mainFrame.howToPlayPanel.btnMainMenu);
				mainFrame.remove(mainFrame.howToPlayPanel);
				mainFrame.add(mainFrame.mainMenuPanel);
				mainFrame.mainMenuPanel.repaint();
			}
	
		});
	}
	
	public void paintComponent(Graphics g)
	{
		this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(this.btnMainMenu);
        g.drawImage(instructionsImage, 0, 0, null);
	}
	
}
