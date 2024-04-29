package com.lavadero.apirestlavadero.error;

public class VehiculoNoFundException extends Exception{
    //heredando de la clase Exception puedo usar constructores que permiter mostrar el mensaje de error, entreo otros varios 
    public VehiculoNoFundException(String message){
        super(message);
    }
}
