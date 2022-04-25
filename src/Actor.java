import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Actor {

	BufferedImage actorImage[]=new BufferedImage[9];
	BufferedImage somerSaultImage[]=new BufferedImage[7];
	BufferedImage actorShootImage;
	String actorImagePath="/ActorImages/Actor";
	String somerSaultPath="/ActorImages/somersault";
	int actorFrameNo=1,somerSaultFrameNo=1,walked=15,changeConst=1285,jumped=0,somerSaulted=0;
	int x=10,y=280;
	PlayPanel playPanel;
	boolean actorShoot=false;
	int noOfShots=0;
	
	Actor(PlayPanel playPanel)
	{
		
		for(int i=1;i<=8;i++)
		{
			try {
				actorImage[i]= ImageIO.read(getClass().getResource(this.actorImagePath+i+".png"));
				if(i<7) somerSaultImage[i]= ImageIO.read(getClass().getResource(this.somerSaultPath+i+".png"));
				actorShootImage=ImageIO.read(getClass().getResource("/ActorImages/ActorShoot.png"));;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.playPanel=playPanel;
	}
	
	void drawActorImage (Graphics g)
	{
		if(actorShoot!=true)
		{
		if(y!=280&&x==10)
		{
			g.drawImage(actorImage[actorFrameNo], x, y, null);
			if(jumped==13&&y!=280) {
				y+=40;
				if(y>=280) 
					{
						y=280;
						jumped=0;
					}
		}
			else {
				if(jumped<=3) {actorFrameNo=5;y-=35;}
				else if(jumped<=5) {actorFrameNo=1;y-=35;}
				else if(jumped<=12) {actorFrameNo=6;}
				jumped++;
		}
		}
		
		else if(x!=10)
		{
			if(somerSaulted<=5) {
					x+=20;
					g.drawImage(somerSaultImage[somerSaultFrameNo], x, y, null);
					if(somerSaulted==3) somerSaultFrameNo++;
					else if(somerSaulted==5) somerSaultFrameNo++;
					somerSaulted++;
			}
			else if (somerSaulted<=10){
				x-=20;
				g.drawImage(somerSaultImage[somerSaultFrameNo], x, y, null);
				if(somerSaulted==8) somerSaultFrameNo++;
				else if(somerSaulted==10) somerSaultFrameNo++;
				somerSaulted++;
			}
			else if(somerSaulted<=15)
				{
					x-=5;
					g.drawImage(somerSaultImage[somerSaultFrameNo], x, y, null);
					if(somerSaulted==13) somerSaultFrameNo++;
					somerSaulted++;
					if(somerSaulted==16)
					{
					x=10;
					actorFrameNo=5;
					somerSaultFrameNo=1;
					somerSaulted=0;
					y=280;
					}
				}
		}
		
		else{
			 g.drawImage(actorImage[actorFrameNo], x, y, null);
			 if(walked+this.changeConst==1300) 
				 {
				 	walked+=15;
				 	actorFrameNo++;	
				 }
			 	 changeConst-=5;
		}
		}
		else 
		{
			this.noOfShots++;
			g.drawImage(actorShootImage, x, y, null);
			if(noOfShots==6){
				this.noOfShots=0;
				this.actorShoot=false;
			}
		
		}
		
	 	if(actorFrameNo==8)
		{
			actorFrameNo=1;
			changeConst=1300;
			walked=15;
		}
		
	}

}
