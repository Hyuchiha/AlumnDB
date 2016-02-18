package com.byhyuchiha.studentdb.Model;

import java.io.Serializable;

/**
 * Created by byhyuchiha on 2/02/16.
 */
public class Alumno implements Serializable{
    private String firstName;
    private String lastName;
    private String matricula;

    public Alumno(String matricula, String firstName, String lastName){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setMatricula(matricula);
    }

    public Alumno(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;

        Alumno alumno = (Alumno) o;

        if (getFirstName() != null ? !getFirstName().equals(alumno.getFirstName()) : alumno.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(alumno.getLastName()) : alumno.getLastName() != null)
            return false;
        return !(getMatricula() != null ? !getMatricula().equals(alumno.getMatricula()) : alumno.getMatricula() != null);

    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getMatricula() != null ? getMatricula().hashCode() : 0);
        return result;
    }
}
