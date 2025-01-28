package university.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;


public class TeacherDetails extends JFrame implements ActionListener {
    JTextField trollno;
    JTable table;
    JButton searchButton, printButton, addButton, cancelButton;

    TeacherDetails() {
        // Setting up the JFrame layout and components
        this.getContentPane().setBackground(new Color(192, 223, 239));
        this.setLayout(null);

        // Labels and text field for roll number
        JLabel heading = new JLabel("Search by R Number:");
        heading.setBounds(20, 20, 150, 20);
        this.add(heading);
        this.trollno = new JTextField();
        this.trollno.setBounds(180, 20, 150, 20);
        this.add(this.trollno);

        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());

        // JTable setup
        this.table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from teacher");
            this.table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(this.table);
        jsp.setBounds(0, 100, 900, 600);
        this.add(jsp);

        // Buttons for actions
        searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 80, 20);
        searchButton.addActionListener(this);
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        this.add(searchButton);

        printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 20);
        printButton.addActionListener(this);
        printButton.setBackground(Color.BLACK);
        printButton.setForeground(Color.WHITE);
        this.add(printButton);

        addButton = new JButton("Add");
        addButton.setBounds(220, 70, 80, 20);
        addButton.addActionListener(this);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        this.add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(320, 70, 80, 20);
        cancelButton.addActionListener(this);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        this.add(cancelButton);

        this.setSize(900, 700);
        this.setTitle("Teacher Details");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String rollNo = trollno.getText().trim();
            try {
                Conn c = new Conn();
                ResultSet rs;
                if (rollNo.isEmpty()) {
                    rs = c.s.executeQuery("select * from teacher");
                } else {
                    String query = "select * from teacher where regestration = '" + rollNo + "'";
                    rs = c.s.executeQuery(query);
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(null, "Roll number not found.");
                        return;
                    }
                    rs.beforeFirst();
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == addButton) {
            setVisible(false);
            new AddTeacher();
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeacherDetails();
    }
}
