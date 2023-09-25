package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogicaClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private LogicaCliente logica;

    @Test
    void agregarUsuario() {
        //  La cantidad de pruebas que requiere un metodo esta definida por la complejidad ciclomatica del metodo
        ClienteDTO cliente = new ClienteDTO();
        cliente.setCedula(12345);
        cliente.setNombre("Bolitas");
        cliente.setApellido("Ariza");
        logica.agregarUsuario(cliente);
        Mockito.verify(clienteRepository).save(Mockito.any());
    }

    @Test
    void actualizarPuntos() {
    }

    @Test
    void mostrarClientes() {
    }

    @Test
    void clienteExiste() {
    }
}