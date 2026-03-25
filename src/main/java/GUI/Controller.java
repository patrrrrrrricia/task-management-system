package GUI;

import businessLogic.*;
import dataAcces.Serialization;
import model.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

//CLASA CE CONTROLEAZA INTERACTIUNEA DINTRE UTILIZATOR SI LOGICA APLICATIEI
public class Controller implements ActionListener {
    private View view;
    private TaskManagement logic;
    private Serialization dao;

    //constructorul in care legam view ul, logica si sistemul de salvare
    public Controller(View v, TaskManagement l, Serialization s) {
        this.view = v;
        this.logic = l;
        this.dao = s;

        //inregistrare listeners pentru butoanele din view
        //spunem fiecarui buton sa anunte acest controller cand este apasat
        this.view.getBtnAddEmp().addActionListener(this);
        this.view.getBtnDeleteEmp().addActionListener(this);
        this.view.getBtnAdaugaTask().addActionListener(this);
        this.view.getBtnModifyStatus().addActionListener(this);
        this.view.getBtnDeleteTask().addActionListener(this);
        this.view.getBtnAddSubtask().addActionListener(this);
        this.view.getBtnShowStats().addActionListener(this);

        //incarcam datele initiale in tabele la pornire
        refreshTables();
    }

    //metoda principala care decide ce sa faca in functie de butonul apasat
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        try {
            // 1.gestiune angajati: adaugare sau stergere
            if (src == view.getBtnAddEmp()) {
                // cream un angajat nou cu datele din campurile de text
                logic.assignTaskToEmployee(new Employee(Integer.parseInt(view.getEmpId()), view.getEmpName()), null);
                saveAndRefresh();
            }
            else if (src == view.getBtnDeleteEmp()) {
                //stergem angajatul selectat din tabel
                int row = view.getTableAngajati().getSelectedRow();
                if (row != -1) {
                    int id = Integer.parseInt(view.getTableAngajati().getValueAt(row, 0).toString());
                    Employee target = findEmployeeById(id);
                    logic.deleteEmployee(target);
                    saveAndRefresh();
                }
            }

            // 2.gestiune task-uri (adaugare)
            else if (src == view.getBtnAdaugaTask()) {
                Employee target = getSelectedEmpFromCombo();
                if (target == null) throw new Exception("selectati un angajat!");

                int idT = Integer.parseInt(view.getTaskIdText());
                //verificam tipul selectat si cream obiectul corespunzator (simplu sau complex)
                Task t = view.getComboTipTask().getSelectedItem().equals("Simplu") ?
                        new SimpleTask(idT, "Uncompleted", Integer.parseInt(view.getStartHourText()), Integer.parseInt(view.getEndHourText())) :
                        new ComplexTask(idT, "Uncompleted");

                logic.assignTaskToEmployee(target, t);
                saveAndRefresh();
            }

            // 3.modificare status sau stergere task selectat
            else if (src == view.getBtnModifyStatus() || src == view.getBtnDeleteTask()) {
                int row = view.getTableTaskuri().getSelectedRow();
                if (row != -1) {
                    int idT = Integer.parseInt(view.getTableTaskuri().getValueAt(row, 0).toString());
                    Employee owner = findOwnerOfTask(idT);
                    if (owner != null) {
                        if (src == view.getBtnModifyStatus()) {
                            logic.modifyTaskStatus(owner, idT, "Completed");
                        } else {
                            logic.deleteTask(owner, idT);
                        }
                        saveAndRefresh();
                    }
                }
            }

            // 4.grupare task-uri (aplicare design pattern composite)
            else if (src == view.getBtnAddSubtask()) {
                int pId = Integer.parseInt(view.getParentIdText());
                int cId = Integer.parseInt(view.getChildIdText());
                boolean ok = logic.addSubtaskToParent(pId, cId);
                if (ok) {
                    JOptionPane.showMessageDialog(view, "grupare realizata! durata s-a actualizat.");
                    saveAndRefresh();
                } else {
                    JOptionPane.showMessageDialog(view, "eroare: verificati id-urile! parintele trebuie sa fie complex.");
                }
            }

            // 5.afisare statistici (cerintele a si b din utility)
            else if (src == view.getBtnShowStats()) {
                StringBuilder sb = new StringBuilder();

                //apelam logica pentru calculul statisticilor per angajat
                var stats = Utility.getTaskStatusStats(logic.getMap());
                sb.append("=== statistici task-uri (punctul b) ===\n");
                sb.append(String.format("%-20s | %-12s | %-12s\n", "angajat", "completate", "necompletate"));
                sb.append("------------------------------------------------------------\n");

                stats.forEach((numeEmp, statusMap) -> {
                    int comp = statusMap.getOrDefault("Completed", 0);
                    int uncomp = statusMap.getOrDefault("Uncompleted", 0);
                    sb.append(String.format("%-20s | %-12d | %-12d\n", numeEmp, comp, uncomp));
                });

                sb.append("\n");

                //apelam logica pentru filtrarea celor care lucreaza peste 40 de ore
                List<String> hardWorkers = Utility.getHardWorkingEmployees(logic.getMap(), logic);
                sb.append("=== angajati cu peste 40 ore (punctul a) ===\n");
                if (hardWorkers.isEmpty()) {
                    sb.append("nu exista angajati care sa depaseasca 40 de ore.\n");
                } else {
                    sb.append("lista (sortata crescator dupa ore):\n");
                    for (String nume : hardWorkers) {
                        sb.append("- ").append(nume).append("\n");
                    }
                }
                //trimitem textul generat catre zona de afisare din interfata
                view.setStatsText(sb.toString());
            }

        } catch (Exception ex) {
            //capturam orice eroare de input (ex: litere in loc de cifre)
            JOptionPane.showMessageDialog(view, "eroare: " + ex.getMessage());
        }
    }

    // --- metode ajutatoare pentru cautare si actualizare ---

    //gaseste cine este principalul unui anumit task
    private Employee findOwnerOfTask(int taskId) {
        for (Map.Entry<Employee, List<Task>> entry : logic.getMap().entrySet()) {
            for (Task t : entry.getValue()) {
                if (t != null && t.getIdTask() == taskId) return entry.getKey();
            }
        }
        return null;
    }

    //gaseste un angajat dupa id cautand prin colectia principala
    private Employee findEmployeeById(int id) {
        return logic.getMap().keySet().stream()
                .filter(e -> e.getIdEmployee() == id)
                .findFirst().orElse(null);
    }

    //extrage angajatul selectat din lista drop-down a interfetei
    private Employee getSelectedEmpFromCombo() {
        String s = (String) view.getComboAngajati().getSelectedItem();
        if (s == null) return null;
        int id = Integer.parseInt(s.split(":")[1]);
        return findEmployeeById(id);
    }

    //salveaza datele in fisier si reimprospateaza tabelele din gui
    private void saveAndRefresh() {
        dao.saveData(logic.getMap());
        refreshTables();
        view.clearFields();
    }

    //curata si umple din nou tabelele si listele cu datele actuale din memorie
    private void refreshTables() {
        view.getModelAngajati().setRowCount(0);
        view.getModelTaskuri().setRowCount(0);
        view.getComboAngajati().removeAllItems();

        logic.getMap().forEach((emp, tasks) -> {
            //adaugam angajatul si durata lui totala (doar pt task urile completed)
            view.getModelAngajati().addRow(new Object[]{
                    emp.getIdEmployee(),
                    emp.getName(),
                    logic.calculateEmployeeWorkDuration(emp) + " ore"
            });

            view.getComboAngajati().addItem(emp.getName() + " - id:" + emp.getIdEmployee());

            if (tasks != null) {
                for (Task t : tasks) {
                    if (t != null) {
                        // adaugam fiecare task in tabelul de task-uri
                        view.getModelTaskuri().addRow(new Object[]{
                                t.getIdTask(),
                                (t instanceof SimpleTask ? "Simplu" : "Complex"),
                                t.getStatusTask(),
                                t.estimateDuration()
                        });
                    }
                }
            }
        });
    }
}