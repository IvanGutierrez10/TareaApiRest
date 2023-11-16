package co.edu.unisabana.lealtadcliente.unit.logica;

import co.edu.unisabana.lealtadcliente.bd.*;
import co.edu.unisabana.lealtadcliente.controller.dto.ProductoRedimibleDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.CategoriaProductoEnum;
import co.edu.unisabana.lealtadcliente.logica.LogicaCliente;
import co.edu.unisabana.lealtadcliente.logica.LogicaCompra;
import co.edu.unisabana.lealtadcliente.logica.LogicaProducto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogicaProductoTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private LogicaCompra logicaCompra;

    @Mock
    private LogicaCliente logicaCliente;

    @InjectMocks
    private LogicaProducto logicaProducto;

    @Test
    void Dado_un_producto_dto_Cuando_agregar_producto_Entonces_se_guarda_el_prodcuto() {
        ProductoRedimibleDTO producto = new ProductoRedimibleDTO("Bono McDonads", CategoriaProductoEnum.BONOS, 300);
        logicaProducto.agregarProducto(producto);
        Mockito.verify(productoRepository).save(Mockito.any());
    }

    @Test
    void Cuando_mostrar_productos_Entonces_se_buscan_y_muestran_los_productos() {
        List<ProductoRedimibleDTO> productos = logicaProducto.mostrarProductos();
        Mockito.verify(productoRepository).findAll();
    }

    @Test
    void Dado_un_cliente_con_puntos_suficientes_Cuando_se_verifica_puntos_suficientes_Entonces_retorna_true() {
        int id = 123;
        int cedula = 1091;
        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setNombre("Andres");
        cliente.setApellido("Mbappe");
        cliente.setPuntos(777);
        ProductoRedimibleBD producto = new ProductoRedimibleBD();
        producto.setId(id);
        producto.setNombre("FiFA 23");
        producto.setCategoria(String.valueOf(CategoriaProductoEnum.TECNOLOGIA));
        producto.setValor(750);

        Mockito.when(clienteRepository.getReferenceById(cedula)).thenReturn(cliente);
        Mockito.when(productoRepository.getReferenceById(id)).thenReturn(producto);

        boolean resultado = logicaProducto.puntosSuficientes(id, cedula);
        assertTrue(resultado);
    }

    @Test
    void Dado_un_cliente_con_puntos_insuficientes_Cuando_se_verifica_puntos_suficientes_Entonces_retorna_false() {
        int id = 123;
        int cedula = 1091;
        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setNombre("Andres");
        cliente.setApellido("Mbappe");
        cliente.setPuntos(740);
        ProductoRedimibleBD producto = new ProductoRedimibleBD();
        producto.setId(id);
        producto.setNombre("EA FC 25");
        producto.setCategoria(String.valueOf(CategoriaProductoEnum.TECNOLOGIA));
        producto.setValor(750);

        Mockito.when(clienteRepository.getReferenceById(cedula)).thenReturn(cliente);
        Mockito.when(productoRepository.getReferenceById(id)).thenReturn(producto);

        boolean resultado = logicaProducto.puntosSuficientes(id, cedula);
        assertFalse(resultado);
    }

    @Test
    void Dado_un_cliente_que_ya_existe_y_tiene_puntos_suficientes_Cuando_realiza_canjeo_Entonces_realizar_canjeo() {
        int id = 1;
        int cedula = 12345;
        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setNombre("David");
        cliente.setApellido("Sarmiento");
        cliente.setPuntos(100);
        ProductoRedimibleBD producto = new ProductoRedimibleBD();
        producto.setId(id);
        producto.setNombre("Bono de 20.000");
        producto.setCategoria(String.valueOf(CategoriaProductoEnum.BONOS));
        producto.setValor(70);

        Mockito.when(clienteRepository.existsById(cedula)).thenReturn(true);
        Mockito.when(clienteRepository.getReferenceById(cedula)).thenReturn(cliente);
        Mockito.when(productoRepository.getReferenceById(id)).thenReturn(producto);

        RespuestaDTO respuesta = logicaProducto.realizarCanjeo(id, cedula);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("Canjeo realizado con exito");

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    void Dado_un_cliente_que_ya_existe_y_no_tiene_puntos_suficientes_Cuando_realiza_canjeo_Entonces_no_realizar_canjeo() {
        int id = 1;
        int cedula = 12345;
        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setNombre("David");
        cliente.setApellido("Sarmiento");
        cliente.setPuntos(100);
        ProductoRedimibleBD producto = new ProductoRedimibleBD();
        producto.setId(id);
        producto.setNombre("Bono de 50.000");
        producto.setCategoria(String.valueOf(CategoriaProductoEnum.BONOS));
        producto.setValor(180);

        Mockito.when(clienteRepository.existsById(cedula)).thenReturn(true);
        Mockito.when(clienteRepository.getReferenceById(cedula)).thenReturn(cliente);
        Mockito.when(productoRepository.getReferenceById(id)).thenReturn(producto);

        RespuestaDTO respuesta = logicaProducto.realizarCanjeo(id, cedula);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("Cantidad de puntos insuficientes");

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    void Dado_un_cliente_que_no_existe_Cuando_realiza_canjeo_Entonces_no_realizar_canjeo() {
        int id = 1;
        int cedula = 12345;
        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setNombre("David");
        cliente.setApellido("Sarmiento");
        cliente.setPuntos(100);
        ProductoRedimibleBD producto = new ProductoRedimibleBD();
        producto.setId(id);
        producto.setNombre("Bono de 50.000");
        producto.setCategoria(String.valueOf(CategoriaProductoEnum.BONOS));
        producto.setValor(180);

        Mockito.when(clienteRepository.existsById(cedula)).thenReturn(false);
        RespuestaDTO respuesta = logicaProducto.realizarCanjeo(id, cedula);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("El usuario ingresado no existe");

        assertEquals(respuestaEsperada, respuesta);
    }

}