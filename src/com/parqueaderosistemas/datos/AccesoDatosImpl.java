package com.parqueaderosistemas.datos;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import com.parqueaderosistemas.dominio.*;


public class AccesoDatosImpl implements AccesoDatos{
    
    @Override
    public boolean existe(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }
    //
    @Override
    public List<String> listar(String nombreArchivo){
       //Parqueo parqueo = new Parqueo(); 
       File archivo = new File(nombreArchivo);
       List<String> parqueos = new ArrayList();
       try{
           BufferedReader entrada = new BufferedReader(new FileReader(archivo));
           String linea = null;
           linea = entrada.readLine();
           while(linea != null){
               //Parqueo parqueo = new Parqueo(linea);
               parqueos.add(linea);
               linea = entrada.readLine();
           }
           entrada.close();
           
       }catch(IOException ex){
           ex.printStackTrace();
       }
    return parqueos;
    }
    //
    @Override
    public void escribir(Parqueo parqueo, String nombreArchivo, boolean anexar){
        File archivo = new File(nombreArchivo);
        try{
        PrintWriter salida = new PrintWriter(new FileWriter(archivo,anexar));
        salida.println(parqueo.toString());
        salida.close();
        //se ha registrado correctamente
        }catch(IOException ex){}
    }
}
