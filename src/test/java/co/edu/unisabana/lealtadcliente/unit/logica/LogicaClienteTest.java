package co.edu.unisabana.lealtadcliente.unit.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaCliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogicaClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private LogicaCliente logicaCliente;

    @Test
    void Dado_cliente_que_no_existe_Cuando_guarde_Entonces_se_guarda() {
        ClienteDTO cliente = new ClienteDTO(123, "Ivan", "Gutierrez");
        Mockito.when(clienteRepository.existsById(123)).thenReturn(Boolean.FALSE);
        RespuestaDTO respuesta = logicaCliente.agregarUsuario(cliente);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("Cliente creado con exito");

        Mockito.verify(clienteRepository).save(Mockito.any());
        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    void Dado_cliente_que_ya_existe_Cuando_guarde_Entonces_no_se_guarda() {
        ClienteDTO cliente = new ClienteDTO(123, "Ivan", "Gutierrez");
        Mockito.when(clienteRepository.existsById(123)).thenReturn(Boolean.TRUE);
        RespuestaDTO respuesta = logicaCliente.agregarUsuario(cliente);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("Ya existe un usuario registrado con esa cedula");
        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    void Dado_cliente_que_ya_existe_Cuando_actualice_puntos_Entonces_se_actualiza() {
        int cedula = 100;

        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setPuntos(600);

        Mockito.when(clienteRepository.existsById(cedula)).thenReturn(Boolean.TRUE);
        Mockito.when(clienteRepository.getReferenceById(cedula)).thenReturn(cliente);
        RespuestaDTO respuesta = logicaCliente.actualizarPuntos(100, 300);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("Los puntos del cliente han sido actualizados");

        Mockito.verify(clienteRepository).save(Mockito.any());
        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    void Dado_cliente_que_no_existe_Cuando_actualice_puntos_Entonces_no_se_actualiza() {
        int cedula = 150;

        ClienteBD cliente = new ClienteBD();
        cliente.setCedula(cedula);
        cliente.setPuntos(600);

        Mockito.when(clienteRepository.existsById(cedula)).thenReturn(Boolean.FALSE);
        RespuestaDTO respuesta = logicaCliente.actualizarPuntos(150, 300);
        RespuestaDTO respuestaEsperada = new RespuestaDTO("No existe ningun usuario registrado con esa cedula");

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    void Cuando_mostrar_clientes_Entonces_se_buscan_y_muestran_los_clientes() {
        List<ClienteDTO> clientes = logicaCliente.mostrarClientes();
        Mockito.verify(clienteRepository).findAll();
    }

    @Test
    void Dado_un_cliente_que_ya_existe_Cuando_buscamos_si_existe_Entonces_retorna_true() {
        int cedula = 123;
        Mockito.when(clienteRepository.existsById(cedula)).thenReturn(Boolean.TRUE);

        boolean existe = logicaCliente.clienteExiste(cedula);
        Mockito.verify(clienteRepository).existsById(cedula);
        assertTrue(existe);
    }
}