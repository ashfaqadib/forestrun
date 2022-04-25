import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Point;
import javax.swing.JFrame;
import javax.imageio.ImageIO;

public class MainFrame extends JFrame{
	
	PlayPanel playPanel;
	MainMenuPanel mainMenuPanel;
	BufferedImage cursorImg;
	PlayGameInterface playGameInterface;
	GameOverPanel gameOverPanel;
	HowToPlayPanel howToPlayPanel;
	
	

	public MainFrame ()
	
	{
		GameSound.music();
		try {
			cursorImg = ImageIO.read(getClass().getResource("/BackgroundImages/Cursor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 File file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Forest Run");
	     if (!file.exists()) {
	            if (file.mkdir()) {
	                //System.out.println("Directory is created!");
	            } else {
	               // System.out.println("Failed to create directory!");
	            }
	        }
		
		
		Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");	
		this.getContentPane().setCursor(customCursor);
		
		this.setSize(1300, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)	;
		this.setResizable(false);
		
		
		
		this.playPanel= new PlayPanel(this);
		this.mainMenuPanel=new MainMenuPanel(this);
		this.gameOverPanel=new GameOverPanel(this);
		this.howToPlayPanel=new HowToPlayPanel(this);
		this.add(mainMenuPanel);
	}
	
}