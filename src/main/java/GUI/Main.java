package GUI;

import businessLogic.TaskManagement;
import dataAcces.Serialization;
import model.Employee;
import model.Task;
import java.util.List;
import java.util.Map;

//CLASA MAIN
public class Main {
    public static void main(String[] args) {
        // 1.initializare obiecte principale: salvarea si logica de business
        Serialization dao = new Serialization();
        TaskManagement businessLogic = new TaskManagement();

        // 2.incarcare date salvate anterior de pe disc (deserializare)
        Object loadedData = dao.loadData();
        //verificam daca datele citite sunt de tipul map asteptat
        if (loadedData instanceof Map) {
            // ransferam datele incarcate in obiectul de logica
            businessLogic.setMap((Map<Employee, List<Task>>) loadedData);
            System.out.println("datele au fost incarcate cu succes din fisier!");
        } else {
            //daca nu exista fisierul sau e prima pornire, incepem cu o lista goala
            System.out.println("nu s-au gasit date vechi. se porneste o sesiune noua.");
        }

        // 3.pornire interfata grafica pe thread-ul special de evenimente swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            //cream fereastra principala
            View view = new View("Task Management System");
            // controller-ul este cel care leaga view-ul de logica si de sistemul de salvare
            new Controller(view, businessLogic, dao);
            //facem fereastra vizibila pentru utilizator
            view.setVisible(true);
        });
    }
}