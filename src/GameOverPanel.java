import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameOverPanel extends JPanel{

	MainFrame mainFrame;
	BufferedImage surrenderImage;
	BufferedImage gameOverImage;
	JButton mainMenuBtn;
	int robotX=1300;
	int paintDuration;
	
	GameOverPanel(MainFrame mainFrame)
	{
		this.setSize(1300,650);
		this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
		this.mainFrame=mainFrame;
		try {
			this.surrenderImage=ImageIO.read(getClass().getResource("/ActorImages/SurrenderImage.png"));
			this.gameOverImage=ImageIO.read(getClass().getResource("/BackgroundImages/GameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addComponent();
	}
	
	void addComponent()
	{
		this.mainMenuBtn=new JButton();
		this.mainMenuBtn.setBounds(1000, 280, 250, 100);
		this.mainMenuBtn.setBackground(Color.WHITE);
		this.mainMenuBtn.setOpaque(false);
		this.mainMenuBtn.setBorderPainted(false);
		this.add(mainMenuBtn);
		this.mainMenuBtn.addActionListener(new ActionListener () {
			
			public void actionPerformed(ActionEvent arg0) {
					mainFrame.playPanel.returnToMainMenu=true;
			}
			
		});
	}
	
	public void paintComponent (Graphics g)
	{
		this.paintDuration+=1;
		g.setFont( new Font( "Monospaced", Font.BOLD, 50 ) );
		super.paintComponent(g);
		
		g.drawImage(mainFrame.playPanel.backgroundImage[mainFrame.playPanel.backgroundFrameNo], 0, 0, mainFrame.playPanel.currentImgDestX2, mainFrame.playPanel.getHeight(), mainFrame.playPanel.currentImgSrcX1, 0, 1300, 650, null);	
		g.drawImage(mainFrame.playPanel.backgroundImage[mainFrame.playPanel.backgroundFrameNo+1], mainFrame.playPanel.nextImgDestX1, 0, this.getWidth(), this.getHeight(), 0, 0, mainFrame.playPanel.nextImgSrcX2, 650, null);
		g.drawImage(this.gameOverImage, 0, 0, null);
		g.drawString(String.format("%.01f", mainFrame.playPanel.score), 490, 320);
		g.drawString(String.format("%.01f", mainFrame.playPanel.highscore), 490, 520);
		
		
		if(mainFrame.playPanel.actor.x!=10&&mainFrame.playPanel.actor.y!=280)
			 mainFrame.playPanel.actor.drawActorImage(g);
		else g.drawImage(this.surrenderImage, 10, 350, null);
		
		if(this.robotX>=330)
		{
			g.drawImage(mainFrame.playPanel.enemy.greenRobotImage[1], robotX, 270, null);
			robotX-=30;
		}
		else g.drawImage(mainFrame.playPanel.enemy.greenRobotImage[1], robotX, 270, null);
	}
	
}
