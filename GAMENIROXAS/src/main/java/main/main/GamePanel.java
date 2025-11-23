package main.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

//SCREEN
	public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 32;
    final int scale = 5;
    
    public final int tileSize = originalTileSize * scale;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int screenWidth = (int) screenSize.getWidth();
    public final int screenHeight = (int) screenSize.getHeight();
    public final int maxScreenCol = screenWidth / tileSize;
    public final int maxScreenRow = screenHeight / tileSize;

    
    
//WORLD
    public final int maxWorldCol = 10;
    public final int maxWorldRow = 11;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    int FPS = 60;
    public CollisionChecker Checker = new CollisionChecker(this);
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);
    
    private GraphicsDevice gd;
     
    public GamePanel(){
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();    
    }
    @Override
//    public void run() {
//       
//        double drawInterval = 1000000000/FPS; //0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        
//       while(gameThread != null) {
//        
//          
//           // 1 update: update information such as character position
//           update(); 
//           
//           // 2 draw: draw the screen with the updated information
//           repaint(); 
//           
//          
//           
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                
//              if(remainingTime < 0){
//                  remainingTime = 0;
//              }
//                
//                Thread.sleep((long) remainingTime);
//                
//                nextDrawTime += drawInterval;
//                
//            } catch (InterruptedException ex) {
//                
//            }
//           
//           
//           
//    }
   
        
    
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
        
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if (delta >= 1){
                 update();
                repaint();
                delta--;
                drawCount++;
            }
        if (timer >= 1000000000){
            System.out.println("FPS " + drawCount);
            drawCount = 0;
            timer = 0;
        }
        
      
        
    }
}
          public void update(){  
              
              player.update();
          }
           public void paintComponent(Graphics g){
               
               super.paintComponent(g);
               
                Graphics2D g2 = (Graphics2D)g;
               
                tileM.draw(g2);
                player.draw(g2);
                
                g2.dispose();
            
               
              }
	}   

    