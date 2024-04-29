package com.lavadero.apirestlavadero.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lavadero.apirestlavadero.Entities.Vehiculo;
import com.lavadero.apirestlavadero.Services.ServicesVehiculosImpl;
import com.lavadero.apirestlavadero.error.VehiculoNoFundException;

import jakarta.validation.Valid;

import java.util.List;

@RestController
public class VehiculoController {

    @Autowired//inject services
    ServicesVehiculosImpl servicesVehiculosImpl;

    @GetMapping("/vehiculos")
    public List<Vehiculo> findAllVehiculos(){
        return servicesVehiculosImpl.findAllVehiculos();
    }

    @GetMapping("/vehiculos/{placa}")
    public Vehiculo findVehiculoByPlaca(@PathVariable String placa) throws VehiculoNoFundException{
        return servicesVehiculosImpl.findVehiculoByPlaca(placa);
    }

    @PostMapping("/registrar")//@Valid -> "spring-boot-starter-validation" conprueba la validacion de la entidad
    public Vehiculo registerVehiculo(@Valid @RequestBody Vehiculo vehiculo){
        //validacion de placa
        // if(Objects.nonNull(vehiculo.getPlaca()) && !vehiculo.getPlaca().isEmpty()){
        //     if(vehiculo.getPlaca().contains("-")){
        //         return servicesVehiculosImpl.saveVehiculo(vehiculo);
        //     }
        // }
        // return null;
        return servicesVehiculosImpl.saveVehiculo(vehiculo);
    }

    @PutMapping("/actualizar/{placa}")//indicamos que este metodo posiblemente pueda arrojar un error del tipo "VehiculoNoFundException", lo hacemos asi tambien el los servicios.
    public Vehiculo actualizarVehiculo(@PathVariable String placa, @RequestBody Vehiculo vehiculo) throws VehiculoNoFundException{
        return servicesVehiculosImpl.updateVehiculo(placa, vehiculo);
    }

    @DeleteMapping("/{placa}")
    public String eliminarVehiculo(@PathVariable String placa){
       // return    servicesVehiculosImpl.deleteVehiculo(id);
       return servicesVehiculosImpl.deleteVehiculoByPlaca(placa);
    }

    
}
