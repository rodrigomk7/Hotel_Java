/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

/**
 *
 * @author Alumno
 */
public class Habitual extends Cliente{
    private double descuento;
    
    //constructor

    public Habitual( String apellido, String nombre, String dni,double descuento) {
        super(apellido, nombre, dni);
        this.descuento = descuento;
    }

 
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    //Implementacion metodo tipo()

    @Override
    public String tipo() {
        return "Habitual";
    }
    //

    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Tipo de cliente: Habitual        Descuento: $"+this.descuento);
    }
    
    
}
