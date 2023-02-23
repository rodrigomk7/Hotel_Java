/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Alumno
 */
public abstract class Cliente implements Serializable{
    private String nombre;
    private String dni;
    private String apellido;

    //Constructor
    public Cliente(String apellido,String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
    }
    //Metodos get/set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    //Metodos equals y hashcode

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass().getSuperclass() != obj.getClass().getSuperclass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }
    
    //Metodo para mostrar un cliente
    public void mostrar(){
        System.out.println("Apellido: "+this.apellido+"\nNombre: "+this.nombre+"\nD.N.I.: "+this.dni);
    }
    
    public abstract String tipo();
    
    
    public static int compararNombre(Cliente c1,Cliente c2){
        return c1.getNombre().compareTo(c2.getNombre());
    }
    
    public static int compararApellido(Cliente c1 ,Cliente c2){
        return c1.getApellido().compareTo(c2.getApellido());
    }
    
    public static int compApellidoNombre(Cliente c1 , Cliente c2){
       
        if(c1.getApellido().compareTo(c2.getApellido())!=0)
            return c1.getApellido().compareTo(c2.getApellido());
        else    
           return c1.getNombre().compareTo(c2.getNombre());
    }
}
