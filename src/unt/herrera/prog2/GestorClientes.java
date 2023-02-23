/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unt.herrera.prog2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Alumno
 */
public class GestorClientes {
    private List<Cliente> listaClientes=new ArrayList<>();
    private static final String ERROR_OP="La operacion no se pudo realizar";
    private static final String EXITO_OP="La operacion se realizo con exito";
    private static final String ARCHIVO="Clientes.txt";
    
    private static  GestorClientes gestor;
    private GestorClientes(){};
    public static GestorClientes creaGestor(){
        if(gestor==null){
            gestor=new GestorClientes();
            gestor.leerArchivo();
        }
        return gestor;
    }
    
    
    public String crearHabitual(String apellido, String nombre, String dni,double descuento){
        if(!apellido.isEmpty()&&!nombre.isEmpty()&&!dni.isEmpty()&&descuento>0){
            Cliente cliente=new Habitual(apellido, nombre, dni, descuento);
            if(listaClientes.contains(cliente))
                return ERROR_OP;
            else{
                listaClientes.add(cliente);
                this.modificarArchivo();
                return EXITO_OP;
            }
        }else
            return ERROR_OP;
    }
    
    //
       public String crearEsporadico(String apellido, String nombre, String dni){
        if(!apellido.isEmpty()&&!nombre.isEmpty()&&!dni.isEmpty()){
            Cliente cliente=new Esporadico(apellido, nombre, dni);
            if(listaClientes.contains(cliente))
                return ERROR_OP;
            else{
                listaClientes.add(cliente);
                this.modificarArchivo();
                return EXITO_OP;
            }
        }else
            return ERROR_OP;
    }

  
   //
       public void mostrarClientes(){
           if(!listaClientes.isEmpty()){
               Collections.sort(listaClientes,Cliente::compApellidoNombre);
               for(Cliente c:listaClientes){
                   c.mostrar();
                   System.out.println();
               }
           }
           else{System.out.println("No hay clientes"); }
       }
       
       
       
       public Cliente buscarCliente(String dni){
           if(!dni.isEmpty()){
               Cliente c=new Esporadico(null,null, dni);
               for(Cliente cl:listaClientes)
                   if(c.equals(cl))
                       return cl;
               System.out.println("No se encontro el cliente");
               return null;
           }else{
               System.out.println("El campo dni esta vacio o es incorrecto");
               return null;           
           }
       }
           
//
       private void modificarArchivo(){
           try(BufferedWriter bw=new BufferedWriter(new FileWriter(new File(ARCHIVO)))){
               for(Cliente c: this.listaClientes){
                   if(c instanceof Habitual){
                       bw.write(c.tipo()+":"+c.getApellido()+":"+c.getNombre()+":"+c.getDni()+":"+((Habitual) c).getDescuento());
                       bw.newLine();
                   }
                    if(c instanceof Esporadico){
                       bw.write(c.tipo()+":"+c.getApellido()+":"+c.getNombre()+":"+c.getDni());
                       bw.newLine();
                   }   
               }
           }catch(IOException io){System.out.println("Error al escribir en el archivo");}
           
       }
       
       
       private void leerArchivo(){
           File archivo=new File(ARCHIVO);
           if(archivo.exists()){//Se puede atrapar la exepcion FileNotFoundException
               try(BufferedReader br=new BufferedReader(new FileReader(archivo))){
                   String linea;
                   while((linea=br.readLine())!=null){
                       String palabras[]=linea.split(":");
                       if(palabras[0]=="Habitual")
                           this.listaClientes.add(new Habitual(palabras[1],palabras[2],palabras[3],Double.parseDouble(palabras[4])));
                       else
                           this.listaClientes.add(new Esporadico(palabras[1],palabras[2],palabras[3]));
                   }
               }
               catch(Exception e){}
           }else{System.out.println("El archivo no existe");}
           
       }

}
