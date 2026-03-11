package org.example;

public abstract class Task {

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

    //set si get
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
    public abstract double estimateDuration();
    //clasele SimpleTask si ComplexTask au propria logica

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", statusTask='" + statusTask + '\'' +
                '}';
    }
}
