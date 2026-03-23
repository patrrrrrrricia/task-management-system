package businessLogic;

import model.Employee;
import model.Task;

import java.util.*;

public class TaskManagement {
    //cheia este angajatul
    //valoarea este lista lui de task uri
    private Map<Employee, List<Task>> map = new HashMap<>();

    //(a) aloca un task unui angajat
    public void assignTaskToEmployee(Employee emp, Task task) {
        map.computeIfAbsent(emp, k -> new ArrayList<>()).add(task);
    }

    //(b) calculeaza durata totala doar pt task urile "Completed"
    public int calculateEmployeeWorkDuration(Employee emp) {
        List<Task> tasks = map.get(emp);
        if (tasks == null) return 0;

        int total = 0;
        for (Task t : tasks) {
            if ("Completed".equals(t.getStatusTask())) {
                total += t.estimateDuration();
            }
        }
        return total;
    }

    // (c) schimba statusul unui task
    public void modifyTaskStatus(Employee emp, int idTask, String newStatus) {
        List<Task> tasks = map.get(emp);
        if (tasks != null) {
            for (Task t : tasks) {
                if (t.getIdTask() == idTask) {
                    t.setStatusTask(newStatus);
                    break;
                }
            }
        }
    }

    public int calculateEmployeeWorkDuration2(Employee emp) {
        List<Task> tasks = map.get(emp);
        if (tasks == null) return 0;

        return tasks.stream()
                .filter(t -> "Completed".equals(t.getStatusTask()))
                .mapToInt(Task::estimateDuration) // polimorfism aici!
                .sum();
    }

    public void setMap(Map<Employee, List<Task>> map) {
        this.map = map;
    }
}