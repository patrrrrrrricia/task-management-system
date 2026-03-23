package businessLogic;

import model.Employee;
import model.Task;
import java.util.*;
import java.util.stream.Collectors;

public class Utility {

    // (a) Filtrează angajații cu peste 40 de ore lucrate, sortați crescător
    public static List<String> getHardWorkingEmployees(Map<Employee, List<Task>> data, TaskManagement logic) {
        return data.keySet().stream()
                .filter(emp -> logic.calculateEmployeeWorkDuration(emp) > 40)
                .sorted(Comparator.comparingInt(logic::calculateEmployeeWorkDuration))
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    // (b) Numărul de task-uri Completed vs Uncompleted pentru fiecare angajat
    public static Map<String, Map<String, Integer>> getTaskStatusStats(Map<Employee, List<Task>> data) {
        Map<String, Map<String, Integer>> report = new HashMap<>();

        for (var entry : data.entrySet()) {
            int completed = 0;
            int uncompleted = 0;
            for (Task t : entry.getValue()) {
                if ("Completed".equals(t.getStatusTask())) completed++;
                else uncompleted++;
            }
            Map<String, Integer> stats = new HashMap<>();
            stats.put("Completed", completed);
            stats.put("Uncompleted", uncompleted);
            report.put(entry.getKey().getName(), stats);
        }
        return report;
    }
}