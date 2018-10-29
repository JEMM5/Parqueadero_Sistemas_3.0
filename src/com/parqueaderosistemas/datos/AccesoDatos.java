package com.parqueaderosistemas.datos;
import java.util.List;
import com.parqueaderosistemas.dominio.*;

public interface AccesoDatos {
    
    boolean existe(String nombreArchivo);
    public List<String> listar(String nombreArchivo);
    void escribir(Parqueo parqueo, String nombreArchivo, boolean anexar);
}
