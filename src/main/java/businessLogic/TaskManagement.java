package businessLogic;

import model.Employee;
import model.Task;
import java.util.*;

public class TaskManagement {
    // map-ul cerut: cheia este angajatul, valoarea este lista lui de task-uri
    private Map<Employee, List<Task>> map = new HashMap<>();

    // (a) assignTaskToEmployee: aloca un task unui angajat
    public void assignTaskToEmployee(Employee emp, Task task) {
        // daca angajatul nu exista in map, cream o lista noua pentru el
        map.computeIfAbsent(emp, k -> new ArrayList<>()).add(task);
    }

    // (b) calculateEmployeeWorkDuration: suma duratelor task-urilor cu status "Completed"
    public int calculateEmployeeWorkDuration(Employee emp) {
        List<Task> tasks = map.get(emp);
        if (tasks == null) return 0;

        int total = 0;
        for (Task t : tasks) {
            // verificam daca task-ul este finalizat
            if ("Completed".equals(t.getStatusTask())) {
                total += t.estimateDuration(); // polimorfism: apeleaza metoda corecta din Simple/ComplexTask
            }
        }
        return total;
    }

    // (c) modifyTaskStatus: schimba statusul unui task specific al unui angajat
    public void modifyTaskStatus(Employee emp, int idTask, String newStatus) {
        List<Task> tasks = map.get(emp);
        if (tasks != null) {
            for (Task t : tasks) {
                if (t.getIdTask() == idTask) {
                    t.setStatusTask(newStatus);
                    break; // am gasit task-ul, iesim din bucla
                }
            }
        }
    }

    // getter si setter pentru serializare (folosite de Main si Controller)
    public Map<Employee, List<Task>> getMap() {
        return map;
    }

    public void setMap(Map<Employee, List<Task>> map) {
        this.map = map;
    }
}