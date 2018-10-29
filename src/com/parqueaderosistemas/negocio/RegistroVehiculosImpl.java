package com.parqueaderosistemas.negocio;
import interfaces.parqueadero.*;
import com.parqueaderosistemas.datos.*;
import com.parqueaderosistemas.dominio.*;
import java.util.List;

public class RegistroVehiculosImpl implements RegistroVehiculos{
    private final AccesoDatos datos;
    
    public RegistroVehiculosImpl(){
        this.datos = new AccesoDatosImpl();
    }
    
    public void agregarParqueo(String nombreArchivo, String cadenaParqueo){
        Parqueo p = new Parqueo(cadenaParqueo);
        boolean anexar = false;
        try{
            anexar = datos.existe(nombreArchivo);
            datos.escribir(p, nombreArchivo, anexar);
        }catch(Exception ex){
            //error, no se encontraron los datos
        }
    }
    
    @Override
    public void listarParqueo(String nombreArchivo){
        parqueaderoRegistro pr = new parqueaderoRegistro();
        try{
            List<String> parqueos = datos.listar(nombreArchivo);
            for(String parqueo : parqueos){
                //String parqueo2 += parqueo;
                //System.out.println("***" + parqueo);
                pr.txt_area.setText(parqueo);
                        
            }
        }catch(Exception ex){
            //error
        }
    }
    
}
