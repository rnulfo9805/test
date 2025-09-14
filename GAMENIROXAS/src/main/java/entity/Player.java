package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.main.GamePanel;
import main.main.KeyHandler;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    
    public int screenX;
    public int screenY;
    
    public Player(GamePanel gp, KeyHandler keyH) {
    	
    	this.gp = gp;
    	this.keyH = keyH;
    	
    	screenX = gp.screenWidth/2 - (gp.tileSize/2);
    	screenY = gp.screenHeight/2 - (gp.tileSize/2);
    	
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
		  
		  down1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_down_1.png"));
		  down2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_down_2.png"));
		  down3 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_down_3.png"));
		  down4 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_down_4.png"));
		  down5 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_down_5.png"));
		  left1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_left_1.png"));
		  left2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_left_2.png"));
		  left3 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_left_3.png"));
		  left4 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_left_4.png"));
		  left5 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_left_5.png"));
		  right1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_right_1.png"));
		  right2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_right_2.png"));
		  right3 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_right_3.png"));
		  right4 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_right_4.png"));
		  right5 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_right_5.png"));
		  up1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_up_1.png"));
		  up2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_up_2.png"));
		  up3 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_up_3.png"));
		  up4 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_up_4.png"));
		  up5 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_up_5.png"));
		  
	  }catch(IOException e ) {
		  e.printStackTrace();
	  }
  }

  public void update (){
      
	  if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
		  
		  if(keyH.upPressed == true) {
	          direction = "up";
	    	  worldY -= speed;
          }
	      else if (keyH.downPressed == true){
	          direction = "down";       
	    	  worldY += speed;
          }
	      else if (keyH.leftPressed == true){
	          direction = "left";       
	    	  worldX -= speed;
          }
	      else if (keyH.rightPressed == true){
	          direction = "right";       
	    	  worldX += speed;
          }
	      
	      spriteCounter++;
	      	if(spriteCounter > 6) {  
	            spriteNum++;
	            	if(spriteNum > 5) {
	            		spriteNum = 1;
	    	  }
	    	  spriteCounter = 0;
	      }
	  }

	}
  public void draw(Graphics2D g2 ){
		
	  BufferedImage image = null;
	  
	  switch(direction) {
		  case "up":
			  if(spriteNum == 1) {
				  image = up1;
			  }
			  if(spriteNum == 2) {
				  image =up2;
			  }
			  if(spriteNum == 3) {
				  image =up3;
			  }
			  if(spriteNum == 4) {
				  image =up4;
			  }
			  if(spriteNum == 5) {
				  image =up5;
			  }
			  break;
			  
		  case "down":
			  if(spriteNum ==1) {
				  image = down1;
			  }
			  if(spriteNum ==2) {
				  image = down2;
			  }
			  if(spriteNum ==3) {
				  image = down3;
			  }
			  if(spriteNum ==4) {
				  image = down4;
			  }
			  if(spriteNum ==5) {
				  image = down5;
			  }
			  break;
			  
		  case "left":
			  if(spriteNum ==1) {
				  image = left1;
			  }
			  if(spriteNum ==2) {
				  image = left2;
			  }
			  if(spriteNum ==3) {
				  image = left3;
			  }
			  if(spriteNum ==4) {
				  image = left4;
			  }
			  if(spriteNum ==5) {
				  image = left5;
			  }
			  break;
			  
		  case "right":
			  if(spriteNum ==1) {
				  image = right1;
			  }
			  if(spriteNum ==2) {
				  image = right2;
			  }
			  if(spriteNum ==3) {
				  image = right3;
			  }
			  if(spriteNum ==4) {
				  image = right4;
			  }
			  if(spriteNum ==5) {
				  image = right5;
			  }
			  break;
		  }
	  g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	  
	  
  	}
 
  }