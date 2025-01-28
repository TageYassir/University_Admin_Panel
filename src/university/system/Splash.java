package university.system;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash extends JFrame implements Runnable {
    Thread t;  // Thread for controlling the splash screen delay

    // Constructor for setting up the splash screen
    Splash() {
        // Load the splash image and scale it to fit the window
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, 1);  // Resize the image
        ImageIcon i3 = new ImageIcon(i2);

        // Add the image to the JFrame
        JLabel image = new JLabel(i3);
        this.add(image);
        this.setTitle("UEMF");

        // Start a new thread to manage the splash screen's animation and delay
        this.t = new Thread(this);
        this.t.start();

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Make the frame visible
        this.setVisible(true);

        int x = 1;
        // Gradually resize and move the splash screen to create a zoom-in effect
        for (int i = 2; i <= 600; ++x) {
            this.setLocation(600 - (i + x) / 2, 350 - i / 2);  // Adjust the location of the splash screen
            this.setSize(i + 3 * x, i + x / 2);  // Adjust the size of the splash screen

            try {
                // Slow down the loop to create a smooth animation effect
                Thread.sleep(10L);
            } catch (Exception var8) {
                // Handle exception (if any)
            }

            i += 4;  // Increment the size for the next loop iteration
        }
    }

    // Run method that waits for a set duration and then hides the splash screen
    public void run() {
        try {
            Thread.sleep(3500L);
            // Hide the splash screen
            this.setVisible(false);
            // After the splash screen, show the login window
            new Login();
        } catch (Exception var2) {
            // Handle exception (if any)
        }
    }

    // Main method to launch the splash screen
    public static void main(String[] args) {
        new Splash();  // Create an instance of the Splash screen
    }
}
