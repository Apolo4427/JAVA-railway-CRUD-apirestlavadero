package com.lavadero.apirestlavadero.Entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;



@Entity
@Data//genera automáticamente ciertos métodos como toString(), equals(), hashCode(), getters y setters
@Builder
public class Vehiculo {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    @NotBlank(message = "Por favor ingrese una placa, no registrada anteriormente")
    @Length(min = 7, message = "la placa debe tener min 7 caracteres, ejemplo: XXX-123, recuerda incluir el gion('-')")
    private String placa;
    private String nombre;
    @Length(min=10, max =14)
    private String telefono;
    @DecimalMin(value = "1", message = "El valor debe ser igual o mayor a 1")
    @DecimalMax(value = "6", message = "El valor debe ser igual o menor a 6")
    private int numero_lavadas;
    private String descripcion; 

    public Vehiculo(){

    }

    public Vehiculo(
            @NotBlank(message = "Por favor ingrese una placa, no registrada anteriormente") @Length(min = 7, message = "la placa debe tener min 7 caracteres, ejemplo: XXX-123, recuerda incluir el gion('-')") String placa,
            String nombre, @Length(min = 10, max = 14) String telefono,
            @DecimalMin(value = "1", message = "El valor debe ser igual o mayor a 1") @DecimalMax(value = "6", message = "El valor debe ser igual o menor a 6") int numero_lavadas,
            String descripcion) {
        this.placa = placa;
        this.nombre = nombre;
        this.telefono = telefono;
        this.numero_lavadas = numero_lavadas;
        this.descripcion = descripcion;
    }

}