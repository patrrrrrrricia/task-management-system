package businessLogic;

import model.ComplexTask;
import model.Employee;
import model.Task;
import java.util.*;

//CLASA GESTIONARE LEGATURA ANGAJATI SI TASK URI
public class TaskManagement {
    //map ul principal: un angajat are o lista de task uri
    private Map<Employee, List<Task>> map = new HashMap<>();

    //(a) assigntasktoemployee: aloca un task unui angajat in map
    public void assignTaskToEmployee(Employee emp, Task task) {
        if (emp == null) return;
        //daca angajatul nu exista in map il adaugam cu o lista noua
        List<Task> tasks = map.computeIfAbsent(emp, k -> new ArrayList<>());
        if (task != null) {
            tasks.add(task);
        }
    }

    // --- metoda noua pentru grupare (subtasks) ---
    //cauta un task parinte (complex) si ii adauga un task copil
    public boolean addSubtaskToParent(int parentId, int childId) {
        Task parent = findTaskById(parentId);
        Task child = findTaskById(childId);

        //verificare daca a gasit ambele task uri si daca parintele este complex
        if (parent instanceof ComplexTask && child != null) {
            //parintele aduna copilul in lista sa interna
            ((ComplexTask) parent).addSubTask(child);
            return true;
        }
        return false; //nu s a putut face gruparea
    }

    //metoda utilitara pentru a gasi un task in tot sistemul dupa id
    public Task findTaskById(int id) {
        //cautam prin toate listele de task uri ale tuturor angajatilor
        for (List<Task> taskList : map.values()) {
            for (Task t : taskList) {
                if (t != null && t.getIdTask() == id) {
                    return t;
                }
            }
        }
        return null;
    }

    //sterge un task dupa id pentru un anumit angajat
    public void deleteTask(Employee emp, int idTask) {
        List<Task> tasks = map.get(emp);
        if (tasks != null) {
            // removeif pentru a sterge direct elementul care corespunde id ului
            tasks.removeIf(t -> t != null && t.getIdTask() == idTask);
        }
    }

    //sterge un angajat cu totul din sistem
    public void deleteEmployee(Employee emp) {
        if (emp != null) {
            map.remove(emp);
        }
    }

    //(b) calculateemployeeworkduration: suma duratelor task urilor "completed"
    public int calculateEmployeeWorkDuration(Employee emp) {
        List<Task> tasks = map.get(emp);
        if (tasks == null) return 0;

        int total = 0;
        // adunam duratele doar daca task-ul este marcat ca terminat
        for (Task t : tasks) {
            if (t != null && "Completed".equals(t.getStatusTask())) {
                total += t.estimateDuration();
            }
        }
        return total;
    }

    //(c) modifytaskstatus: schimba statusul unui task dupa id
    public void modifyTaskStatus(Employee emp, int idTask, String newStatus) {
        //de la mep->lista de task uri angajatului emp
        List<Task> tasks = map.get(emp);
        if (tasks != null) {
            //fiecare task din lista angajatului
            for (Task t : tasks) {
                if (t != null && t.getIdTask() == idTask) {
                    t.setStatusTask(newStatus); ///(ex:Completed)
                    break; //id urile sunt unice, oprire bucla
                }
            }
        }
    }

    // getter pt accesarea map ului (folosit la serializare si in controller)
    public Map<Employee, List<Task>> getMap() {
        return map;
    }

    // setter pt incarcarea datelor din fisierul salvat
    public void setMap(Map<Employee, List<Task>> map) {
        if (map != null) {
            this.map = map;
        } else {
            this.map = new HashMap<>();
        }
    }
}