package university.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    // UI Components
    JButton login, cancel;
    JTextField tfusername;
    JPasswordField tfpassword;

    // Constructor to set up the login screen
    Login() {
        // Set the background color of the frame
        getContentPane().setBackground(new Color(192, 223, 239));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);  // Padding for components
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Stretch components horizontally

        // Heading for "Login"
        JLabel heading = new JLabel("Login", JLabel.CENTER);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        gbc.gridwidth = 2;  // Span across columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(heading, gbc);

        // Username label and text field
        JLabel lblusername = new JLabel("Username");
        lblusername.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblusername, gbc);

        tfusername = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        tfusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(tfusername, gbc);

        // Password label and password field
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblpassword, gbc);

        tfpassword = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        tfpassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(tfpassword, gbc);

        // Login button
        login = new JButton("Login");
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        login.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(login, gbc);

        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        cancel.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(cancel, gbc);

        // Image icon
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(image, gbc);

        // Frame settings
        setSize(400, 300);
        setTitle("Login");// Set frame size
        setLocation(500, 250); // Set frame location
        setVisible(true); // Make frame visible
        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            // Get username and password input
            String username = tfusername.getText();
            String password = tfpassword.getText();
            String query = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";

            try {
                // Database connection and query execution
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    // If user exists, open the next screen
                    setVisible(false);
                    new Project();
                } else {
                    // Show error message for invalid credentials
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print exception details
            }
        } else if (ae.getSource() == cancel) {
            // Close the login screen when cancel is clicked
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
