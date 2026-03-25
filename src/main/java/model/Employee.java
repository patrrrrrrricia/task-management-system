package model;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable { //pt salvarea datelor ulterior
    public int idEmployee;
    public String name;

    //constructor fara parametri necesar pt diverse operatii de baza
    public Employee() {
    }

    //constructor cu parametri pentru a seta id ul si numele la creare
    public Employee(int idEmployee, String name) {
        this.idEmployee = idEmployee;
        this.name = name;
    }

    //suprascriere pt map, pt a sti ca 2 angajati cu acelasi ID sunt aceeasi persoana
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return idEmployee == employee.idEmployee;
    }

    //generare cod unic pe baza id-ului pentru a lucra corect cu hashmap
    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
    }

    //getter pentru id ul angajatului
    public int getIdEmployee() {
        return idEmployee;
    }

    //setter pentru id ul angajatului
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    //getter pentru numele angajatului
    public String getName() {
        return name;
    }

    //setter pentru numele angajatului
    public void setName(String name) {
        this.name = name;
    }

    //metoda toString
    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                '}';
    }
}