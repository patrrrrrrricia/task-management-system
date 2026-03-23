package GUI;

import businessLogic.*;
import dataAcces.Serialization;
import model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private View view;
    private TaskManagement logic;
    private Serialization dao;

    public Controller(View v, TaskManagement l, Serialization s) {
        this.view = v; this.logic = l; this.dao = s;
        this.view.getBtnAddEmp().addActionListener(this);
        this.view.getBtnAddTask().addActionListener(this);
        this.view.getBtnStats().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getBtnAddEmp()) {
            Employee emp = new Employee(Integer.parseInt(view.getEmpId()), view.getEmpName());
            logic.assignTaskToEmployee(emp, null); // Creăm intrarea în map
            view.setDisplay("Employee added: " + emp.getName());
        }
        else if (e.getSource() == view.getBtnAddTask()) {
            // Luăm primul angajat pentru test (sau implementează selecție)
            Employee target = logic.getMap().keySet().iterator().next();
            Task t = new SimpleTask(Integer.parseInt(view.getTaskId()), "Uncompleted",
                    Integer.parseInt(view.getStart()), Integer.parseInt(view.getEnd()));
            logic.assignTaskToEmployee(target, t);
            view.setDisplay("Task assigned to " + target.getName());
        }
        else if (e.getSource() == view.getBtnStats()) {
            view.setDisplay("Stats:\n" + Utility.getTaskStatusStats(logic.getMap()));
        }

        // Salvare automată după orice modificare (Serializare)
        dao.saveData(logic.getMap());
    }
}