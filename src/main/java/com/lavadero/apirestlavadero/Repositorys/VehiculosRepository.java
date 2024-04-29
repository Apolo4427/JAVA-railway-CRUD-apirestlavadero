package com.lavadero.apirestlavadero.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lavadero.apirestlavadero.Entities.Vehiculo;
                                                        // <object type, id type>
public interface VehiculosRepository extends JpaRepository<Vehiculo, String> {

    @Query("SELECT v FROM Vehiculo v WHERE v.placa = :placa")
    Optional<Vehiculo> findVehiculoByPlaca(String placa);//consulta personalizada con codigo JPQL

    String deleteByPlaca(String placa);//consulta personalizada por spring JPA (inversión de control)
}
