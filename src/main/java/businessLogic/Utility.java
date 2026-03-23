package businessLogic;

import java.util.*;

public class Utility {
    public static Map<Employee, String> getTaskStats(Map<Employee, List<Task>> data) {
        Map<Employee, String> stats = new HashMap<>();
        for (var entry : data.entrySet()) {
            long completed = entry.getValue().stream().filter(t -> "Completed".equals(t.getStatusTask())).count();
            long uncompleted = entry.getValue().size() - completed;
            stats.put(entry.getKey(), "C: " + completed + " | U: " + uncompleted);
        }
        return stats;
    }
}