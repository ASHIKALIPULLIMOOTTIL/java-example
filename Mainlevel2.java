import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Ball {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    private int x, y, xDir, yDir, radius;

    public Ball(int x, int y) {
        this.x = x;
        this.y = screenHeight - 30;
        this.xDir = 2;  // Initial speed in x direction
        this.yDir = -3; // Initial speed in y direction
        this.radius = 10;
    }

    public void move() {
        x += xDir;
        y += 2 * yDir;

        if (x < 0 || x > screenWidth - 2 * radius) {
            xDir = -xDir;
        }
        if (y < 0) {
            yDir = -yDir;
        }
    }

    public int getXDir() {
        return xDir;
    }

    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public void reverseY() {
        yDir = -yDir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, radius * 2, radius * 2);
    }
}

class Paddle {
    private int x, y, width, height;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = screenHeight - 50; // Adjusted to ensure visibility
        this.width = 120;
        this.height = 15;
    }

    public void move(int mouseX) {
        this.x = Math.max(0, Math.min(screenWidth - width, mouseX - width / 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}

class Brick {
    private int x, y, width, height;
    private boolean destroyed;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = screenWidth / 15; // Adjust width for Level 2 layout
        this.height = screenHeight / 30; // Adjust height for Level 2 layout
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(Color.YELLOW); // Change color for Level 2
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
}

class GamePanel extends JPanel implements KeyListener, MouseMotionListener, ActionListener {
    private Timer timer;
    private Ball ball;
    private Paddle paddle;
    private Brick[][] bricks;
    private boolean inGame;
    private boolean isPaused; // New variable to track paused state
    private int totalBricks;
    private int score = 0; // Score variable
    private int lives = 3; // Lives variable

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);

        ball = new Ball(550, 550);
        paddle = new Paddle(200, 350);
        bricks = new Brick[6][15]; // Change brick arrangement for Level 2
        inGame = true;
        isPaused = false; // Initialize paused state to false

        // Initialize the bricks for Level 2 with different layout
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 15; j++) {
                bricks[i][j] = new Brick(j * (screenWidth / 15), i * (screenHeight / 30));
            }
        }
        totalBricks = 6 * 15; // Total bricks in level 2

        timer = new Timer(10, this);
        timer.start();

        addMouseMotionListener(this);
        addMouseListener(new MouseAdapter() { // Add mouse listener for click events
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePause(); // Toggle pause on mouse click
            }
        });
        setFocusable(true);
    }

    private void togglePause() {
        isPaused = !isPaused; // Toggle the paused state
    }

    public void checkCollisions() {
        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getRadius() * 2, ball.getRadius() * 2);
        Rectangle paddleRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());

        if (ballRect.intersects(paddleRect)) {
            int paddleCenter = paddle.getX() + paddle.getWidth() / 2;
            int ballCenter = ball.getX() + ball.getRadius();
            int difference = ballCenter - paddleCenter;

            double hitPosition = (double) difference / (paddle.getWidth() / 2);
            ball.reverseY();
            ball.setXDir((int) (ball.getXDir() + hitPosition * 2));
        }

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                Brick b = bricks[i][j];
                if (!b.isDestroyed()) {
                    Rectangle brickRect = b.getBounds();
                    if (ballRect.intersects(brickRect)) {
                        b.setDestroyed(true);
                        ball.reverseY();
                        score++;          // Increment score
                        totalBricks--;    // Decrease total bricks count when one is destroyed
                    }
                }
            }
        }

        if (ball.getY() > screenHeight) {
            lives--; // Decrement lives
            if (lives <= 0) {
                inGame = false; // End game if no lives are left
            } else {
                // Reset ball position or other logic to allow the player to continue
                ball = new Ball(550, 550); // Reset the ball
            }
        }

        if (totalBricks == 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && !isPaused) { // Only move and check collisions if not paused
            ball.move();
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        paddle.draw(g);

        for (int i = 0; i < bricks.length; i++) {
            for (int j = 0; j < bricks[i].length; j++) {
                bricks[i][j].draw(g);
            }
        }

        // Draw score in a red box in the top-right corner
        g.setColor(Color.RED);
        g.fillRect(screenWidth - 160, 10, 150, 40); // Red box for the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, screenWidth - 150, 30); // Draw score text

        // Draw lives below the score
        g.setColor(Color.RED);
        g.fillRect(screenWidth - 160, 60, 150, 40); // Red box for lives
        g.setColor(Color.WHITE);
        g.drawString("Lives: " + lives, screenWidth - 150, 80); // Draw lives text

        if (!inGame) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            String message = (totalBricks == 0) ? "Level Complete!" : "Game Over";
            g.drawString(message, screenWidth / 2 - 150, screenHeight / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Click to exit", screenWidth / 2 - 70, screenHeight / 2 + 40);
        }

        if (isPaused) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Paused", screenWidth / 2 - 100, screenHeight / 2);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!isPaused) { // Only move paddle if not paused
            paddle.move(e.getX());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0); // Exit the game on Escape key press
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

public class Mainlevel2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker Game");
        GamePanel gamePanel = new GamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        gamePanel.requestFocus();
    }
}
