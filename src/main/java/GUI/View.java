package GUI;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JTextField txtIdEmp = new JTextField(5), txtNameEmp = new JTextField(10);
    private JTextField txtIdTask = new JTextField(5), txtStart = new JTextField(3), txtEnd = new JTextField(3);
    private JButton btnAddEmp = new JButton("Add Employee");
    private JButton btnAddTask = new JButton("Add Simple Task");
    private JButton btnStats = new JButton("Show Statistics");
    private JTextArea display = new JTextArea(10, 30);

    public View(String name) {
        super(name);
        JPanel panel = new JPanel(new GridLayout(0, 2));

        panel.add(new JLabel("Emp ID & Name:"));
        JPanel p1 = new JPanel(); p1.add(txtIdEmp); p1.add(txtNameEmp);
        panel.add(p1);

        panel.add(new JLabel("Task ID, Start, End:"));
        JPanel p2 = new JPanel(); p2.add(txtIdTask); p2.add(txtStart); p2.add(txtEnd);
        panel.add(p2);

        panel.add(btnAddEmp);
        panel.add(btnAddTask);
        panel.add(btnStats);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(display), BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    // Getters pentru Controller
    public JButton getBtnAddEmp() { return btnAddEmp; }
    public JButton getBtnAddTask() { return btnAddTask; }
    public JButton getBtnStats() { return btnStats; }
    public String getEmpId() { return txtIdEmp.getText(); }
    public String getEmpName() { return txtNameEmp.getText(); }
    public String getTaskId() { return txtIdTask.getText(); }
    public String getStart() { return txtStart.getText(); }
    public String getEnd() { return txtEnd.getText(); }
    public void setDisplay(String text) { display.setText(text); }
}