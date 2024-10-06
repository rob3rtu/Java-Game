import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setTitle("Java 2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
    }
}