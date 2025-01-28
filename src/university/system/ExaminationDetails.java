package university.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class ExaminationDetails extends JFrame implements ActionListener {
    JTextField search;
    JButton submit;
    JButton cancel;
    JTable table;

    // Constructor for setting up the UI and behavior
    public ExaminationDetails() {
        // Set the size and location of the frame
        this.setSize(1000, 475);
        this.setTitle("Examination");
        this.setLocation(300, 100);
        this.setLayout(null); // Absolute positioning
        this.getContentPane().setBackground(new Color(192, 223, 239)); // Set background color
        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Heading for the form
        JLabel heading = new JLabel("Check Result");
        heading.setBounds(80, 15, 400, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        this.add(heading);

        // Search text field
        this.search = new JTextField();
        this.search.setBounds(80, 90, 200, 30);
        this.search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.add(this.search);

        // Result button
        this.submit = new JButton("Result");
        this.submit.setBounds(300, 90, 120, 30);
        this.submit.setBackground(Color.BLACK);
        this.submit.setForeground(Color.WHITE);
        this.submit.addActionListener(this);
        this.submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(this.submit);

        // Back button
        this.cancel = new JButton("Back");
        this.cancel.setBounds(440, 90, 120, 30);
        this.cancel.setBackground(Color.BLACK);
        this.cancel.setForeground(Color.WHITE);
        this.cancel.addActionListener(this);
        this.cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        this.add(this.cancel);

        // Table to display student data
        this.table = new JTable();
        this.table.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // Add table to a scroll pane for better navigation
        JScrollPane jsp = new JScrollPane(this.table);
        jsp.setBounds(0, 130, 1000, 310);
        this.add(jsp);

        // Fetch and display student data from the database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            this.table.setModel(DbUtils.resultSetToTableModel(rs)); // Convert result set to table model
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions
        }

        // Add mouse listener to the table for selecting a student
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                // On clicking a row, update the search field with the selected student's roll number
                int row = ExaminationDetails.this.table.getSelectedRow();
                ExaminationDetails.this.search.setText(ExaminationDetails.this.table.getModel().getValueAt(row, 2).toString());
            }
        });

        // Make the frame visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.submit) {
            // When 'Result' button is clicked, hide the current frame
            this.setVisible(false);
            String rollNumber = this.search.getText();
            // Open the dialog with the student's roll number
            new Marks(rollNumber) {
                @Override
                public void dispose() {
                    super.dispose();
                    // Re-display the ExaminationDetails frame when the Marks dialog is closed
                    ExaminationDetails.this.setVisible(true);
                }
            };
        } else if (ae.getSource() == this.cancel) {
            // Hide the current frame when 'Back' button is clicked
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ExaminationDetails(); // Create and display the ExaminationDetails frame
    }
}
