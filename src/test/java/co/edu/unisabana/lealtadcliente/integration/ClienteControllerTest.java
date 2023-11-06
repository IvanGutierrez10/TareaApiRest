package co.edu.unisabana.lealtadcliente.integration;

import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
class ClienteControllerTest {


    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void Dado_un_cliente_dto_Cuando_agregar_cliente_Entonces_se_agrega() {
        ClienteDTO cliente = new ClienteDTO(242750, "Sebastian", "Pardo");
        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/cliente/agregar", cliente, RespuestaDTO.class);
        assertEquals("Cliente creado con exito", respuesta.getBody().getMensaje());
    }

    @Test
    void Dado_ningun_cliente_creado_Cuando_mostrar_cliente_Entonces_lista_de_clientes_size_0() {
        ParameterizedTypeReference<List<ClienteDTO>> responseType = new ParameterizedTypeReference<List<ClienteDTO>>() {
        };
        ResponseEntity<List<ClienteDTO>> respuesta = restTemplate.exchange("/cliente/mostrartodos", HttpMethod.GET, null, responseType);
        List<ClienteDTO> clientes = respuesta.getBody();
        Assertions.assertEquals(0, clientes.size());
    }

    @Test
    void Dado_un_usuario_que_no_existe_Cuando_actualizar_puntos_Entonces_no_se_actualiza() {
        ResponseEntity<RespuestaDTO> respuesta = restTemplate.exchange(
                "/cliente/actualizarpuntos/123/100",
                HttpMethod.PUT,
                null,
                RespuestaDTO.class
        );
        assertEquals("No existe ningun usuario registrado con esa cedula", respuesta.getBody().getMensaje());
    }


}
