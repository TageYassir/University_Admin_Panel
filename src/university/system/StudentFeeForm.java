package university.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class StudentFeeForm extends JFrame implements ActionListener {

    JTextField tfrollno;  // Input field for roll number
    JComboBox cbbranch;   // Dropdown list for branch selection
    JComboBox cbsemester; // Dropdown list for semester selection
    JLabel labelname;     // Label to display student name
    JLabel labelfname;    // Label to display family name
    JLabel labeltotal;    // Label to display total fee
    JButton search;       // Button to search student by roll number
    JButton update;       // Button to update the total payable fee
    JButton pay;          // Button to submit the fee payment
    JButton back;         // Button to go back/close the form

    StudentFeeForm() {
        this.setSize(500, 500);
        this.setTitle("Student Fee Form"); // Set size of the JFrame
        this.setLocationRelativeTo(null);  // Center the JFrame on the screen
        this.setLayout(new GridBagLayout());  // Use GridBagLayout for centering
        this.getContentPane().setBackground(new Color(192, 223, 239));  // Set background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);  // Padding for components
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Stretch components horizontally

        JLabel heading = new JLabel("Student Fee Form", JLabel.CENTER);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        gbc.gridwidth = 4; // Span four columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(heading, gbc);

        // Roll Number Input
        JLabel lblrollnumber = new JLabel("Enter R Number: ");
        lblrollnumber.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(lblrollnumber, gbc);

        tfrollno = new JTextField();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        this.add(tfrollno, gbc);

        // Name and Family Name Labels
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblname, gbc);

        labelname = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(labelname, gbc);

        JLabel lblfname = new JLabel("Family Name");
        lblfname.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(lblfname, gbc);

        labelfname = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(labelfname, gbc);

        // Branch and Semester Selection
        JLabel lblbranch = new JLabel("Branch");
        lblbranch.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(lblbranch, gbc);

        String[] branches = new String[]{"EIDIA", "EPS", "EBS", "EIB"};
        cbbranch = new JComboBox(branches);
        gbc.gridx = 2;
        gbc.gridy = 4;
        this.add(cbbranch, gbc);

        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(lblsemester, gbc);

        String[] semesters = new String[]{"Semester1", "Semester2", "Semester3", "Semester4", "Semester5", "Semester6", "Semester7", "Semester8", "Semester9", "Semester10"};
        cbsemester = new JComboBox(semesters);
        gbc.gridx = 2;
        gbc.gridy = 5;
        this.add(cbsemester, gbc);

        // Total Fee Label
        JLabel lbltotal = new JLabel("Total Payable");
        lbltotal.setFont(new Font("Tahoma", 1, 16));
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(lbltotal, gbc);

        labeltotal = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 6;
        this.add(labeltotal, gbc);

        // Buttons
        search = new JButton("Search");
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.setFont(new Font("Tahoma", 1, 16));
        search.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        this.add(search, gbc);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Tahoma", 1, 16));
        update.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 7;
        this.add(update, gbc);

        pay = new JButton("Pay Fee");
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.setFont(new Font("Tahoma", 1, 16));
        pay.addActionListener(this);
        gbc.gridx = 2;
        gbc.gridy = 7;
        this.add(pay, gbc);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", 1, 16));
        back.addActionListener(this);
        gbc.gridx = 3;
        gbc.gridy = 7;
        this.add(back, gbc);

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String rollno = tfrollno.getText().trim();

        // Validate input
        if (rollno.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a R number.");
            return;
        }

        if (ae.getSource() == search) {
            try {
                Conn c = new Conn();
                String query = "select * from student where regestration='" + rollno + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    labelname.setText(rs.getString("name"));
                    labelfname.setText(rs.getString("fname"));
                } else {
                    JOptionPane.showMessageDialog(null, "R number not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            try {
                Conn c = new Conn();
                String query = "select * from fee where branch='" + cbbranch.getSelectedItem() + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    String semester = (String) cbsemester.getSelectedItem();
                    labeltotal.setText(rs.getString(semester));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == pay) {
            try {
                Conn c = new Conn();
                String branch = (String) cbbranch.getSelectedItem();
                String semester = (String) cbsemester.getSelectedItem();

                // Check if the fee is already paid
                String checkQuery = "select * from fee_status where regestration='" + rollno +
                        "' and branch='" + branch + "' and semester='" + semester + "'";
                ResultSet rs = c.s.executeQuery(checkQuery);

                if (rs.next()) {
                    String isPaid = rs.getString("is_paid");
                    if ("Yes".equalsIgnoreCase(isPaid)) {
                        JOptionPane.showMessageDialog(null, "Fee already paid for this semester.");
                    } else {
                        // Update fee status to "Yes"
                        String updateQuery = "update fee_status set is_paid='Yes' where regestration='" + rollno +
                                "' and branch='" + branch + "' and semester='" + semester + "'";
                        c.s.executeUpdate(updateQuery);
                        JOptionPane.showMessageDialog(null, "Fee payment successful.");
                    }
                } else {
                    // Insert new record if not exists
                    String insertQuery = "insert into fee_status (regestration, branch, semester, is_paid) values ('" +
                            rollno + "', '" + branch + "', '" + semester + "', 'Yes')";
                    c.s.executeUpdate(insertQuery);
                    JOptionPane.showMessageDialog(null, "Fee payment successful.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.setVisible(false);
        }
    }
    public static void main(String[] args) {
        new StudentFeeForm();
    }
}
