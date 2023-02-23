/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class GestorReservas {
    private List<Reserva> listaReservas= new ArrayList<>();
    private static final String ARCHIVO="Reservas.txt";
    
    private static GestorReservas gestor;
    private GestorReservas(){};
    public static GestorReservas crearGestor(){
        if(gestor==null){
            gestor=new GestorReservas();
            gestor.deserializar();
        }
        return gestor;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
    
    public String nuevaReserva(Habitacion habitacion, Cliente cliente, LocalDate fecha, int dias,GestorHabitaciones gestorHab){
        if(habitacion!=null&&cliente!=null&& fecha!=null&&dias>0){
            Reserva reserva=new Reserva(habitacion, cliente, fecha, dias);
            if(gestorHab.buscarHabitacion(habitacion.getNumero()).agregarReserva(cliente, fecha, dias).compareTo("EXITO")==0){
            listaReservas.add(reserva);
            this.serializar();
            return "La reserva se realizo con exito";
            }else
                return "No se pudo realizar la reserva";
                 
            
        }else return "Campos incorrectos";
    }
    
    
    public void mostrarReservas(){
        if(!listaReservas.isEmpty()){
            Collections.sort(listaReservas);
            for(Reserva r:listaReservas){
                r.mostrar();
                System.out.println();
            }
        }else{System.out.println("No hay reservas");
    }
    }    
    
    private void serializar(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(ARCHIVO))))){
            oos.writeObject(this.listaReservas);
        }
        catch(FileNotFoundException fn){System.out.println("El archivo no existe");}
        catch(NotSerializableException ns){System.out.println("La clase Reserva no es serializable");}//Se puede omitir
        catch(IOException io){System.out.println("No se pudo escribir en el archivo");}
    }
        
    private void deserializar(){
        File archivo=new File(ARCHIVO);
        if(archivo.exists()){
            try(ObjectInputStream oi=new ObjectInputStream(new BufferedInputStream(new FileInputStream(archivo)))){
                this.listaReservas=(List<Reserva>)oi.readObject();
                GestorHabitaciones gestorh=GestorHabitaciones.crearGestor(); 
                GestorClientes gestorc=GestorClientes.creaGestor();
                for(Reserva r:listaReservas)
                    gestorh.buscarHabitacion(r.getHabitacion().getNumero()).agregarReserva(gestorc.buscarCliente(r.getCliente().getDni()), r.getFecha(),r.getDias());
                
            }
            catch(ClassNotFoundException | IOException c){System.out.println("No se puede leer desde el archivo");}
        }else {System.out.println("El archivo no existe");}
    }
            
   
    
    
}
