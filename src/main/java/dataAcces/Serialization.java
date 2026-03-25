package dataAcces;
import java.io.*;

//CLASA CE SALVEAZA SI INCARCA DATELE PE HARD DISK
public class Serialization {
    //fisierul unde se vor scrie datele binare
    private static final String FILE_NAME = "data.ser";

    /**
     * metoda pentru salvarea obiectelor
     * primeste un obiect (map-ul cu date) si il scrie in fisier
     */
    public void saveData(Object data) {
        //flux de iesire care creeaza sau suprascrie fisierul data.ser
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            //metoda writeobject transforma obiectul din ram in octeti pe disc
            oos.writeObject(data);
        } catch (IOException e) {
            //afisare eroare daca nu are permisiuni sau fisierul este blocat
            e.printStackTrace();
        }
    }

    /**
     * metoda pentru incarcarea datelor la deschiderea aplicatiei
     * citeste octetii din fisier si ii transforma inapoi in obiecte java
     */
    public Object loadData() {
        //deschidere fisier pentru citire
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            //citire date si le returneaza ca obiect general(va fi convertit in controller)
            return ois.readObject();
        } catch (Exception e) {
            //daca fisierul nu exista sau e corupt, returneaza null pt a incepe cu o baza goala
            return null;
        }
    }
}