package com.lavadero.apirestlavadero.Services;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.lavadero.apirestlavadero.Entities.Vehiculo;
import com.lavadero.apirestlavadero.Repositorys.VehiculosRepository;

@SpringBootTest//indicamos que sera una prueba de funcionalidad(no de persistensia como la prueva de repository)
public class ServicesVehiculosTest {

    @Autowired//injectmos la capa de servicios (ServicesVehiculos)
    private ServicesVehiculos servicesVehiculos;
    @MockBean// se simulara el repositorio...
    private VehiculosRepository vehiculosRepository;

    @BeforeEach
    void setUp(){
        Vehiculo vehiculo = Vehiculo.builder()
                                    .placa("KBT-183")
                                    .nombre("Sergio")
                                    .telefono("")
                                    .numero_lavadas(1)
                                    .descripcion("").build();

        Mockito.when(vehiculosRepository.findVehiculoByPlaca("KBT-183")).thenReturn(Optional.of(vehiculo));//cuando se llame el metodo buscar vehiculo por placa del repositorio
        //se simulara un llamado a la base de datos retornando el vehiculo instanciado mediante el builder, en este metodo
    }

   
}
