package model;

//CLASA PT TASK URI SIMPLE CARE NU CONTIN SUBTASK URI
public final class SimpleTask extends Task{
    public int startHour;
    public int endHour;

    //constructor fara parametri pentru initializarea obiectului
    public SimpleTask() {
    }

    //constructor care apeleaza parintele si seteaza intervalul orar
    public SimpleTask(int idTask, String statusTask, int startHour, int endHour) {
        super(idTask, statusTask);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    //metoda din clasa abstracta care calculeaza durata ca diferenta intre ore
    @Override
    public int estimateDuration() {
        return endHour-startHour;
    }

    //getter pt ora de inceput a task ului
    public int getStartHour() {
        return startHour;
    }

    //setter pt ora de inceput a task ului
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    //metoda toString
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