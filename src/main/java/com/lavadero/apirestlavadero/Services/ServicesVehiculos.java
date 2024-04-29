package com.lavadero.apirestlavadero.Services;

import java.util.List;
//import java.util.Optional;

import com.lavadero.apirestlavadero.Entities.Vehiculo;
import com.lavadero.apirestlavadero.error.VehiculoNoFundException;

public interface ServicesVehiculos {

    List<Vehiculo> findAllVehiculos();
    Vehiculo saveVehiculo(Vehiculo vehiculo);
    Vehiculo updateVehiculo(String Placa, Vehiculo vehiculo) throws VehiculoNoFundException;
    //String deleteVehiculo(String placa);
    Vehiculo findVehiculoByPlaca(String placa) throws VehiculoNoFundException;
    String deleteVehiculoByPlaca(String placa);

}
