package model;

import java.io.Serializable;

//CLASA DE BAZA/ABSTRACTA
public abstract sealed class Task implements Serializable permits SimpleTask, ComplexTask{

    public int idTask;
    public String statusTask;

    //constructor gol
    public Task() {
    }

    //constructor
    public Task(int idTask, String statusTask) {
        this.idTask = idTask;
        this.statusTask = statusTask;
    }

    //sett si gett
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(String statusTask) {
        this.statusTask = statusTask;
    }

    //metoda abstracta
    public abstract int estimateDuration();
    //clasele SimpleTask si ComplexTask au propria logica

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
}
