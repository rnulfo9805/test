package entity;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage up3, up4, down3, down4, left3, left4, right3, right4;
    public BufferedImage up5, down5, left5, right5;
    public BufferedImage downStill, upStill, leftStill, rightStill;

    public String direction; 
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
}