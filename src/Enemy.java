import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {


	BufferedImage greenRobotImage[]=new BufferedImage[3];
	BufferedImage runningRobotImage[]=new BufferedImage[6];
	BufferedImage killerRobotImage[]=new BufferedImage[11];
	BufferedImage greenRobotShootImage;
	BufferedImage smokeImage;
	String greenRobotPath,greenRobotShootPath,runningRobotImagePath,killerRobotImagePath;
	int enemyX=1300,enemyY=200,rockX=1300;
	int circleX=1300,circleY;
	int runningRobotFrameNo=1;
	int killerRobotFrameNo=1;
	int walked=0;
	int killerX=1300;
	boolean drawRock=false;
	boolean drawRobot=false;
	boolean drawShoot=false;
	boolean drawKiller=false;
	double theta=0;
	int shootX=1000;
	PlayPanel playPanel;
	
	Enemy (PlayPanel playPanel)
	{
		this.playPanel=playPanel;
		this.greenRobotPath="/EnemyImages/GreenRobot";
		this.greenRobotShootPath="/EnemyImages/GreenRobotShoot.png";
		this.runningRobotImagePath="/EnemyImages/RunningRobot1_";
		this.killerRobotImagePath="/EnemyImages/KillerRobot";
		try {
			greenRobotShootImage = ImageIO.read(getClass().getResource(this.greenRobotShootPath));
			smokeImage= ImageIO.read(getClass().getResource("/EnemyImages/Smoke.png"));;
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=1;i<=10;i++)
		{
			try {
				if(i<=2)greenRobotImage[i]= ImageIO.read(getClass().getResource(this.greenRobotPath+i+".png"));
				if(i<=5)runningRobotImage[i]= ImageIO.read(getClass().getResource(this.runningRobotImagePath+i+".png"));
				killerRobotImage[i]=ImageIO.read(getClass().getResource(this.killerRobotImagePath+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	void drawEnemy(Graphics g)
	{
		
		if(drawRock)
		{
			g.drawImage(runningRobotImage[runningRobotFrameNo], rockX, 480, null);
			if(rockX+walked==1300)
			{
				walked+=60;
				runningRobotFrameNo++;
			}
			rockX-=20;
			if(runningRobotFrameNo==6) runningRobotFrameNo=1;
		}

		if(playPanel.noOfShots<=2){
		if(drawRobot)
		{
			if(circleX!=1200)
			{
				circleX-=10;
				enemyX=circleX;
			}
			else{
			theta = theta + Math.toRadians(8);
			enemyX = (int) (1000 + (130*Math.cos(theta)));
			enemyY = (int) (200 + (130*Math.sin(theta)));
			}
			if(rockX>=1100||rockX<=100){
				g.drawImage(greenRobotImage[2], enemyX,enemyY, null);
				drawShoot=true;
				if(shootX>=1000) GameSound.laserShot();
			}
			else{
				g.drawImage(greenRobotImage[1], enemyX,enemyY, null);
			}
			if(playPanel.noOfShots==1)
				g.drawImage(smokeImage, enemyX+20,enemyY+20, null);
			else if (playPanel.noOfShots==2) 
				{
					g.drawImage(smokeImage, enemyX+20,enemyY+20, null);
					g.drawImage(smokeImage, enemyX+80,enemyY-20, null);
				}
		}
		if(drawKiller)
		{
			g.drawImage(killerRobotImage[killerRobotFrameNo],killerX,280,null);
			killerX-=15;
			killerRobotFrameNo++;
			if(this.killerRobotFrameNo==11)
			{
				killerRobotFrameNo=1;
			}
		}
		}
		else
		{
			circleX=1300;
			playPanel.cursor.setLocation(0,0);
			drawRobot=false;
			playPanel.noOfShots=0;
			drawKiller=false;
			killerX=1300;
		}
		if(drawShoot)
		{
			g.drawImage(greenRobotShootImage,shootX,280,null);
			shootX-=40;
			if(shootX<=0)
			{
				drawShoot=false;
				shootX=1000;
			}
		}

		if(rockX<=0){
			rockX=1300;
			runningRobotFrameNo=1;
			walked=0;
			drawRock=false;
		}
	}
	
}
