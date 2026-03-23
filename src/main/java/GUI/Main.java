package GUI;

import businessLogic.TaskManagement;
import dataAcces.Serialization;
import model.Employee;
import model.Task;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Initializare obiecte
        Serialization dao = new Serialization();
        TaskManagement businessLogic = new TaskManagement();

        // 2. Incarcare date salvate (Deserializare)
        Object loadedData = dao.loadData();
        if (loadedData instanceof Map) {
            businessLogic.setMap((Map<Employee, List<Task>>) loadedData);
            System.out.println("Datele au fost incarcate cu succes din fisier!");
        } else {
            System.out.println("Nu s-au gasit date vechi. Se porneste o sesiune noua.");
        }

        // 3. Pornire Interfata Grafica
        javax.swing.SwingUtilities.invokeLater(() -> {
            View view = new View("Task Management System");
            // Controller-ul leaga totul impreuna
            new Controller(view, businessLogic, dao);
            view.setVisible(true);
        });
    }
}