import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Rectangle;


public class PlayPanel extends JPanel{

	Actor actor;
	Enemy enemy;
	BufferedImage backgroundImage[]=new BufferedImage[57];
	BufferedImage pauseImage;
	Random rnd = new Random();
	Rectangle cursor=new Rectangle(0,0,20,20);
	Rectangle playerRect,enemyRect,rockRect,shootRect,killerRect;
	double theta;
	double score=1;
	double highscore=0;
	MainFrame mainFrame;
	JButton mainMenuBtn;
	JButton exitBtn;
	
	String backgorundImagePath;
	
	int currentImgDestX2,currentImgSrcX1,backgroundFrameNo=1;
	int nextImgDestX1, nextImgSrcX2;
	int randomNo;
	int spawnDelay=0;
	int noOfShots=0;
	boolean gameOver=false;
	boolean returnToMainMenu=false;
	boolean paused=false;
	int rockSpawnDelay=0;
	int robotSpawnDelay=0;
	int killerSpawnDelay=0;
	
	public PlayPanel (MainFrame mainFrame){
		
		this.setSize(1300,650);
		this.setFocusable(true);
        this.requestFocusInWindow();
        this.mainFrame=mainFrame;
        
		this.backgorundImagePath="/BackgroundImages/Background";
		actor=new Actor(this);
		enemy=new Enemy(this);
		
		try {
			this.pauseImage=ImageIO.read(getClass().getResource("/BackgroundImages/PauseImage.png"));
			for(int i=1;i<=56;i++)
			{
				this.backgroundImage[i]=ImageIO.read(getClass().getResource(this.backgorundImagePath+i+".png"));
			}
		} catch (IOException e) {			
			System.out.println("Can not load Image");
			e.printStackTrace();			
		}
		currentImgDestX2=this.getWidth();
		nextImgDestX1=this.getWidth();
		theta=0;
		this.addComponents();
		this.addLisToPanel();
	
	}
	
	private void addComponents() {
		
		this.mainMenuBtn=new JButton("");
		this.mainMenuBtn.setBounds(220,300,850,50);		
		this.mainMenuBtn.setBackground(Color.WHITE);
		this.mainMenuBtn.setOpaque(false);
		this.mainMenuBtn.setBorderPainted(false);
		
		this.exitBtn=new JButton("");
		this.exitBtn.setBounds(300,470,670,50);	
		this.exitBtn.setBackground(Color.WHITE);
		this.exitBtn.setOpaque(false);
		this.exitBtn.setBorderPainted(false);
		
		this.mainMenuBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				returnToMainMenu=true;
			}
			
		});
		this.exitBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				 System.exit(0);
			}
			
		});
		
	}

	void setDefaultvalues()
	{
		actor.x=10;	actor.y=280;
		actor.actorFrameNo=1;	actor.somerSaultFrameNo=1;
		enemy.enemyX=0;		enemy.enemyY=350;
		actor.walked=15;	actor.changeConst=1285;
		enemy.walked=0;
		enemy.runningRobotFrameNo=1;
		enemy.shootX=1000;
		currentImgDestX2=this.getWidth();
		nextImgDestX1=this.getWidth();
		currentImgSrcX1=0; nextImgSrcX2=0;
		actor.jumped=0;actor.somerSaulted=0;
		enemy.enemyX=this.getWidth();	enemy.enemyY=200;
		enemy.rockX=1300;	enemy.circleX=1300;
		this.gameOver=false;		this.paused=false;
		enemy.drawRock=false;	enemy.drawRobot=false;		enemy.drawShoot=false;
		this.score=1;	this.noOfShots=0;
		this.backgroundFrameNo=1;
		mainFrame.gameOverPanel.robotX=1300;
		this.remove(mainMenuBtn);
		this.remove(exitBtn);
		this.rockSpawnDelay=0;
		this.robotSpawnDelay=0;
		this.killerSpawnDelay=0;
		enemy.killerX=1300;
		enemy.drawKiller=false;
		enemy.killerRobotFrameNo=1;
		mainFrame.gameOverPanel.paintDuration=0;
	}
	
	
	private void addLisToPanel() {
		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if((code==KeyEvent.VK_UP||code==KeyEvent.VK_W)&&paused==false){
					if(actor.y==280&&actor.x==10&&paused==false) 
					{
						GameSound.jump();
						actor.y-=60;
					}
					
				}else if((code==KeyEvent.VK_DOWN||code==KeyEvent.VK_S)&&paused==false){
					if(actor.x==10&&actor.y==280) 
						{
						actor.y+=90;actor.x+=60;
						GameSound.dive();
						}
					
				}else if(code == KeyEvent.VK_ESCAPE){
					if(paused==false) 
						{
						paused=true;
						mainFrame.playPanel.add(mainMenuBtn);
						mainFrame.playPanel.add(exitBtn);
						}
					else {
						mainFrame.playPanel.remove(mainMenuBtn);
						mainFrame.playPanel.remove(exitBtn);
						paused=false;
					}
					
				}
			}
		});
		
		this.addMouseListener(new MouseAdapter(){
			
       	 public void mousePressed(MouseEvent e) {
       		if(e.getButton()==MouseEvent.BUTTON1)
       		{
       		if(actor.x==10&&actor.y==280&&actor.actorShoot==false&&paused==false&&gameOver==false){
       		GameSound.gunShot();
       		actor.actorShoot=true;
       		cursor.setLocation(e.getX(),e.getY());
       		}
       		}
        }  
		
	});
	}
	
	public void paintComponent(Graphics g)
		{
		if(this.paused==false){
		if(gameOver==false){
			g.setFont( new Font( "Monospaced", Font.BOLD, 30 ) );
			score+=0.1;
			randomNo=rnd.nextInt(10);
			if(!enemy.drawKiller&&!enemy.drawRobot)
			{
				this.killerSpawnDelay+=this.randomNo;
				if(killerSpawnDelay>=300&&!enemy.drawRobot)
				{
					GameSound.killerEntry();
					this.robotSpawnDelay=0;
					this.rockSpawnDelay=0;
					this.killerSpawnDelay=0;
					enemy.drawKiller=true;
					enemy.killerX=1300;
				}
			}
			if(!enemy.drawRock)
			{
				this.rockSpawnDelay+=this.randomNo;
				if(this.rockSpawnDelay>=100){
						enemy.rockX=1300;
						enemy.drawRock=true;
						this.rockSpawnDelay=0;
				}
			}
			randomNo=rnd.nextInt(10);
			if(!enemy.drawRobot&&!enemy.drawKiller)
			{
				this.robotSpawnDelay+=this.randomNo;
				if(this.robotSpawnDelay>=300&&!enemy.drawKiller)
				{
					GameSound.robotEntry();
					this.killerSpawnDelay=0;
					enemy.enemyX=1300;
					enemy.drawRobot=true;
					this.robotSpawnDelay=0;
				}
			}
			
			super.paintComponent(g);
			
		
			g.drawImage(backgroundImage[backgroundFrameNo], 0, 0, this.currentImgDestX2, this.getHeight(), currentImgSrcX1, 0, 1300, 650, null);	
			g.drawImage(backgroundImage[backgroundFrameNo+1], nextImgDestX1, 0, this.getWidth(), this.getHeight(), 0, 0, nextImgSrcX2, 650, null);

			actor.drawActorImage(g);
			enemy.drawEnemy(g);
			
			playerRect=new Rectangle(actor.x+140,actor.y,actor.actorImage[actor.actorFrameNo].getWidth()-200,actor.actorImage[actor.actorFrameNo].getHeight());			
			rockRect= new Rectangle(enemy.rockX,500,enemy.runningRobotImage[1].getWidth(),enemy.runningRobotImage[1].getHeight());			
			enemyRect= new Rectangle(enemy.enemyX,enemy.enemyY,enemy.greenRobotImage[1].getWidth()-50,enemy.greenRobotImage[1].getHeight()-50);
			shootRect= new Rectangle(enemy.shootX,280,enemy.greenRobotShootImage.getWidth(),enemy.greenRobotShootImage.getHeight());
			killerRect = new Rectangle(enemy.killerX,280,enemy.killerRobotImage[enemy.killerRobotFrameNo].getWidth(),enemy.killerRobotImage[enemy.killerRobotFrameNo].getHeight());
	
			
			if(enemyRect.intersects(cursor)||killerRect.intersects(cursor)) {
				if(enemyRect.intersects(cursor)) GameSound.robotGotShot();
				else GameSound.killerGotShot();
				noOfShots++;
			}
			cursor.setLocation(0,0);
			
			if(rockRect.intersects(playerRect)||shootRect.intersects(playerRect)||killerRect.intersects(playerRect)) 
				{
				GameSound.gameOver();
				this.actor.x=10;
				this.actor.y=280;
				this.actor.y+=90;
				this.actor.x+=60;
				
				try {
					String path = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Forest Run" + File.separator + "Highscore.txt";
					File file = new File(path);
					DataInputStream stream = new DataInputStream(new FileInputStream(file));
					String line=stream.readLine();
					this.highscore = Double.valueOf(line);
					stream.close();
				} catch (FileNotFoundException e) {
					System.out.println("FileNotFoundException");
				} catch (NumberFormatException e) {
					System.out.println("NumberFormatException");
				} catch (IOException e) {
					System.out.println("IOException");
				}
				
				if(score>=highscore) 
				{
				highscore=score;
				try (PrintStream out = new PrintStream(new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Forest Run" + File.separator + "Highscore.txt"))) 
				{
				    out.print(String.valueOf(this.highscore));
				}
				catch (FileNotFoundException e) {
					System.out.println("FileNotFoundException 2");
				}
				}
				this.gameOver=true;
				}
			
			currentImgDestX2-=8; currentImgSrcX1+=8;
			nextImgDestX1-=8; nextImgSrcX2+=8;
						
			if(currentImgDestX2<=0)
			{
				currentImgDestX2=this.getWidth();
				currentImgSrcX1=0;
				nextImgDestX1=this.getWidth();
				nextImgSrcX2=0;
				backgroundFrameNo++;
			}
			
			if(backgroundFrameNo==56)
			{
				backgroundFrameNo=1;
			}
			g.drawString(String.format("%.01f", score), 20, 50);
			}
			
			else
			{
				mainFrame.add(mainFrame.gameOverPanel);
				mainFrame.gameOverPanel.setFocusable(true);
				mainFrame.gameOverPanel.requestFocusInWindow();
				mainFrame.gameOverPanel.repaint();
				if(mainFrame.gameOverPanel.paintDuration>=150) 
					{
					mainFrame.remove(mainFrame.playPanel);
					}
			}
		}
		else 
		{
			super.paintComponent(g);
			g.drawImage(backgroundImage[backgroundFrameNo], 0, 0, this.currentImgDestX2, this.getHeight(), currentImgSrcX1, 0, 1300, 650, null);	
			g.drawImage(backgroundImage[backgroundFrameNo+1], nextImgDestX1, 0, this.getWidth(), this.getHeight(), 0, 0, nextImgSrcX2, 650, null);
			g.drawImage(this.pauseImage,0,0,null);
		}
		}
				
}
