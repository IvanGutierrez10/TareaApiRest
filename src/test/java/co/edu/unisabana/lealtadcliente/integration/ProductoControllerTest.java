package co.edu.unisabana.lealtadcliente.integration;

import co.edu.unisabana.lealtadcliente.controller.dto.ProductoRedimibleDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.CategoriaProductoEnum;
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
public class ProductoControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void Dado_un_producto_dto_Cuando_agregar_producto_Entonces_se_agrega() {
        ProductoRedimibleDTO producto = new ProductoRedimibleDTO("Bono Comida", CategoriaProductoEnum.BONOS, 400);
        ResponseEntity<RespuestaDTO> respuesta = restTemplate.postForEntity("/productos/agregar", producto, RespuestaDTO.class);
        assertEquals("Producto creado exitosamente", respuesta.getBody().getMensaje());
    }

    @Test
    public void Dado_un_producto_redimible_creado_Cuando_mostrar_productos_Entonces_lista_de_productos_redimibles_size_1() {
        ParameterizedTypeReference<List<ProductoRedimibleDTO>> responseType = new ParameterizedTypeReference<List<ProductoRedimibleDTO>>() {
        };
        ResponseEntity<List<ProductoRedimibleDTO>> respuesta = restTemplate.exchange("/productos/mostrartodos", HttpMethod.GET, null, responseType);
        List<ProductoRedimibleDTO> productos = respuesta.getBody();
        Assertions.assertEquals(1, productos.size());
    }

    @Test
    public void Dado_un_usuario_que_no_existe_Cuando_redimir_producto_Entonces_no_redimir() {
        ResponseEntity<RespuestaDTO> respuesta = restTemplate.exchange(
                "/productos/redimirproducto/567/123",
                HttpMethod.PUT,
                null,
                RespuestaDTO.class
        );
        assertEquals("El usuario ingresado no existe", respuesta.getBody().getMensaje());
    }

}
