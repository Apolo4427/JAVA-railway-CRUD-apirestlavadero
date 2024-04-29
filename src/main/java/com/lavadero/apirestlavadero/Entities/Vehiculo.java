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

    // public Vehiculo(Long id, String nombre){
    //     this.nombre=nombre;
    // }

    //constructor
    // public Vehiculo(String placa, int lavadas){
    //     this.placa = placa;
    //     this.numero_lavadas = lavadas;
    // }

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



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumero_lavadas() {
        return numero_lavadas;
    }
    public void setNumero_lavadas(int numero_lavadas) {
        this.numero_lavadas = numero_lavadas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    // public Long getId() {
    //     return id;
    // }
    // public void setId(Long id) {
    //     this.id = id;
    // }

}