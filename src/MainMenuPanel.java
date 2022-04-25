import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.JButton;


public class MainMenuPanel extends JPanel{


	private JButton btnPlay;
	private JButton btnExit;
	private JButton btnHowToPlay;
	BufferedImage backgroundImage;
	MainFrame mainFrame;
	
	public MainMenuPanel(MainFrame obj)
	{	
		try {
			backgroundImage= ImageIO.read(getClass().getResource("/BackgroundImages/MainMenu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.mainFrame=obj;
		this.setFocusable(true);
    	this.requestFocusInWindow();
		this.setSize(1300,650);
		this.setLayout(null);
		this.addComponents();
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(backgroundImage, 0, 0, 1300, 650, 0, 0, 1300, 650, null);
	}
	
	void addComponents()
	{
		this.btnPlay=new JButton();	 
		this.btnPlay.setBackground(Color.WHITE);
		this.btnPlay.setOpaque(false);
		this.btnPlay.setBorderPainted(false);
		this.btnPlay.setBounds(490, 242,350, 50);
		this.add(btnPlay);
		this.btnPlay.addActionListener(new PlayGameInterface(this.mainFrame));
		
		this.btnExit=new JButton();
		this.btnExit.setBackground(Color.WHITE);
		this.btnExit.setOpaque(false);
		this.btnExit.setBorderPainted(false);
		this.btnExit.setBounds(392, 518, 550, 50);
		this.add(btnExit);
		this.btnExit.addActionListener(new ActionListener ()
				{
					public void actionPerformed(ActionEvent arg0) {
						 System.exit(0);
					}
			
				});
		
		this.btnHowToPlay=new JButton();	 
		this.btnHowToPlay.setBackground(Color.WHITE);
		this.btnHowToPlay.setOpaque(false);
		this.btnHowToPlay.setBorderPainted(false);
		this.btnHowToPlay.setBounds(460, 380,400, 50);
		this.add(btnHowToPlay);
		this.btnHowToPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				mainFrame.remove(mainFrame.mainMenuPanel);
				mainFrame.add(mainFrame.howToPlayPanel);
				mainFrame.howToPlayPanel.repaint();
			}
			
		});
	}
}
