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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class GestorHabitaciones {
    private List<Habitacion> listaHabitaciones=new ArrayList<>();
    private static final String ERROR_CAMPO="Se ingresaron datos incorrecto";
    private static final String EXITO_OP="La operacion se realizo con exito";
    private static final String ERROR_OP="La operacion no se pudo realizar";
    private static final String ARCHIVO="Habitaciones.txt";
    private static GestorHabitaciones gestor;
    private GestorHabitaciones(){}
    public static GestorHabitaciones crearGestor(){
        if(gestor==null){
             gestor=new GestorHabitaciones();
            gestor.deserializar();
        }  
            return gestor;
    }
    
    //
    public String nuevaSingle(int numero ,double precio){
        if(numero>0&&precio>0){
            Single single=new Single(numero, precio);
            if(listaHabitaciones.contains(single))
                return ERROR_OP;
            else{
                listaHabitaciones.add(single);
                this.serializar();
                return EXITO_OP;
            }
        }
        else {return ERROR_CAMPO;}
    }
    
    
    public String nuevaDoble(int numero ,double precio){
        if(numero>0&&precio>0){
            Doble doble=new Doble(numero, precio);
            if(listaHabitaciones.contains(doble))
                return ERROR_OP;
            else{
                listaHabitaciones.add(doble);
                this.serializar();
                return EXITO_OP;
            }
        }
        else {return ERROR_CAMPO;}
    }
        
        
        
    public String nuevaMatrimonial(int numero ,double precio){
        if(numero>0&&precio>0){
            Matrimonial matrimonial=new Matrimonial(numero, precio);
            if(listaHabitaciones.contains(matrimonial))
                return ERROR_OP;
            else{
                listaHabitaciones.add(matrimonial);
                this.serializar();
                return EXITO_OP;
            }
        }
        else {return ERROR_CAMPO;}
    }
    
    //
    public void mostrarHabitaciones(){
        if(!listaHabitaciones.isEmpty()){
            Collections.sort(listaHabitaciones);
            for(Habitacion h: listaHabitaciones){
                System.out.print("*");
                h.mostrar();
                System.out.println();
            }
        }else{
            System.out.println("No hay habitaciones");
        }
    }
    
    //
    public Habitacion buscarHabitacion(int numero){
        if(!listaHabitaciones.isEmpty()){
            Habitacion h1=new Single(numero,0);
            for(Habitacion h:listaHabitaciones){
                if(h1.equals(h))
                    return h;
            }
            return null;
        }
        else{
            System.out.println("La Habitacion no existe");
            return null;
        }
            
    }
    
    //
    private void serializar(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(ARCHIVO))))){
            oos.writeObject(this.listaHabitaciones);
        }
        catch(NotSerializableException nse){}
        catch(FileNotFoundException fnf){System.out.println("El archivo no se encuentra");}
        catch(IOException io){System.out.println("Error al escribir en el archivo");}
    }
    
    private void deserializar(){
        File archivo=new File(ARCHIVO);
        if(archivo.exists()){
          try(ObjectInputStream ois=new ObjectInputStream(new BufferedInputStream(new FileInputStream(archivo)))){
             this.listaHabitaciones=(List<Habitacion>)ois.readObject();
          }
          catch(ClassNotFoundException cnf){System.out.println("No se pudo leer desde el archivo");}//
          catch(IOException io){System.out.println("Error al leer desde el archivo");}
            
            
            
        }else{System.out.println("El archivo no existe");}
    }
}
