package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class LogicaClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private LogicaCliente logica;

    @Test
    void agregarUsuario() {
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