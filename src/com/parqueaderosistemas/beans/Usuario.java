package com.parqueaderosistemas.beans;

/**
 *
 * @author JONATHAN
 */
public class Usuario {
    private int id;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String tipoUsuario;
    
    public Usuario(){
    
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    //
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    //
    public String getUsuario(){
        return this.usuario;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public String getContrasena(){
        return this.contrasena;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    public String getTipoUsuario(){
        return this.tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }    
}
