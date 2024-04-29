package com.lavadero.apirestlavadero.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lavadero.apirestlavadero.Entities.Vehiculo;
import com.lavadero.apirestlavadero.Services.ServicesVehiculosImpl;

import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//          espesificamos que controlador deseamos testeasr
@WebMvcTest(VehiculoController.class)//  se utiliza en pruebas unitarias de Spring Boot para testear controladores web en un entorno de simulación de servidor HTTP.
public class VehiculoControllerTest extends MockMvcRequestBuilders {

    @Autowired
    private MockMvc mockMvc;//permite simular peticione HTTP (POST, GET, etc)

    @MockBean
    private ServicesVehiculosImpl servicesVehiculosImpl;//simulamos los servicios

    private Vehiculo vehiculo1;

    @BeforeEach
    void setUp(){
        this.vehiculo1 = Vehiculo.builder()
                                    .placa("MAF-98A")
                                    .nombre("Sergio")
                                    .numero_lavadas(1)
                                    .telefono("")
                                    .descripcion("").build();
        
    }

    @Test
    public void testRegisterVehiculo() throws Exception{
        Vehiculo metodoPostVehiculo = Vehiculo.builder()
                                    .placa("MAF-98A")
                                    .nombre("Sergio")
                                    .numero_lavadas(1)
                                    .telefono("")
                                    .descripcion("").build();

        Mockito.when(servicesVehiculosImpl.saveVehiculo(metodoPostVehiculo)).thenReturn(metodoPostVehiculo);

        mockMvc.perform(post("/registrar").contentType(MediaType.APPLICATION_JSON).content("{\n"+
                                                                                           "   \"placa\":\"DET-46G\",\n"+
                                                                                           "   \"nombre\":\"Juanita\",\n"+
                                                                                           "   \"telefono\":\"3143742829\",\n"+
                                                                                           "   \"numero_lavadas\":1,\n"+
                                                                                           "   \"descripcion\":\"mazda 2 blanco\"\n"+
                                                                                        "}")).andExpect((ResultMatcher) status().isOk());//devuleve estado HTTP 200?
    }

    @Test
    public void testFindVehiculosByPlaca()throws Exception{
        Mockito.when(servicesVehiculosImpl.findVehiculoByPlaca(this.vehiculo1.getPlaca())).thenReturn(this.vehiculo1);

        mockMvc.perform(get("/vehiculos/MAF-98A").contentType(MediaType.APPLICATION_JSON)).andExpect((ResultMatcher) status().isOk())//devuleve estado HTTP 200?
                                                                                            .andExpect((ResultMatcher) jsonPath("$.placa").value(this.vehiculo1.getPlaca()));//la respuesta en el json campo placa es igual a this.vehiculo1.getPlaca()?
    }

}
