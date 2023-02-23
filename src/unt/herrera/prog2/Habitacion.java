/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Alumno
 */
public abstract class Habitacion implements Serializable, Comparable<Habitacion>{
    private int numero;
    private double precio;
    private List<Reserva> listaReservas=new ArrayList<>();
    private static final String ERROR_RESERVA="ERROR";
    private static final String EXITO_RESERVA="EXITO";

    //Costructor
    public Habitacion(int numero, double precio) {
        this.numero = numero;
        this.precio = precio;
    }
    //Metodos get/set
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrecio() {
        return this.precio;
    }
    

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    //Metodos equal y hascode (numero)
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.numero;
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
        final Habitacion other = (Habitacion) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }
    
    //Devuelve el precio de la habitacion
    public abstract double calcularPrecio();
    
    //mostrar
    public void mostrar(){
        System.out.println("Habitacion n°"+this.numero+"\t\t Precio: $"+this.precio+"\t\tTipo: "+this.tipo()+"\n Reservas:");
        System.out.println("---------------------------------------------");
        this.mostrarReservas();
        System.out.println("---------------------------------------------");
        
    }
    
    
    public void mostrarReservas(){
        if(!listaReservas.isEmpty()){
         Collections.sort(listaReservas);
            for(Reserva r:listaReservas){
                r.mostrar();
                System.out.println();
            }
        }
        else
            System.out.println("No hay reservas para esta habitacion");
    }

    //Metodo par agregar una reserva ala habitacion
    public String agregarReserva(Cliente cliente,LocalDate fecha,int dias){
        if(cliente!=null&&fecha!=null&&dias>0){
            if(!listaReservas.isEmpty()){
                int bandera=0;
                
                for(Reserva r:listaReservas){
                    LocalDate fechaFinReserva=fecha.plusDays(dias);
                    if(fecha.compareTo(r.getFecha())<0){//Fecha nueva reserva menor a la de la reserva
                        if(fechaFinReserva.compareTo(r.getFecha())>=0)
                            bandera =1;
                    }
                    else{
                        if(fecha.compareTo(r.getFechaFin())<=0)
                            bandera=1;
                    }
                }
               
                if(bandera==0){
                    Reserva reserva=new Reserva(this, cliente, fecha, dias);
                    listaReservas.add(reserva); 
                    return EXITO_RESERVA;
                }
                else
                    return  ERROR_RESERVA;
            }
            else{
                Reserva reserva=new Reserva(this, cliente, fecha, dias);
                listaReservas.add(reserva);
                return EXITO_RESERVA;
            }
        }
        else
            return ERROR_RESERVA;
    }

    
    //Metodo para saber si una habitacion esta reservada en un rango de fechas
    public boolean estaReservada(LocalDate fechaInicio,LocalDate fechaFin){
     if(!listaReservas.isEmpty()){
                int bandera=0;
                for(Reserva r:listaReservas){
                    
                    if(fechaInicio.compareTo(r.getFecha())<0){//Fecha nueva reserva menor a la de la reserva
                        if(fechaFin.compareTo(r.getFecha())>=0)
                            bandera =1;}
                    else{
                        if(fechaInicio.compareTo(r.getFechaFin())<=0)
                            bandera=1;}
                }
                if(bandera==0)
                    return false;
                else
                    return true;
    }
     else return false;
    }
    
    //
    public abstract String tipo();

    @Override
    public int compareTo(Habitacion o) {
    return this.getNumero()-o.getNumero();
    }
    
    
    
    
  }  
    

