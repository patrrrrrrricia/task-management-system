package org.example;

public final class SimpleTask extends Task{
    public int startHour;
    public int endHour;

    //constructor gol
    public SimpleTask() {
    }

    //constructor
    public SimpleTask(int idTask, String statusTask, int startHour, int endHour) {
        super(idTask, statusTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    //metoda din clasa abstracta
    @Override
    public int estimateDuration() {
        return endHour-startHour;
    }

    //gett si sett
    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    @Override
    public String toString() {
        return "SimpleTask{" +
                "startHour=" + startHour +
                ", endHour=" + endHour +
                "idTask=" + this.getIdTask() +
                ", statusTask='" + this.getStatusTask() + '\'' +
                '}';
    }
}
