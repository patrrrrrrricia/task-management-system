package model;

import java.util.ArrayList;
import java.util.List;

public final class ComplexTask extends Task {

    //lista ce contine simple task si complex task
    private List<Task> subTasks;

    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
        //initializare lista goala, aloc de memorie(evitare pointernull)
        this.subTasks = new ArrayList<>();
    }

    //metoda add subtasks in lista
    public void addSubTask(Task task) {
        this.subTasks.add(task);
    }

    //metoda din clasa abstracta
    @Override
    public int estimateDuration() {
        int totalDuration = 0;

        //adunare durata fiecarui subtask
        for (Task t : subTasks) {
            totalDuration += t.estimateDuration();
        }

        return totalDuration;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }
}