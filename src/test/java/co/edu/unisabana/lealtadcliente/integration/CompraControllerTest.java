package co.edu.unisabana.lealtadcliente.integration;

import co.edu.unisabana.lealtadcliente.controller.dto.CompraDTO;
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


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class CompraControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    /*
    @Test
    public void Dado_un_cliente_sin_compras_Cuando_obtener_historial_Entonces_retornar_lista_vacia() {
        ParameterizedTypeReference<List<CompraDTO>> responseType = new ParameterizedTypeReference<List<CompraDTO>>() {};
        ResponseEntity<List<CompraDTO>> respuesta = restTemplate.exchange("/compras/historial/123", HttpMethod.GET, null, responseType);
        List<CompraDTO> compras = respuesta.getBody();
        Assertions.assertEquals(0, compras.size());
    }

     */
}
