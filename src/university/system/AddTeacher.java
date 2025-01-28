package university.system;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;
import javax.swing.*;

public class AddTeacher extends JFrame implements ActionListener {
    JTextField tfname, tffname, tfaddress, tfphone, tfemail;
    JLabel labelgenerated;
    JDateChooser dcdob;
    JComboBox<String> cbbranch;
    JButton submit, cancel;
    Random ran = new Random();
    long first4;

    public AddTeacher() {
        // Generate a random roll number for the student
        this.first4 = Math.abs(this.ran.nextLong() % 9000L + 1000L);

        // Set JFrame properties
        this.setTitle("Add New Teacher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        // Use GridBagLayout for dynamic centering
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(192, 223, 239));
        this.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components

        // Heading
        JLabel heading = new JLabel("New Teacher Details", JLabel.CENTER);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        gbc.gridwidth = 2; // Span two columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(heading, gbc);

        // Name
        gbc.gridwidth = 1; // Reset to single column
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Name:", JLabel.RIGHT), gbc);

        tfname = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfname, gbc);

        // Family Name
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Family Name:", JLabel.RIGHT), gbc);

        tffname = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tffname, gbc);

        // Roll Number
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("R Number:", JLabel.RIGHT), gbc);

        labelgenerated = new JLabel("4400" + this.first4, JLabel.LEFT);
        gbc.gridx = 1;
        panel.add(labelgenerated, gbc);

        // Date of Birth
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Date of Birth:", JLabel.RIGHT), gbc);

        dcdob = new JDateChooser();
        gbc.gridx = 1;
        panel.add(dcdob, gbc);

        // Address
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Address:", JLabel.RIGHT), gbc);

        tfaddress = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfaddress, gbc);

        // Phone
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Phone:", JLabel.RIGHT), gbc);

        tfphone = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfphone, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Email :", JLabel.RIGHT), gbc);

        tfemail = new JTextField(15);
        gbc.gridx = 1;
        panel.add(tfemail, gbc);

        // Branch
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(new JLabel("Branch:", JLabel.RIGHT), gbc);

        String[] branch = {"EIDIA", "EPS", "EBS", "EIB"};
        cbbranch = new JComboBox<>(branch);
        gbc.gridx = 1;
        panel.add(cbbranch, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        gbc.gridwidth = 2; // Span two columns
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(buttonPanel, gbc);

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String regestration = labelgenerated.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String branch = (String) cbbranch.getSelectedItem();

            try {
                Conn con = new Conn();

                // Check if a teacher with the same name, family name, and branch already exists
                String checkQuery = "SELECT * FROM teacher WHERE name = '" + name + "' AND fname = '" + fname + "' AND branch = '" + branch + "'";
                ResultSet rs = con.s.executeQuery(checkQuery);

                if (rs.next()) {
                    // If a duplicate is found, display an error message
                    JOptionPane.showMessageDialog(null, "Teacher with the same name, family name, and branch already exists.");
                } else {
                    // If no duplicate is found, insert the new teacher details
                    String query = "INSERT INTO teacher (name, fname, regestration, dob, address, phone, email, branch) " +
                            "VALUES('" + name + "', '" + fname + "', '" + regestration + "', '" + dob + "', '" + address + "', '" + phone + "', '" + email + "', '" + branch + "')";
                    con.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Teacher Details Inserted Successfully");
                    this.setVisible(false); // Close the form after successful submission
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.setVisible(false); // Close the form on cancel
        }
    }

    public static void main(String[] args) {
        new AddTeacher();
    }
}