package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    
    public int screenX;
    public int screenY;
    int counter2 = 0;
    
    BufferedImage standingLeft, standingRight;
    BufferedImage left1, left2, right1, right2;
    BufferedImage inBetweenLeft, inBetweenRight;
	
    public Player(GamePanel gp, KeyHandler keyH) {
    	
    	this.gp = gp;
    	this.keyH = keyH;
    	
    	screenX = gp.screenWidth/2 - (gp.tileSize/2);
    	screenY = gp.screenHeight/2 - (gp.tileSize/2);
    	
    	solidArea = new Rectangle();
    	solidArea.x = 8;
    	solidArea.y = 16;
    	solidArea.width = 32;
    	solidArea.height = 32;
    	
    	
    	
    	setDefaultValues();
		GetPlayerImage();

    }
  public void setDefaultValues(){ 
      
      worldX = 100;
      worldY = 100;
      speed = 4;
      direction = "down";
  
  	}
  public void GetPlayerImage() {
	    try {
			
	        standingLeft = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Standing_Left.png"));
	        standingRight = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Standing_Right.png"));

	        left1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Left_1.png"));
	        left2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Left_2 .png"));

	        right1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Right_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Right_2 .png"));

	        
	        inBetweenLeft = ImageIO.read(getClass().getResourceAsStream("/player/Rem _InBetween_Left.png"));
	        inBetweenRight = ImageIO.read(getClass().getResourceAsStream("/player/Rem_InBetween_Right.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

  public void update (){
      
	  if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
		    if (keyH.upPressed) {
		        direction = "up";	       
		    } else if (keyH.downPressed) {
		        direction = "down"; 
		    } else if (keyH.leftPressed) {
		        direction = "left";	     
		    } else if (keyH.rightPressed) {
		        direction = "right";	      
		    }
		    
		    //check tile collision
		    if (collisionOn == false) {
		    	switch (direction) {
		    	case "up":
		    		worldY -= speed;
		    	break;
		    	case "down":
		    		worldY += speed;
		    	break;
		    	case "left":
		    		 worldX -= speed;
		    	break;
		    	case "right":
		    		 worldX += speed;
		    	break;
		    	
		    	
		    	}
		    }
		    
		    collisionOn = false;
		    gp.cChecker.checkTile(this);

		    spriteCounter++;
		    if (spriteCounter > 6) {
		        if (spriteNum == 1) spriteNum = 2;
		        else spriteNum = 1;
		        spriteCounter = 0;
		    }
		}

  	}
  public void draw(Graphics2D g2) {
	    BufferedImage image = null;

	    boolean moving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;

	    switch (direction) {
	        case "left":
	            if (moving) {
	                image = (spriteNum == 1) ? left1 : left2;
	            } else {
	                image = standingLeft;
	            }
	            break;

	        case "right":
	            if (moving) {
	                image = (spriteNum == 1) ? right1 : right2;
	            } else {
	                image = standingRight;
	            }
	            break;

	        case "up":
	        case "down":
				
	            image = standingRight;
	            break;
	    }

	    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  	}
}
