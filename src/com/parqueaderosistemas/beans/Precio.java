package com.parqueaderosistemas.beans;

/**
 *
 * @author JONATHAN
 */
public class Precio {
    private String tipo;
    private double valor;
    private int indice;
    
    public Precio(){
    
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public double getValor(){
        return this.valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }
    public int getIndice(){
        return this.indice;
    }
    public void setIndice(int indice){
        this.indice = indice;
    }    
}
