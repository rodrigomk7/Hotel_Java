/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.time.LocalDate;


/**
 *
 * @author Alumno
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
GestorClientes gc=GestorClientes.creaGestor();
//
System.out.println(gc.crearEsporadico("Diaz","Rodrigo","39359920"));
System.out.println(gc.crearHabitual("Di","Rigo","39359920",23.4));//No se debe crear
System.out.println(gc.crearHabitual("Boca", "Juan","3456789", 567));
System.out.println(gc.crearEsporadico("Diaz","Dario","293590"));
System.out.println();
//gc.mostrarClientes();

GestorHabitaciones gh=GestorHabitaciones.crearGestor();

System.out.println(gh.nuevaDoble(1, 234));
System.out.println(gh.nuevaSingle(5, 135));
System.out.println(gh.nuevaMatrimonial(2, 345));
System.out.println(gh.nuevaDoble(4, 234));
System.out.println(gh.nuevaSingle(1, 135));//No debde crear

System.out.println();
//gh.mostrarHabitaciones();


GestorReservas gs=GestorReservas.crearGestor();

System.out.println(gs.nuevaReserva(gh.buscarHabitacion(4),gc.buscarCliente("39359920"), LocalDate.of(2016,12,17), 4,gh));
System.out.println(gs.nuevaReserva(gh.buscarHabitacion(4),gc.buscarCliente("3456789"), LocalDate.of(2016,12,14),2 ,gh));
System.out.println(gs.nuevaReserva(gh.buscarHabitacion(5),gc.buscarCliente("293590"), LocalDate.of(2016,12,1), 7,gh));
System.out.println(gs.nuevaReserva(gh.buscarHabitacion(2),gc.buscarCliente("293590"), LocalDate.of(2016,12,1), 7,gh));
System.out.println(gs.nuevaReserva(gh.buscarHabitacion(1),gc.buscarCliente("3456789"), LocalDate.of(2016,12,14),2 ,gh));

System.out.println();

System.out.println("CLIENTES\n");
gc.mostrarClientes();
System.out.println();
System.out.println("RESERVAS\n");
gs.mostrarReservas();
System.out.println();
System.out.println("HABITACIONES\n");
gh.mostrarHabitaciones();
}


}
