package university.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class EnterMarks extends JFrame implements ActionListener {
    // Declaring necessary UI components
    JTextField cRnumber;
    JButton search;
    JComboBox cbsemester;
    JButton cancel, submit, addSubject, modifySubject, deleteSubject, showMarks;

    // List to hold subjects and marks
    List<String> subjects = new ArrayList<>();
    List<String> marks = new ArrayList<>();

    // Constructor for initializing the EnterMarks form
    EnterMarks() {
        // Set the size, location, and layout of the frame
        this.setSize(500, 500);
        this.setTitle("Enter Marks");
        this.setLocationRelativeTo(null); // Center the window on the screen
        this.setLayout(new GridBagLayout()); // Using GridBagLayout for centering
        this.getContentPane().setBackground(new Color(192, 223, 239));

        // Create a GridBagConstraints object for managing layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding


        this.search = new JButton("Search");
        gbc.gridx = 2; // Add a new column for the search button
        gbc.gridy = 1; // Align it with the roll number field
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        this.search.addActionListener(this);
        this.add(this.search, gbc);


        // Form heading
        JLabel heading = new JLabel("Enter Marks of Student");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(heading, gbc);

        // Roll Number Label and Text Field for entering roll number
        JLabel lblrnumber = new JLabel("Enter R Number");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(lblrnumber, gbc);

        this.cRnumber = new JTextField(15); // Replaced Choice with JTextField
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(this.cRnumber, gbc);

        // Semester Label and ComboBox for selecting semester
        JLabel lblsemester = new JLabel("Select Semester");
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblsemester, gbc);

        String[] semester = new String[]{"1st Semester", "2nd Semester", "3rd Semester", "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester", "9th Semester", "10th Semester"};
        this.cbsemester = new JComboBox(semester);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(this.cbsemester, gbc);

        // Add Subject Button
        this.addSubject = new JButton("Add Subject");
        gbc.gridx = 0;
        gbc.gridy = 3;
        addSubject.setBackground(Color.BLACK);
        addSubject.setForeground(Color.WHITE);
        this.addSubject.addActionListener(this);
        this.add(this.addSubject, gbc);

        // Modify Subject Button
        this.modifySubject = new JButton("Modify Subject");
        gbc.gridx = 1;
        gbc.gridy = 3;
        modifySubject.setBackground(Color.BLACK);
        modifySubject.setForeground(Color.WHITE);
        this.modifySubject.addActionListener(this);
        this.add(this.modifySubject, gbc);

        // Delete Subject Button
        this.deleteSubject = new JButton("Delete Subject");
        gbc.gridx = 0;
        gbc.gridy = 4;
        deleteSubject.setBackground(Color.BLACK);
        deleteSubject.setForeground(Color.WHITE);
        this.deleteSubject.addActionListener(this);
        this.add(this.deleteSubject, gbc);

        // Show Marks Button
        this.showMarks = new JButton("Show Marks");
        gbc.gridx = 1;
        gbc.gridy = 4;
        showMarks.setBackground(Color.BLACK);
        showMarks.setForeground(Color.WHITE);
        this.showMarks.addActionListener(this);
        this.add(this.showMarks, gbc);

        // Submit and Cancel buttons
        this.submit = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 5;
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        this.submit.addActionListener(this);
        this.add(this.submit, gbc);

        this.cancel = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 5;
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        this.cancel.addActionListener(this);
        this.add(this.cancel, gbc);

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // Make the form visible
        this.setVisible(true);
    }

    // Action listener for handling button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.submit) {
            try {
                Conn c = new Conn();
                String Rnumber = this.cRnumber.getText(); // Get text from JTextField
                String semester = (String) this.cbsemester.getSelectedItem();

                // Insert each subject and its marks into the combined table
                for (int i = 0; i < subjects.size(); i++) {
                    String query = "INSERT INTO subject_marks (regestration, semester, subject_name, marks) VALUES ('"
                            + Rnumber + "', '" + semester + "', '" + subjects.get(i) + "', '" + marks.get(i) + "')";
                    c.s.executeUpdate(query);
                }

                // Show success message
                JOptionPane.showMessageDialog(this, "Marks Inserted Successfully");

                // Clear subjects and marks
                subjects.clear();
                marks.clear();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == this.addSubject) {
            String newSubject = JOptionPane.showInputDialog(this, "Enter new subject name:");
            if (newSubject != null && !newSubject.isEmpty()) {
                try {
                    Conn c = new Conn();
                    String Rnumber = this.cRnumber.getText(); // Get text from JTextField
                    String semester = (String) this.cbsemester.getSelectedItem();

                    // Check if the subject already exists in the database
                    String query = "SELECT * FROM subject_marks WHERE regestration = '" + Rnumber + "' AND semester = '"
                            + semester + "' AND subject_name = '" + newSubject + "'";
                    ResultSet rs = c.s.executeQuery(query);

                    if (rs.next()) {
                        // Subject already exists
                        JOptionPane.showMessageDialog(this, "Subject already exists for this student and semester.");
                    } else {
                        // If the subject doesn't exist, prompt for marks
                        String marksForSubject = JOptionPane.showInputDialog(this, "Enter marks for " + newSubject + ":");
                        if (marksForSubject != null && !marksForSubject.isEmpty()) {
                            subjects.add(newSubject);
                            marks.add(marksForSubject);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == this.modifySubject) {
            try {
                // Retrieve the entered roll number and selected semester
                String Rnumber = this.cRnumber.getText().trim();
                String semester = (String) this.cbsemester.getSelectedItem();

                // Validate input
                if (Rnumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a roll number and search first.");
                    return;
                }

                // Fetch the subjects for the selected student and semester
                Conn c = new Conn();
                String query = "SELECT subject_name FROM subject_marks WHERE regestration = '" + Rnumber + "' AND semester = '" + semester + "'";
                ResultSet rs = c.s.executeQuery(query);

                // Populate a list of subjects from the database
                List<String> dbSubjects = new ArrayList<>();
                while (rs.next()) {
                    dbSubjects.add(rs.getString("subject_name"));
                }

                // Check if any subjects are available
                if (dbSubjects.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No subjects found for the selected student and semester.");
                    return;
                }

                // Prompt the user to select a subject to modify
                String subjectToModify = (String) JOptionPane.showInputDialog(
                        this,
                        "Select a subject to modify:",
                        "Modify Subject",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        dbSubjects.toArray(),
                        dbSubjects.get(0)
                );

                if (subjectToModify != null) {
                    // Prompt the user for new marks
                    String newMarks = JOptionPane.showInputDialog(this, "Enter new marks for " + subjectToModify + ":");

                    // Validate the input for new marks
                    if (newMarks != null && !newMarks.isEmpty()) {
                        try {
                            int marksValue = Integer.parseInt(newMarks); // Ensure the input is numeric

                            // Update the marks in the database
                            String updateQuery = "UPDATE subject_marks SET marks = '" + marksValue + "' WHERE regestration = '"
                                    + Rnumber + "' AND semester = '" + semester + "' AND subject_name = '" + subjectToModify + "'";
                            c.s.executeUpdate(updateQuery);

                            JOptionPane.showMessageDialog(this, "Marks updated successfully.");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Please enter valid numeric marks.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Marks cannot be empty. Please try again.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to modify the subject. Please try again.");
            }
    } else if (ae.getSource() == this.deleteSubject) {
            try {
                Conn c = new Conn();
                String Rnumber = this.cRnumber.getText().trim(); // Corrected
                String semester = (String) this.cbsemester.getSelectedItem();

                if (Rnumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a roll number and search first.");
                    return;
                }

                // Fetch the subjects for the selected student and semester
                String query = "SELECT subject_name FROM subject_marks WHERE regestration = '" + Rnumber + "' AND semester = '" + semester + "'";
                ResultSet rs = c.s.executeQuery(query);

                List<String> dbSubjects = new ArrayList<>();
                while (rs.next()) {
                    dbSubjects.add(rs.getString("subject_name"));
                }

                // Check if there are any subjects to delete
                if (dbSubjects.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No subjects found for the selected student and semester.");
                    return;
                }

                // Display the subjects in a dropdown for selection
                String subjectToDelete = (String) JOptionPane.showInputDialog(
                        this,
                        "Select a subject to delete:",
                        "Delete Subject",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        dbSubjects.toArray(),
                        dbSubjects.get(0)
                );

                if (subjectToDelete != null) {
                    // Confirm the deletion
                    int confirmation = JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete " + subjectToDelete + "?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        // Delete the selected subject from the database
                        String deleteQuery = "DELETE FROM subject_marks WHERE regestration = '" + Rnumber + "' AND semester = '"
                                + semester + "' AND subject_name = '" + subjectToDelete + "'";
                        c.s.executeUpdate(deleteQuery);

                        JOptionPane.showMessageDialog(this, "Subject deleted successfully.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to delete the subject.");
            }
    } else if (ae.getSource() == this.showMarks) {
            try {
                // Simply instantiate and display ExaminationDetails
                new ExaminationDetails().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to load Examination Details.");
            }
        }else if (ae.getSource() == this.search) {
            try {
                Conn c = new Conn();
                String Rnumber = this.cRnumber.getText().trim(); // Get text from JTextField

                // Validate input
                if (Rnumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a roll number to search.");
                    return;
                }

                // Query to check if the roll number exists
                String query = "SELECT * FROM student WHERE regestration = '" + Rnumber + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    // If roll number exists, display a success message with student details
                    String name = rs.getString("name");
                    String branch = rs.getString("branch");
                    JOptionPane.showMessageDialog(this, "Student Found:\nName: " + name + "\nBranch: " + branch);
                } else {
                    // If roll number doesn't exist, show an error message
                    JOptionPane.showMessageDialog(this, "Roll Number not found. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while searching for the student.");
            }
        }
        else if (ae.getSource() == this.cancel) {
            this.setVisible(false);
        }
    }

    // Main method to run the EnterMarks form
    public static void main(String[] args) {
        new EnterMarks(); // Create and display the EnterMarks form
    }
}
