package com.parqueaderosistemas.beans;

/**
 *
 * @author JONATHAN
 */
public class Disponibles {
    private String id;
    private String placa;
    private String tipoVehiculo;
    private String horaEntrada;
    
    public Disponibles(){
        
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getPlaca(){
        return this.placa;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }
    public String getTipoVehiculo(){
        return this.tipoVehiculo;
    }
    public void setTipoVehiculo(String tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
    }
    public String getHoraEntrada(){
        return this.horaEntrada;
    }
    public void setHoraEntrada(String horaEntrada){
        this.horaEntrada = horaEntrada;
    }
}
