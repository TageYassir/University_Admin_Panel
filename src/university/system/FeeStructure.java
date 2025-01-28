package university.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame {

    // Constructor to set up the UI for Fee Structure
    FeeStructure() {
        // Set size and position of the frame
        this.setSize(1000, 400);
        this.setLocation(250, 250);
        this.setTitle("Fee Structure");

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Set layout manager to null for absolute positioning
        this.setLayout((LayoutManager) null);

        // Set background color of the frame to white
        this.getContentPane().setBackground(new Color(192, 223, 239));

        // Add heading label for "Fee Structure"
        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(400, 10, 400, 30);  // Positioning the label
        heading.setFont(new Font("Tahoma", 1, 30));  // Font settings
        this.add(heading);  // Add the label to the frame

        // Create a JTable to display the fee structure data
        JTable table = new JTable();

        try {
            // Create a database connection
            Conn c = new Conn();

            // Query to fetch fee structure details
            ResultSet rs = c.s.executeQuery("select * from fee");

            // Populate the table with the data from the result set
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception var5) {
            // Handle any exceptions that occur during database operations
            var5.printStackTrace();
        }

        // Add the table to a JScrollPane for scrollable view
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 60, 1000, 700);  // Position and size of the JScrollPane
        this.add(jsp);  // Add the JScrollPane to the frame

        // Make the frame visible
        this.setVisible(true);
    }

    // Main method to run the FeeStructure form
    public static void main(String[] args) {
        // Create and display the FeeStructure frame
        new FeeStructure();
    }
}
