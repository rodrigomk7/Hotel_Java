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
public class Esporadico extends Cliente{

    //Constructor

    public Esporadico(String apellido, String nombre, String dni) {
        super(apellido, nombre, dni);
    }

    //Implementacion metodo tipo()
    @Override
    public String tipo() {
        return "Esporadico";
    }
    
    @Override
    public void mostrar(){ 
        super.mostrar();
        System.out.println("Tipo de cliente: Esporadico");
    }
    
}
