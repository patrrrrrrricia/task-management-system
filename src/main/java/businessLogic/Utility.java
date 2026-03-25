package businessLogic;

import model.Employee;
import model.Task;
import java.util.*;
import java.util.stream.Collectors;

//CLASA PT OPERATII DE FILTRARE SI PROCESARE A DATERLOR
public class Utility {

    /**
     * (a) filtreaza angajatii care au lucrat peste 40 de ore
     * rez e o lista cu numele lor, sortata crescator dupa ore
     */
    public static List<String> getHardWorkingEmployees(Map<Employee, List<Task>> data, TaskManagement logic) {
        //stream pt a procesa setul de angajati(cheile din map)
        return data.keySet().stream()
                //doar angajatii care trec de 40 de ore lucrate
                .filter(emp -> logic.calculateEmployeeWorkDuration(emp) > 40)
                //sortare lista crescator folosind durata calculata de clasa de logica
                .sorted(Comparator.comparingInt(logic::calculateEmployeeWorkDuration))
                //extragere doar numele angajatului din obiectul employee
                .map(Employee::getName)
                //colectare rez intr o lista de tip string
                .collect(Collectors.toList());
    }

    /**
     * (b) statistici: cate task uri sunt gata(completed) si cate nu(uncompleted)
     * pt fiecare angajat in parte
     */
    public static Map<String, Map<String, Integer>> getTaskStatusStats(Map<Employee, List<Task>> data) {
        //creare un map care asociaza numele angajatului cu o alta colectie de statistici
        Map<String, Map<String, Integer>> report = new HashMap<>();

        //parcurgere fiecare intrare din map(perechea angajat - lista task uri)
        for (var entry : data.entrySet()) {
            int completed = 0;
            int uncompleted = 0;

            //parcurgere lista de task uri a angajatului curent
            for (Task t : entry.getValue()) {
                if (t != null) { //verificare pt a evita nullpointerexception
                    //nr task urile in functie de statusul lor text
                    if ("Completed".equals(t.getStatusTask())) {
                        completed++;
                    } else {
                        uncompleted++;
                    }
                }
            }

            //creare map temporar pt a stoca cele doua numere calculate
            Map<String, Integer> stats = new HashMap<>();
            stats.put("Completed", completed);
            stats.put("Uncompleted", uncompleted);

            //adaugare rez in raportul final folosind numele angajatului drept cheie
            report.put(entry.getKey().getName(), stats);
        }
        //returnare raport complet catre controller pentru a fi afisat in gui
        return report;
    }
}