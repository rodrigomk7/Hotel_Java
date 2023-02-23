/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Alumno
 */
public class Reserva implements Comparable<Reserva>, Serializable{
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fecha;
    private int dias;//Por cuantos dias reservara la habitacion

    public Reserva(Habitacion habitacion, Cliente cliente, LocalDate fecha, int dias) {
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fecha = fecha;
        this.dias = dias;
    }
    
    //Metodos get/ set

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    //Dos reservas son iguales si tienen la misma Habitacion?y fecha ?
    
    //Metodo para calcular el precio de uyna habitacion
    public double calcularPrecio(){
        if(this.cliente.tipo().compareTo("Habitual")==0)
            return this.habitacion.calcularPrecio()-((Habitual)this.cliente).getDescuento();
        else
            return this.habitacion.calcularPrecio();
    }
    
    //Metodo para mostrar reserva
    public void mostrar(){
        LocalDate fechaFin=this.getFechaFin();
        String formato="dd/MM/yyyy";
        System.out.println("Habitacion n°: "+this.habitacion.getNumero()+"\nFecha de inicio: "+fecha.format(DateTimeFormatter.ofPattern(formato))+"\tFecha fin: "+fechaFin.format(DateTimeFormatter.ofPattern(formato))+"\nReserva hecha por: ");
        System.out.println(this.cliente.getApellido()+","+this.cliente.getNombre()+"\t D.N.I:"+this.cliente.getDni());
    }
        
    
   //Metodo para devolver la fecha finalizacion de una reserva
    public LocalDate getFechaFin(){
        return this.fecha.plusDays(this.dias);
    }

    @Override
    public int compareTo(Reserva o) {
        return this.fecha.compareTo(o.getFecha());
    }
    
    
}
