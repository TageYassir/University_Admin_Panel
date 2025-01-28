package university.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Project extends JFrame implements ActionListener {

    // Constructor for setting up the JFrame and adding components
    Project() {
        this.setSize(1540, 850);  // Set the size of the window
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.png")); // Load the background image
        Image i2 = i1.getImage().getScaledInstance(1500, 750, 1); // Scale the image to fit the window
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3); // Add the image as a JLabel to the JFrame
        this.add(image);
        ImageIcon frameIcon = new ImageIcon(ClassLoader.getSystemResource("icons/imageicon.png"));
        setIconImage(frameIcon.getImage());
        this.setTitle("UEMF");

        // Creating a menu bar and adding menus
        JMenuBar mb = new JMenuBar();

        // New Information Menu
        JMenu newInformation = new JMenu("New Information");
        newInformation.setForeground(Color.BLUE);
        mb.add(newInformation);

        // Adding menu items under "New Information"
        JMenuItem facultyInfo = new JMenuItem("New Teacher Information");
        facultyInfo.setBackground(new Color(192, 223, 239));
        facultyInfo.addActionListener(this); // Adding action listener
        newInformation.add(facultyInfo);

        JMenuItem studentInfo = new JMenuItem("New Student Information");
        studentInfo.setBackground(new Color(192, 223, 239));
        studentInfo.addActionListener(this); // Adding action listener
        newInformation.add(studentInfo);

        // View Details Menu
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.GREEN);
        mb.add(details);

        // Adding menu items under "View Details"
        JMenuItem facultydetails = new JMenuItem("View Teacher Details");
        facultydetails.setBackground(Color.WHITE);
        facultydetails.addActionListener(this); // Adding action listener
        details.add(facultydetails);

        JMenuItem studentdetails = new JMenuItem("View Student Details");
        studentdetails.setBackground(Color.WHITE);
        studentdetails.addActionListener(this); // Adding action listener
        details.add(studentdetails);

        // Apply Leave Menu
        JMenu leave = new JMenu("Apply Leave");
        leave.setForeground(Color.BLUE);
        mb.add(leave);

        // Adding menu items under "Apply Leave"
        JMenuItem facultyleave = new JMenuItem("Teacher Leave");
        facultyleave.setBackground(new Color(192, 223, 239));
        facultyleave.addActionListener(this); // Adding action listener
        leave.add(facultyleave);

        JMenuItem studentleave = new JMenuItem("Student Leave");
        studentleave.setBackground(new Color(192, 223, 239));
        studentleave.addActionListener(this); // Adding action listener
        leave.add(studentleave);

        // Leave Details Menu
        JMenu leaveDetails = new JMenu("Leave Details");
        leaveDetails.setForeground(Color.GREEN);
        mb.add(leaveDetails);

        // Adding menu items under "Leave Details"
        JMenuItem facultyleavedetails = new JMenuItem("Teacher Leave Details");
        facultyleavedetails.setBackground(Color.WHITE);
        facultyleavedetails.addActionListener(this); // Adding action listener
        leaveDetails.add(facultyleavedetails);

        JMenuItem studentleavedetails = new JMenuItem("Student Leave Details");
        studentleavedetails.setBackground(Color.WHITE);
        studentleavedetails.addActionListener(this); // Adding action listener
        leaveDetails.add(studentleavedetails);

        // Examination Menu
        JMenu exam = new JMenu("Examination");
        exam.setForeground(Color.BLUE);
        mb.add(exam);

        // Adding menu items under "Examination"
        JMenuItem examinationdetails = new JMenuItem("Examination Results");
        examinationdetails.setBackground(new Color(192, 223, 239));
        examinationdetails.addActionListener(this); // Adding action listener
        exam.add(examinationdetails);

        JMenuItem entermarks = new JMenuItem("Enter Marks");
        entermarks.setBackground(new Color(192, 223, 239));
        entermarks.addActionListener(this); // Adding action listener
        exam.add(entermarks);

        // Fee Details Menu
        JMenu fee = new JMenu("Fee Details");
        fee.setForeground(Color.GREEN);
        mb.add(fee);

        // Adding menu items under "Fee Details"
        JMenuItem feestructure = new JMenuItem("Fee Structure");
        feestructure.setBackground(Color.WHITE);
        feestructure.addActionListener(this); // Adding action listener
        fee.add(feestructure);

        JMenuItem feeform = new JMenuItem("Student Fee Form");
        feeform.setBackground(Color.WHITE);
        feeform.addActionListener(this); // Adding action listener
        fee.add(feeform);

        // Utility Menu
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.BLUE);
        mb.add(utility);

        // Adding menu items under "Utility"
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(new Color(192, 223, 239));
        notepad.addActionListener(this); // Adding action listener
        utility.add(notepad);

        JMenuItem calc = new JMenuItem("Calculator");
        calc.setBackground(new Color(192, 223, 239));
        calc.addActionListener(this); // Adding action listener
        utility.add(calc);

        // Exit Menu
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.GREEN);
        mb.add(exit);

        // Adding exit menu item
        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this); // Adding action listener
        exit.add(ex);

        // Set the menu bar for the JFrame
        this.setJMenuBar(mb);
        this.setVisible(true);  // Make the frame visible
    }

    // Action listener for menu item clicks
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand(); // Get the action command of the selected menu item

        // Exit the application
        if (msg.equals("Exit")) {
            this.setVisible(false);
        }

        // Open Calculator
        else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        // Open Notepad
        else if (msg.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        // Open Add Teacher Window
        else if (msg.equals("New Teacher Information")) {
            new AddTeacher();
        }

        // Open Add Student Window
        else if (msg.equals("New Student Information")) {
            new AddStudent();
        }

        // Open Teacher Details Window
        else if (msg.equals("View Teacher Details")) {
            new TeacherDetails();
        }

        // Open Student Details Window
        else if (msg.equals("View Student Details")) {
            new StudentDetails();
        }

        // Open Teacher Leave Window
        else if (msg.equals("Teacher Leave")) {
            new TeacherLeave();
        }

        // Open Student Leave Window
        else if (msg.equals("Student Leave")) {
            new StudentLeave();
        }

        // Open Faculty Leave Details Window
        else if (msg.equals("Teacher Leave Details")) {
            new TeacherLeaveDetails();
        }

        // Open Student Leave Details Window
        else if (msg.equals("Student Leave Details")) {
            new StudentLeaveDetails();
        }

        // Open Enter Marks Window
        else if (msg.equals("Enter Marks")) {
            new EnterMarks();
        }

        // Open Examination Details Window
        else if (msg.equals("Examination Results")) {
            new ExaminationDetails();
        }

        // Open Fee Structure Window
        else if (msg.equals("Fee Structure")) {
            new FeeStructure();
        }


        // Open Student Fee Form Window
        else if (msg.equals("Student Fee Form")) {
            new StudentFeeForm();
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new Project();
    }
}
