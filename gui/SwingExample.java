import javax.swing.*;

public class SwingExample {
    public static void main(String[] args) {
        // Create a JFrame instance
        JFrame frame = new JFrame("Swing Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JLabel and add it to the frame
        JLabel label = new JLabel("Hello, Swing!", SwingConstants.CENTER);
        frame.add(label);

        // Make the frame visible
        frame.setVisible(true);
    }
}

