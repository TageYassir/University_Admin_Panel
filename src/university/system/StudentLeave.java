package university.system;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class StudentLeave extends JFrame implements ActionListener {
    JTextField txtRollNo;   // Input field for roll number
    JDateChooser dcdate;  // Date chooser for selecting the leave date
    JButton submit;   // Submit button
    JButton cancel;   // Cancel button
    JButton search;   // Search button

    JLabel lblName, lblFamilyName, lblBranch;  // Labels to show student's details
    String name, fname, branch;  // Variables to store student details

    // Constructor to initialize the GUI components
    StudentLeave() {
        this.setSize(500, 550);   // Set the size of the JFrame
        this.setLocationRelativeTo(null);   // Center the window on the screen
        this.setLayout(null);   // Set layout to null for custom positioning
        this.getContentPane().setBackground(new Color(192, 223, 239));   // Set background color to white
        this.setTitle("Student Leave");

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Create and add a heading label
        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setFont(new Font("Tahoma", 1, 20));
        heading.setBounds(140, 50, 300, 30);   // Set the bounds to position the heading in the center
        this.add(heading);

        // Label and input field for entering roll number
        JLabel lblrollno = new JLabel("Enter R Number");
        lblrollno.setFont(new Font("Tahoma", 0, 18));
        lblrollno.setBounds(150, 100, 200, 20);   // Center the label
        this.add(lblrollno);

        this.txtRollNo = new JTextField();
        this.txtRollNo.setFont(new Font("Tahoma", 0, 18));
        this.txtRollNo.setBounds(150, 130, 200, 25);   // Center the input field
        this.add(this.txtRollNo);

        // Label and date picker for selecting the leave date
        JLabel lbldate = new JLabel("Date");
        lbldate.setFont(new Font("Tahoma", 0, 18));
        lbldate.setBounds(150, 180, 200, 20);   // Center the label
        this.add(lbldate);

        this.dcdate = new JDateChooser();  // JDateChooser for selecting date
        this.dcdate.setBounds(150, 210, 200, 25);   // Center the date picker
        this.add(this.dcdate);

        // Label to display student's name, family name, and branch
        lblName = new JLabel("Name: ");
        lblName.setFont(new Font("Tahoma", 0, 18));
        lblName.setBounds(150, 240, 200, 20);   // Center the label
        this.add(lblName);

        lblFamilyName = new JLabel("Family Name: ");
        lblFamilyName.setFont(new Font("Tahoma", 0, 18));
        lblFamilyName.setBounds(150, 270, 200, 20);   // Center the label
        this.add(lblFamilyName);

        lblBranch = new JLabel("Branch: ");
        lblBranch.setFont(new Font("Tahoma", 0, 18));
        lblBranch.setBounds(150, 300, 200, 20);   // Center the label
        this.add(lblBranch);

        // Submit button to confirm the leave request
        this.submit = new JButton("Submit");
        this.submit.setBackground(Color.BLACK);
        this.submit.setForeground(Color.WHITE);
        this.submit.addActionListener(this);  // Action listener for submit button
        this.submit.setFont(new Font("Tahoma", 1, 15));
        this.submit.setBounds(150, 350, 100, 25);   // Center the button
        this.add(this.submit);

        // Search button to search for the student by roll number, placed near the submit button
        this.search = new JButton("Search");
        this.search.setBackground(Color.BLACK);
        this.search.setForeground(Color.WHITE);
        this.search.addActionListener(this);  // Action listener for search button
        this.search.setFont(new Font("Tahoma", 1, 15));
        this.search.setBounds(270, 350, 100, 25);   // Placed near the submit button
        this.add(this.search);

        // Cancel button to close the form without submitting
        this.cancel = new JButton("Cancel");
        this.cancel.setBackground(Color.BLACK);
        this.cancel.setForeground(Color.WHITE);
        this.cancel.addActionListener(this);  // Action listener for cancel button
        this.cancel.setFont(new Font("Tahoma", 1, 15));
        this.cancel.setBounds(150, 390, 100, 25);   // Center the button
        this.add(this.cancel);

        // Set the form to be visible
        this.setVisible(true);
    }

    // Action listener method to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        // If search button is clicked, fetch the student's details based on roll number
        if (ae.getSource() == this.search) {
            String regestration = this.txtRollNo.getText();

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from student where regestration = '" + regestration + "'");

                if (rs.next()) {
                    // Retrieve student details
                    name = rs.getString("name");
                    fname = rs.getString("fname");
                    branch = rs.getString("branch");

                    // Display the student's details in the labels
                    lblName.setText("Name: " + name);
                    lblFamilyName.setText("Family Name: " + fname);
                    lblBranch.setText("Branch: " + branch);
                } else {
                    JOptionPane.showMessageDialog(null, "Student not found!");
                    lblName.setText("Name: ");
                    lblFamilyName.setText("Family Name: ");
                    lblBranch.setText("Branch: ");
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }

        // If submit button is clicked, confirm leave and insert the request
        else if (ae.getSource() == this.submit) {
            String regestration = this.txtRollNo.getText();
            String date = ((JTextField)this.dcdate.getDateEditor().getUiComponent()).getText();

            try {
                Conn c = new Conn();
                String message = "Are you sure you want to apply for leave?\n\n"
                        + "Name: " + name + "\n"
                        + "Family Name: " + fname + "\n"
                        + "Branch: " + branch + "\n"
                        + "Leave Date: " + date;

                int option = JOptionPane.showConfirmDialog(this, message, "Confirm Leave", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    // Insert the leave request into the database
                    String query = "insert into studentleave (regestration, name, fname, branch, leave_date) values('"
                            + regestration + "', '" + name + "', '" + fname + "', '" + branch + "', '" + date + "')";
                    c.s.executeUpdate(query);  // Execute the insert query to save leave data

                    // Delete the student from the student table after leave confirmation
                    String deleteQuery = "DELETE FROM student WHERE regestration = '" + regestration + "'";
                    c.s.executeUpdate(deleteQuery);  // Execute the delete query

                    JOptionPane.showMessageDialog(null, "Leave Confirmed and Student Deleted");
                    this.setVisible(false);  // Close the form after successful submission
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }
        // If cancel button is clicked, close the form without making any changes
        else if (ae.getSource() == this.cancel) {
            this.setVisible(false);
        }
    }

    // Main method to run the StudentLeave form
    public static void main(String[] args) {
        new StudentLeave();  // Open the Student Leave form
    }
}
