package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean up,left,right,down;
	public boolean wantToPickUp;

	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(gp.gameScreenNumber) {
		case "title":
			//DURING MAIN MENU
			if (key == KeyEvent.VK_UP) {
		        gp.ui.chosenMenuNumber++;
		        if(gp.ui.chosenMenuNumber > 1) {
		        	gp.ui.chosenMenuNumber = 0;
		        }
		    }
		    if (key == KeyEvent.VK_DOWN) {
		    	gp.ui.chosenMenuNumber--;
		        if(gp.ui.chosenMenuNumber < 0) {
		        	gp.ui.chosenMenuNumber = 1;
		        }
		    }
		    if (key == KeyEvent.VK_ENTER) {
		    	if(gp.ui.chosenMenuNumber == 0) {
		    		gp.gameScreenNumber = "normal";
		    	}
		    	if(gp.ui.chosenMenuNumber == 1) {
		    		System.exit(0);
		    	}
		    }
			break;
		case "normal":
			//DURING SIMULATION
			if (key == KeyEvent.VK_UP) {
		        up = true;
		    }
		    if (key == KeyEvent.VK_RIGHT) {
		        right = true;
		    }
		    if (key == KeyEvent.VK_DOWN) {
		        down = true;
		    }
		    if (key == KeyEvent.VK_LEFT) {
		        left = true;
		    }
		    if (key == KeyEvent.VK_P) {
		    	if(gp.isPaused==true) {
	        		gp.isPaused = false;
	        	}else gp.isPaused=true;
		    }
	        if (key == KeyEvent.VK_E) {
	            wantToPickUp = true;
	        }
	        if (key == KeyEvent.VK_S) {
	        	if(gp.statIsVisible==true) {
	        		gp.statIsVisible = false;
	        	}else gp.statIsVisible=true;
	        	
	        }
	        if (key == KeyEvent.VK_A) {
	        	if (gp.statIsVisible){
	        		if(gp.entityIndex>0) {
	        			gp.entityIndex--;
	        		}else gp.entityIndex=gp.entityList.size()-1;
	        		
	        	}
	        }
	        if (key == KeyEvent.VK_D) {
	        	if (gp.statIsVisible){
	        		if(gp.entityIndex<gp.entityList.size()-1) {
	        			gp.entityIndex++;
	        		}else gp.entityIndex=0;
	        	}
	        }
		    break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_UP) {
            up = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
	}

}
