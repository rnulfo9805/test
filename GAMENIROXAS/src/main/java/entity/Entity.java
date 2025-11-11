package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1,up2;
    public BufferedImage down1,down2;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    BufferedImage standingLeft, standingRight;
    BufferedImage left1, left2, right1, right2;
    BufferedImage inBetweenLeft, inBetweenRight;
    BufferedImage inBetweenDown, inBetweenUp;
   

    public String direction; 
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
}

