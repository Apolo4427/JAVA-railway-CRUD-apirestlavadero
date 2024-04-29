package com.lavadero.apirestlavadero.Repositorys;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.lavadero.apirestlavadero.Entities.Vehiculo;

//testin de la capa de acceso a datos

@DataJpaTest//indicamos que esta clase funcionara como testeo de percistencia jpa a la base de datos
public class VehiculosRepositoryTest {

    @Autowired// injeccion de repositorio
    VehiculosRepository vehiculosRepository;
    @Autowired// injeccion de entidad gerente de testeo
    TestEntityManager testEntityManager;

    @BeforeEach//el metodo setUp se ejecutara antes de cada test
    void setUp(){//crea objeto de prueva
        Vehiculo vehiculo = Vehiculo.builder()// usamos el metodo builder de la dependencia lombok para instaciar el objeto
                                    .placa("KBT-183")
                                    .nombre("Juanita")
                                    .numero_lavadas(1)
                                    .telefono("3113742829")
                                    .descripcion("hace mucho no se lava")
                                    .build();// llamar al método build() para obtener una instancia de Vehiculo
        testEntityManager.persist(vehiculo);
    }

    @Test//indicamos que es una prueva unitaria
    void testFindVehiculoByPlaca(){
        Optional<Vehiculo> vehiculo = vehiculosRepository.findVehiculoByPlaca("KBT-183");
        assertEquals(vehiculo.get().getPlaca(), "KBT-183");//proamos si el objeto retornado por el metodo, en realidad contiene la placa "KBT-183"
    }
}
