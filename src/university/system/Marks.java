package university.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class Marks extends JDialog {

    JTable resultTable;
    JButton backButton;

    // Constructor that takes roll number as a parameter
    public Marks(String rNumber) {
        // Set dialog properties
        this.setSize(800, 400);
        this.setLocation(400, 200);
        this.setLayout(null);  // Absolute positioning
        this.getContentPane().setBackground(new Color(192, 223, 239));  // Set background color
        this.setTitle("Examination Results");
        this.setModal(true);
        // Set dialog to modal
        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Heading for the result dialog
        JLabel heading = new JLabel("Examination Results for : " + rNumber);
        heading.setBounds(80, 15, 400, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.add(heading);

        // Table to display results
        resultTable = new JTable();
        resultTable.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Add the table to a scroll pane
        JScrollPane jsp = new JScrollPane(resultTable);
        jsp.setBounds(50, 80, 700, 250);
        this.add(jsp);

        // Fetch student examination results from the database based on the roll number
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM subject_marks WHERE regestration = '" + rNumber + "'";
            ResultSet rs = c.s.executeQuery(query);
            resultTable.setModel(DbUtils.resultSetToTableModel(rs));  // Populate table with results
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back button to close the dialog and go back
        backButton = new JButton("Back");
        backButton.setBounds(350, 340, 100, 30);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose(); // Close the dialog
            }
        });
        this.add(backButton);

        // Display the dialog
        this.setVisible(true);
    }
}