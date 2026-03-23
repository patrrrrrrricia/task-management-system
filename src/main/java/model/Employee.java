package model;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable { //pt salvarea datelor ulterior
    public int idEmployee;
    public String name;

    //constructor gol
    public Employee() {
    }

    //constructor
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

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
    }

    //gett si sett
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                '}';
    }
}
