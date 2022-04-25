import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSound {
	
	public static void music(){
		
		try{
			URL songPath = GameSound.class.getResource("/Sound/midnight.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e){
			System.out.println("Couldn't create background music.");
		}
		
	}
	
	public static void gameBegins(){
		
		try{
			URL songPath = GameSound.class.getResource("/Sound/catchme2.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}
		catch(Exception e){
			System.out.println("Couldn't create background music.");
		}
		
	}
	
     public static void jump(){
		
		try{
			URL songPath = GameSound.class.getResource("/Sound/gruntJumpSilentLand.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}
		catch(Exception e){
			System.out.println("Couldn't create jump music.");
		}
		
	}
     
     public static void dive(){
 		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/dive.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
			System.out.println("Couldn't create slide music.");	
 		}
 		
 	}
     
     
     public static void gunShot(){
  		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/gunshot.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
			System.out.println("Couldn't create slide music.");	
 		}
 		
 	}
     
     public static void laserShot(){
   		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/laserblast.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
			System.out.println("Couldn't create slide music.");	
 		}
 		
 	}
     
     public static void killerEntry(){
   		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/killerentry.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
			System.out.println("Couldn't create slide music.");	
 		}
 		
 	}
     
     public static void robotEntry(){
    		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/havetocapture.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
			System.out.println("Couldn't create slide music.");	
 		}
 		
 	}
     
     public static void gameOver(){
  		
  		try{
  			URL songPath = GameSound.class.getResource("/Sound/gameover.wav");
  			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
  			Clip clip = AudioSystem.getClip();
  			clip.open(ais);
  			clip.start();
  		}
  		catch(Exception e){
			System.out.println("Couldn't create gameover music.");	
  		}
  		
  	}
     
     public static void robotGotShot(){
 		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/hitRobot.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
 			System.out.println("Couldn't create jump music.");
 		}
 		
 	}
     
     public static void killerGotShot(){
  		
 		try{
 			URL songPath = GameSound.class.getResource("/Sound/killergrunt.wav");
 			AudioInputStream ais = AudioSystem.getAudioInputStream(songPath);
 			Clip clip = AudioSystem.getClip();
 			clip.open(ais);
 			clip.start();
 		}
 		catch(Exception e){
 			System.out.println("Couldn't create jump music.");
 		}
 		
 	}
}
