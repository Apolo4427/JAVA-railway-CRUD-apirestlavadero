package com.lavadero.apirestlavadero.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lavadero.apirestlavadero.Entities.Vehiculo;
import com.lavadero.apirestlavadero.Repositorys.VehiculosRepository;
import com.lavadero.apirestlavadero.error.VehiculoNoFundException;

import jakarta.transaction.Transactional;

@Service
public class ServicesVehiculosImpl implements ServicesVehiculos{

    @Autowired //Inject repository
    VehiculosRepository vehiculosRepository;

    @Override
    public List<Vehiculo> findAllVehiculos() {
       return vehiculosRepository.findAll();
    }

    @Transactional
    @Override
    public String deleteVehiculoByPlaca(String placa){
        vehiculosRepository.deleteByPlaca(placa);;
        return "se ha eliminado el vehiculo";
    }

    @Override
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        //validacion de placa repetida
        // List<Vehiculo> vehiculosRegistrados = vehiculosRepository.findAll();
        // for (Vehiculo vehiculoRegistrado : vehiculosRegistrados) {
        //     if (vehiculoRegistrado.getPlaca().equals(vehiculo.getPlaca())) {
        //         return null;
        //     }
        // }

        //validacion numero_lavadas
        if(vehiculo.getNumero_lavadas()<-1){
            vehiculo.setNumero_lavadas(1);
        }

        return vehiculosRepository.save(vehiculo);
    }

    @Transactional
    @Override
    public Vehiculo updateVehiculo(String placa, Vehiculo vehiculo) throws VehiculoNoFundException{

        //si la placa no esta retorna nulo
        // if(vehiculosRepository.findVehiculoByPlaca(placa).isEmpty()){
        //     return null;
        // }
        //con manejo de VehiculoNoFundException:
        Optional<Vehiculo> vehiculoBuscado= vehiculosRepository.findVehiculoByPlaca(placa);
        if(!vehiculoBuscado.isPresent()){
                throw new VehiculoNoFundException("Esa placa no se encuentra registrada");
        }

        Vehiculo vehiculoAntiguo = vehiculosRepository.findVehiculoByPlaca(placa).get();

        //acmbiar placa por una ya existente:
        boolean placaRegistrada=false;
        List<Vehiculo> vehiculosRegistrados = vehiculosRepository.findAll();
        for (Vehiculo vehiculoRegistrado : vehiculosRegistrados) {
            if(vehiculoRegistrado.getPlaca()== vehiculo.getPlaca()){
                placaRegistrada=true;
                System.out.println("La placa ya esta registrada");
                break;
            }
        }
        //NO PODEMOS ACTUALIZAR LA PLACA (CORREGIR)
        if(!placaRegistrada && !"".equalsIgnoreCase(vehiculo.getPlaca())){
            vehiculoAntiguo.setPlaca(vehiculo.getPlaca());
        }

        if(!"".equalsIgnoreCase(vehiculo.getPlaca())){
            vehiculoAntiguo.setPlaca(vehiculo.getPlaca());
        }
        if(!"".equalsIgnoreCase(vehiculo.getNombre())){
            vehiculoAntiguo.setNombre(vehiculo.getNombre());
        }
        if(vehiculo.getNumero_lavadas()>-1 && vehiculo.getNumero_lavadas()<7){
            vehiculoAntiguo.setNumero_lavadas(vehiculo.getNumero_lavadas());
        }
        if(vehiculo.getTelefono().toString().length()>=10){
            vehiculoAntiguo.setTelefono(vehiculo.getTelefono());
        }
        if(!"".equalsIgnoreCase(vehiculo.getDescripcion())){
            vehiculoAntiguo.setDescripcion(vehiculo.getDescripcion());
        }

        return vehiculosRepository.save(vehiculoAntiguo);
    }

    @Override
    public Vehiculo findVehiculoByPlaca(String placa) throws VehiculoNoFundException{
        return vehiculosRepository.findVehiculoByPlaca(placa).get();
    }

    

}
