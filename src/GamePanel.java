import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //meaning 16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16; //number of columns, aka tiles on horizontal
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
         this.setPreferredSize(new Dimension(screenWidth, screenHeight));
         this.setBackground(Color.black);
         this.setDoubleBuffered(true);
         this.addKeyListener(keyHandler);
         this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null) {
            // 1 - Update information, like position for example
            update();

            // 2 - Draw the updated screen
            repaint(); // this actually calls paintComponent()

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0) remainingTime = 0;

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(keyHandler.upPressed) {
            this.playerY -= this.playerSpeed;
        } else  if(keyHandler.downPressed) {
            this.playerY += this.playerSpeed;
        } else if(keyHandler.leftPressed) {
            this.playerX -= this.playerSpeed;
        } else if(keyHandler.rightPressed) {
            this.playerX += this.playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(this.playerX, this.playerY, this.tileSize, this.tileSize);

        graphics2D.dispose();
    }
}





















