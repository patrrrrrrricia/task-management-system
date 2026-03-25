package model;

import java.util.ArrayList;
import java.util.List;

//CLASA PT TASK URI COMPLEXE CARE POT CONTINE ALTE TASK URI
public final class ComplexTask extends Task {

    //lista ce contine obiecte de tip task(simpletask sau alte complextask)
    private List<Task> subTasks;

    //constructor care apeleaza parintele si initializeaza lista de subtask uri
    public ComplexTask(int idTask, String statusTask) {
        super(idTask, statusTask);
        //initializare lista goala pentru a evita nullpointerexception
        this.subTasks = new ArrayList<>();
    }

    //metoda pt composite pattern care permite adaugarea de subtask uri
    public void addSubTask(Task task) {
        //adaugare task in lista daca este valid si nu este el insusi
        if (task != null && task != this) { //evitare auto referintele infinite
            this.subTasks.add(task);
        }
    }

    /**
     * implementarea polimorfica a calculului duratei.
     * add rez calculate de fiecare copil din lista,
     * indiferent daca sunt task uri simple sau alte task uri complexe
     */

    @Override
    public int estimateDuration() {
        int totalDuration = 0;

        //parcurgem lista de subtask uri si adunam duratele lor
        for (Task t : subTasks) {
            if (t != null) {
                //recursivitatea polimorfica pentru calcul
                totalDuration += t.estimateDuration();
            }
        }

        return totalDuration;
    }

    //getter pt a accesa lista interna de subtask uri
    public List<Task> getSubTasks() {
        return subTasks;
    }
}