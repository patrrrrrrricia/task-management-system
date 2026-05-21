package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {
    // Definirea culorilor personalizate
    private final Color PINK_ACCENT = new Color(218, 131, 147);
    private final Color LIGHT_PINK = new Color(254, 225, 227);
    private final Color BG_WHITE = Color.WHITE;

    private JTabbedPane tabbedPane = new JTabbedPane();

    // Componente tab 1
    private DefaultTableModel modelAngajati = new DefaultTableModel(new String[]{"ID", "Nume", "Ore lucrate"}, 0);
    private JTable tableAngajati = new JTable(modelAngajati);
    private JTextField txtIdEmp = new JTextField(5), txtNameEmp = new JTextField(10);
    private JButton btnAddEmp = createStyledButton("Adauga angajat"), btnDeleteEmp = createStyledButton("Sterge angajat");

    // Componente tab 2
    private DefaultTableModel modelTaskuri = new DefaultTableModel(new String[]{"ID Task", "Tip", "Status", "Durata"}, 0);
    private JTable tableTaskuri = new JTable(modelTaskuri);
    private JComboBox<String> comboAngajati = new JComboBox<>(), comboTipTask = new JComboBox<>(new String[]{"Simplu", "Complex"});
    private JTextField txtIdTask = new JTextField(5), txtStartHour = new JTextField(5), txtEndHour = new JTextField(5);
    private JButton btnAdaugaTask = createStyledButton("Adauga task"), btnModifyStatus = createStyledButton("Schimba status"), btnDeleteTask = createStyledButton("Sterge task");
    private JTextField txtParentId = new JTextField(5), txtChildId = new JTextField(5);
    private JButton btnAddSubtask = createStyledButton("Add subtask to parent");

    // Componente tab 3
    private JTextArea txtStats = new JTextArea();
    private JButton btnShowStats = createStyledButton("Show statistics");

    public View(String title) {
        super(title);

        // Configurări globale pentru aspectul vizual
        getContentPane().setBackground(BG_WHITE);
        UIManager.put("TabbedPane.selected", LIGHT_PINK);

        tabbedPane.setBackground(BG_WHITE);

        // --- constructie tab angajati ---
        JPanel p1 = createStyledPanel();
        p1.add(new JScrollPane(tableAngajati), BorderLayout.CENTER);
        JPanel p1Sud = new JPanel(new FlowLayout()); p1Sud.setBackground(BG_WHITE);
        p1Sud.add(new JLabel("ID:")); p1Sud.add(txtIdEmp); p1Sud.add(new JLabel("Nume:")); p1Sud.add(txtNameEmp);
        p1Sud.add(btnAddEmp); p1Sud.add(btnDeleteEmp); p1.add(p1Sud, BorderLayout.SOUTH);

        // --- constructie tab taskuri ---
        JPanel p2 = createStyledPanel();
        p2.add(new JScrollPane(tableTaskuri), BorderLayout.CENTER);
        JPanel p2Nord = new JPanel(new FlowLayout(FlowLayout.CENTER)); p2Nord.setBackground(BG_WHITE);
        p2Nord.add(btnDeleteTask); p2.add(p2Nord, BorderLayout.NORTH);

        JPanel p2Input = new JPanel(new GridLayout(6, 2, 5, 5)); p2Input.setBackground(BG_WHITE);
        p2Input.add(new JLabel("Angajat:")); p2Input.add(comboAngajati);
        p2Input.add(new JLabel("ID Task:")); p2Input.add(txtIdTask);
        p2Input.add(new JLabel("Tip:")); p2Input.add(comboTipTask);
        p2Input.add(new JLabel("Ora Start:")); p2Input.add(txtStartHour);
        p2Input.add(new JLabel("Ora End:")); p2Input.add(txtEndHour);
        p2Input.add(btnAdaugaTask); p2Input.add(btnModifyStatus);

        JPanel pGrupare = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pGrupare.setBackground(BG_WHITE);
        pGrupare.setBorder(BorderFactory.createTitledBorder("Grupare Taskuri (Composite)"));
        pGrupare.add(new JLabel("Parent ID:")); pGrupare.add(txtParentId);
        pGrupare.add(new JLabel("Child ID:")); pGrupare.add(txtChildId);
        pGrupare.add(btnAddSubtask);

        JPanel p2Sud = new JPanel(new BorderLayout()); p2Sud.setBackground(BG_WHITE);
        p2Sud.add(p2Input, BorderLayout.NORTH);
        p2Sud.add(pGrupare, BorderLayout.SOUTH);
        p2.add(p2Sud, BorderLayout.SOUTH);

        //--- constructie tab statistici ---
        JPanel p3 = createStyledPanel();
        txtStats.setEditable(false);
        txtStats.setFont(new Font("Monospaced", Font.PLAIN, 13));
        p3.add(new JScrollPane(txtStats), BorderLayout.CENTER);
        p3.add(btnShowStats, BorderLayout.SOUTH);

        tabbedPane.addTab("Angajati", p1);
        tabbedPane.addTab("Taskuri", p2);
        tabbedPane.addTab("Statistici", p3);

        this.add(tabbedPane);
        this.setSize(750, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private JPanel createStyledPanel() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBackground(BG_WHITE);
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        return p;
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(PINK_ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        // Efect de hover simplu
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(LIGHT_PINK); btn.setForeground(Color.BLACK); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(PINK_ACCENT); btn.setForeground(Color.WHITE); }
        });
        return btn;
    }

    // --- Gettere ---
    public JButton getBtnAddEmp() { return btnAddEmp; }
    public JButton getBtnDeleteEmp() { return btnDeleteEmp; }
    public JButton getBtnAdaugaTask() { return btnAdaugaTask; }
    public JButton getBtnModifyStatus() { return btnModifyStatus; }
    public JButton getBtnDeleteTask() { return btnDeleteTask; }
    public JButton getBtnAddSubtask() { return btnAddSubtask; }
    public JButton getBtnShowStats() { return btnShowStats; }
    public JComboBox<String> getComboAngajati() { return comboAngajati; }
    public JComboBox<String> getComboTipTask() { return comboTipTask; }
    public JTable getTableAngajati() { return tableAngajati; }
    public JTable getTableTaskuri() { return tableTaskuri; }
    public DefaultTableModel getModelAngajati() { return modelAngajati; }
    public DefaultTableModel getModelTaskuri() { return modelTaskuri; }
    public String getEmpId() { return txtIdEmp.getText(); }
    public String getEmpName() { return txtNameEmp.getText(); }
    public String getTaskIdText() { return txtIdTask.getText(); }
    public String getStartHourText() { return txtStartHour.getText(); }
    public String getEndHourText() { return txtEndHour.getText(); }
    public String getParentIdText() { return txtParentId.getText(); }
    public String getChildIdText() { return txtChildId.getText(); }
    public void setStatsText(String t) { txtStats.setText(t); }
    public void clearFields() {
        txtIdEmp.setText(""); txtNameEmp.setText(""); txtIdTask.setText("");
        txtStartHour.setText(""); txtEndHour.setText(""); txtParentId.setText(""); txtChildId.setText("");
    }
}