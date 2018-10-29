package com.parqueaderosistemas.dominio;

public class Parqueo {

    public static String toString(Parqueo parqueo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    String cadena;
    
    public Parqueo(String cadena){
        this.cadena = cadena;
    }
    
    public String getString(){
        return cadena;
    }
    
    public void setString(String cadena){
        this.cadena = cadena;
    }
    @Override
    public String toString(){
        return cadena;
    }
}