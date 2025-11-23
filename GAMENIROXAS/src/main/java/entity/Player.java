package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.main.GamePanel;
import main.main.KeyHandler;
import java.awt.Rectangle;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    
    public int screenX;
    public int screenY;
    int counter2 = 0;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        

        solidArea = new Rectangle(20, 40, 70, 80);
        
        setDefaultValues();
        GetPlayerImage();
        
    }
    
    public void setDefaultValues(){ 
        int centerCol = gp.maxWorldCol / 2;
        int centerRow = gp.maxWorldRow / 2;
        worldX = centerCol * gp.tileSize;
        worldY = centerRow * gp.tileSize;
        speed = 10;
        direction = "down";
    }
    
    public void GetPlayerImage() {
        try {
            standingLeft = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Standing_Left.png"));
            standingRight = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Standing_Right.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Right_2.png"));
            inBetweenLeft = ImageIO.read(getClass().getResourceAsStream("/player/Rem_InBetween_Left.png"));
            inBetweenRight = ImageIO.read(getClass().getResourceAsStream("/player/Rem_InBetween_Right.png"));
            inBetweenUp = ImageIO.read(getClass().getResourceAsStream("/player/Rem_InBetween_Up.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Rem_Walk_Up_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
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

            spriteCounter++;
            if (spriteCounter > 8) {
                spriteNum++;
                if (spriteNum > 4) spriteNum = 1;
                spriteCounter = 0;
            }

            collisionOn = false;
            gp.Checker.checkTile(this);

            if (collisionOn == false) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
                
                // Clamp to world bounds
                worldX = Math.max(0, Math.min(worldX, gp.worldWidth - gp.tileSize));
                worldY = Math.max(0, Math.min(worldY, gp.worldHeight - gp.tileSize));
            }
        } else {
            spriteNum = 1;
        }
    }
    
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        boolean moving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;

        switch (direction) {
            case "left":
                if (moving) {
                    switch (spriteNum) {
                        case 1: image = left1; break;
                        case 2: image = inBetweenLeft; break;
                        case 3: image = left2; break;
                        case 4: image = inBetweenLeft; break;
                    }
                } else {
                    image = standingLeft;
                }
                break;
            case "right":
                if (moving) {
                    switch (spriteNum) {
                        case 1: image = right1; break;
                        case 2: image = inBetweenRight; break;
                        case 3: image = right2; break;
                        case 4: image = inBetweenRight; break;
                    }
                } else {
                    image = standingRight;
                }
                break;
            case "up":
                if (moving) {
                    switch (spriteNum) {
                        case 1: image = up1; break;
                        case 2: image = inBetweenUp; break;
                        case 3: image = up2; break;
                        case 4: image = inBetweenUp; break;
                    }
                } else {
                    image = inBetweenUp;
                }
                break;
            case "down":
                image = standingRight;
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}