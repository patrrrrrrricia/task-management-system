package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

//CLASA CE CONSTRUIESTE INTERFATA APLICATIEI
public class View extends JFrame {
    //panou cu tab-uri pentru a organiza aplicatia in 3 sectiuni
    private JTabbedPane tabbedPane = new JTabbedPane();

    //tab 1: componente pentru gestiunea angajatilor
    private DefaultTableModel modelAngajati = new DefaultTableModel(new String[]{"ID", "Nume", "Ore lucrate"}, 0);
    private JTable tableAngajati = new JTable(modelAngajati);
    private JTextField txtIdEmp = new JTextField(5), txtNameEmp = new JTextField(10);
    private JButton btnAddEmp = new JButton("Adauga angajat"), btnDeleteEmp = new JButton("Sterge angajat");

    //tab 2: componente pentru gestiunea task urilor
    private DefaultTableModel modelTaskuri = new DefaultTableModel(new String[]{"ID Task", "Tip", "Status", "Durata"}, 0);
    private JTable tableTaskuri = new JTable(modelTaskuri);
    private JComboBox<String> comboAngajati = new JComboBox<>(), comboTipTask = new JComboBox<>(new String[]{"Simplu", "Complex"});
    private JTextField txtIdTask = new JTextField(5), txtStartHour = new JTextField(5), txtEndHour = new JTextField(5);
    private JButton btnAdaugaTask = new JButton("Adauga task"), btnModifyStatus = new JButton("Schimba status"), btnDeleteTask = new JButton("Sterge task");

    //elemente pt composite pattern: id parinte si id copil pentru grupare
    private JTextField txtParentId = new JTextField(5), txtChildId = new JTextField(5);
    private JButton btnAddSubtask = new JButton("Add subtask to parent");

    //tab 3: sectiune pt raportul de statistici
    private JTextArea txtStats = new JTextArea();
    private JButton btnShowStats = new JButton("Show statistics");

    //constructorul care aranjeaza elementele in fereastra
    public View(String title) {
        super(title);

        // --- constructie tab angajati ---
        JPanel p1 = new JPanel(new BorderLayout(10,10)); p1.setBorder(new EmptyBorder(10,10,10,10));
        p1.add(new JScrollPane(tableAngajati), BorderLayout.CENTER);
        JPanel p1Sud = new JPanel(); p1Sud.add(new JLabel("ID:")); p1Sud.add(txtIdEmp); p1Sud.add(new JLabel("Nume:")); p1Sud.add(txtNameEmp);
        p1Sud.add(btnAddEmp); p1Sud.add(btnDeleteEmp); p1.add(p1Sud, BorderLayout.SOUTH);

        // --- constructie tab taskuri ---
        JPanel p2 = new JPanel(new BorderLayout(10,10)); p2.setBorder(new EmptyBorder(10,10,10,10));
        p2.add(new JScrollPane(tableTaskuri), BorderLayout.CENTER);

        JPanel p2Nord = new JPanel(new FlowLayout(FlowLayout.CENTER)); p2Nord.add(btnDeleteTask); p2.add(p2Nord, BorderLayout.NORTH);

        //panou pt introducerea datelor unui task nou
        JPanel p2Input = new JPanel(new GridLayout(6, 2, 5, 5));
        p2Input.add(new JLabel("Angajat:")); p2Input.add(comboAngajati);
        p2Input.add(new JLabel("ID Task:")); p2Input.add(txtIdTask);
        p2Input.add(new JLabel("Tip:")); p2Input.add(comboTipTask);
        p2Input.add(new JLabel("Ora Start:")); p2Input.add(txtStartHour);
        p2Input.add(new JLabel("Ora End:")); p2Input.add(txtEndHour);
        p2Input.add(btnAdaugaTask); p2Input.add(btnModifyStatus);

        //panou special pt gruparea task urilor (composite)
        JPanel pGrupare = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pGrupare.setBorder(BorderFactory.createTitledBorder("Grupare Taskuri (Composite)"));
        pGrupare.add(new JLabel("Parent ID:")); pGrupare.add(txtParentId);
        pGrupare.add(new JLabel("Child ID:")); pGrupare.add(txtChildId);
        pGrupare.add(btnAddSubtask);

        JPanel p2Sud = new JPanel(new BorderLayout());
        p2Sud.add(p2Input, BorderLayout.NORTH);
        p2Sud.add(pGrupare, BorderLayout.SOUTH);
        p2.add(p2Sud, BorderLayout.SOUTH);

        //--- constructie tab statistici ---
        JPanel p3 = new JPanel(new BorderLayout(10,10));
        p3.setBorder(new EmptyBorder(10,10,10,10));

        //setari aspect pt zona de text a statisticilor
        txtStats.setEditable(false);
        txtStats.setFont(new Font("Monospaced", Font.PLAIN, 13)); // font fix pentru aliniere tabele text
        txtStats.setBackground(new Color(248, 248, 248));

        p3.add(new JScrollPane(txtStats), BorderLayout.CENTER);
        p3.add(btnShowStats, BorderLayout.SOUTH);

        //adaugarea celor 3 pagini in panoul principal
        tabbedPane.addTab("Angajati", p1);
        tabbedPane.addTab("Taskuri", p2);
        tabbedPane.addTab("Statistici", p3);

        this.add(tabbedPane);
        this.setSize(750, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // centreaza fereastra pe ecran
    }

    //--- metode getter pt butoane (folosite de controller) ---
    public JButton getBtnAddEmp() { return btnAddEmp; }
    public JButton getBtnDeleteEmp() { return btnDeleteEmp; }
    public JButton getBtnAdaugaTask() { return btnAdaugaTask; }
    public JButton getBtnModifyStatus() { return btnModifyStatus; }
    public JButton getBtnDeleteTask() { return btnDeleteTask; }
    public JButton getBtnAddSubtask() { return btnAddSubtask; }
    public JButton getBtnShowStats() { return btnShowStats; }

    //--- metode getter pt a prelua textul introdus de utilizator ---
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

    //metoda pt a actualiza zona de statistici cu textul primit
    public void setStatsText(String t) { txtStats.setText(t); }

    //curata toate campurile de text dupa o adaugare reusita
    public void clearFields() {
        txtIdEmp.setText(""); txtNameEmp.setText(""); txtIdTask.setText("");
        txtStartHour.setText(""); txtEndHour.setText(""); txtParentId.setText(""); txtChildId.setText("");
    }
}