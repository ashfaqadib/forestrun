import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGameInterface implements ActionListener{

	MainFrame mainFrame;
	
	PlayGameInterface (MainFrame frame)
	{
		this.mainFrame=frame;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		mainFrame.playPanel.gameOver=false;
		mainFrame.playPanel.returnToMainMenu=false;
		mainFrame.remove(mainFrame.mainMenuPanel);
		mainFrame.add(mainFrame.playPanel);
		GameSound.gameBegins();
		
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				while(!mainFrame.playPanel.returnToMainMenu){
					mainFrame.playPanel.setFocusable(true);
					mainFrame.playPanel.requestFocusInWindow();
					mainFrame.playPanel.repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				mainFrame.playPanel.setDefaultvalues();
				mainFrame.remove(mainFrame.gameOverPanel);
				mainFrame.remove(mainFrame.playPanel);
				mainFrame.add(mainFrame.mainMenuPanel);
				mainFrame.mainMenuPanel.repaint();
			}
		});
		t.start();
	}

}
