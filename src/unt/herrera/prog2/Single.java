/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Single extends Habitacion implements Serializable{

    public Single(int numero, double precio) {
        super(numero, precio);
    }

    @Override
    public double calcularPrecio() {
        return (this.getPrecio()*10)/100+this.getPrecio();
    }

    @Override
    public String tipo() {
        return "Single";
    }
    
    
}
