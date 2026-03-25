package model;

import java.io.Serializable;

//CLASA ABSTRACTA TASK
public abstract sealed class Task implements Serializable permits SimpleTask, ComplexTask{

    public int idTask;
    public String statusTask;

    //constructor fara parametri pt initializare
    public Task() {
    }

    //constructor cu parametri pt a seta id ul si statusul initial
    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    //getter pt id ul task ului
    public int getIdTask() {
        return idTask;
    }

    //setter pt id ul task ului
    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    //getter pt statusul curent al task ului (ex: completed)
    public String getStatusTask() {
        return statusTask;
    }

    //setter pt statusul task ului
    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    //metoda abstracta pt calculul duratei care va fi implementata diferit in sub clase
    public abstract int estimateDuration();
    //clasele SimpleTask si ComplexTask au propria logica

    //metoda toString
    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
}