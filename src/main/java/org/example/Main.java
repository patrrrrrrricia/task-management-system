package org.example;

import org.example.business.TasksManagement;
import org.example.dao.SerializationDAO;
import org.example.model.Employee;
import org.example.model.Task;
import org.example.ui.MainView;
import org.example.controller.TaskController;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //1.initializare DAO pentru a putea citi datele salvate
        SerializationDAO dao = new SerializationDAO();

        //2.incarcare Map din fișier(deserializare)
        Object loadedData = dao.loadData();
        org.example.TaskManagement businessLogic = new TaskManagement();

        if (loadedData instanceof Map) {
            //dac am gasit date vechi, le punem in obiectul nostru de management
            businessLogic.setMap((Map<org.example.Employee, List<org.example.Task>>) loadedData);
            System.out.println("Datele au fost incarcate cu succes!");
        } else {
            System.out.println("Nu s-au gasit date salvate. Se pornește cu o lista goala.");
        }

        //3.creare interfața grafica (view)
        MainView view = new MainView();

        //4.creare Controller-ul care face legatura intre butoane si calcule
        //ii dam si DAO ul ca sa stie sa salveze cand facem modificari
        new ro.tuc.dsrl.example3.Controller(view, businessLogic, dao);

        // 5.facem fereastra vizibila
        view.setVisible(true);
    }
}