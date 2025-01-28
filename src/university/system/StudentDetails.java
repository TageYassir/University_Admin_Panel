package university.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class StudentDetails extends JFrame implements ActionListener {
    JTextField trollno;  // TextField for entering roll number
    JTable table;        // Table to display student details
    JButton search;      // Button for searching student by roll number
    JButton print;       // Button to print the student details table
    JButton add;         // Button to add a new student
    JButton cancel;      // Button to cancel and close the window

    // Constructor for initializing the components
    StudentDetails() {
        // Set the background color and layout for the JFrame
        this.getContentPane().setBackground(new Color(192, 223, 239));
        this.setLayout(null);  // Use null layout for manual control

        // Label for "Search by Roll Number"
        JLabel heading = new JLabel("Search by R Number:");
        heading.setBounds(20, 20, 150, 20);
        this.add(heading);

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // TextField for entering roll number
        this.trollno = new JTextField();
        this.trollno.setBounds(180, 20, 150, 20);
        this.add(this.trollno);

        // Initialize the table to display student details
        this.table = new JTable();

        // Retrieve all student data to populate the table initially
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            this.table.setModel(DbUtils.resultSetToTableModel(rs));  // Set table model from the result set
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        // JScrollPane to make the table scrollable
        JScrollPane jsp = new JScrollPane(this.table);
        jsp.setBounds(0, 100, 900, 600);
        this.add(jsp);

        // Add buttons for search, print, add, and cancel
        this.search = new JButton("Search");
        this.search.setBounds(20, 70, 80, 20);
        this.search.addActionListener(this);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        this.add(this.search);

        this.print = new JButton("Print");
        this.print.setBounds(120, 70, 80, 20);
        this.print.addActionListener(this);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        this.add(this.print);

        this.add = new JButton("Add");
        this.add.setBounds(220, 70, 80, 20);
        this.add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        this.add(this.add);

        this.cancel = new JButton("Cancel");
        this.cancel.setBounds(320, 70, 80, 20);
        this.cancel.addActionListener(this);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        this.add(this.cancel);

        // Set the size and location of the JFrame
        this.setSize(900, 700);
        this.setTitle("Student Details");
        this.setLocationRelativeTo(null);  // Center the window on the screen
        this.setVisible(true);
    }

    // ActionListener method to handle button actions
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.search) {
            String regestration = this.trollno.getText().trim();

            try {
                Conn c = new Conn();
                ResultSet rs;

                // If input is empty, fetch all records
                if (regestration.isEmpty()) {
                    rs = c.s.executeQuery("select * from student");
                } else {
                    // Otherwise, search for the specific roll number
                    String query = "select * from student where regestration = '" + regestration + "'";
                    rs = c.s.executeQuery(query);

                    // Check if the roll number exists
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "R number not found.");
                        return;
                    }

                    // Reset the cursor to the beginning
                    rs.beforeFirst();
                }

                // Update the table with the result set
                this.table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        // If "Print" button is clicked, print the table details
        else if (ae.getSource() == this.print) {
            try {
                this.table.print();  // Print the table contents
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }
        // If "Add" button is clicked, open the AddStudent form
        else if (ae.getSource() == this.add) {
            this.setVisible(false);
            new AddStudent();  // Open AddStudent form
        }
        // If "Cancel" button is clicked, close the current window
        else {
            this.setVisible(false);
        }
    }

    // Main method to run the StudentDetails JFrame
    public static void main(String[] args) {
        new StudentDetails();  // Create and display the StudentDetails window
    }
}
