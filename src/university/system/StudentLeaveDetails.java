package university.system;

import java.awt.Color;
import java.awt.LayoutManager;
import java.sql.ResultSet;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class StudentLeaveDetails extends JFrame {
    JTable table;  // Table to display student leave records

    // Constructor to initialize the GUI components
    StudentLeaveDetails() {
        this.setLayout((LayoutManager) null);  // Set layout to null for custom positioning
        this.setTitle("Student Leave Details");

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Initialize the table
        this.table = new JTable();

        // Fetch and display student leave details (students who have taken leave)
        try {
            Conn c = new Conn();
            // Query to fetch all students who have taken leave
            ResultSet rs = c.s.executeQuery("select * from studentleave");
            this.table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        // Add the table to a scroll pane and set its position
        JScrollPane jsp = new JScrollPane(this.table);
        jsp.setBounds(0, 0, 900, 800);
        this.add(jsp);

        // Set frame size and location
        this.setSize(900, 700);
        this.setLocation(300, 100);
        this.setVisible(true);
    }

    // Main method to run the StudentLeaveDetails form
    public static void main(String[] args) {
        new StudentLeaveDetails();  // Open the Student Leave Details form
    }
}
