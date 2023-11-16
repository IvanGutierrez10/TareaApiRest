package co.edu.unisabana.lealtadcliente.unit.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.bd.CompraBD;
import co.edu.unisabana.lealtadcliente.bd.CompraRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.CompraDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaCompra;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogicaCompraTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private LogicaCompra logicaCompra;

    @Test
    void Dada_una_compra_dto_Cuando_agregar_compra_Entonces_se_guarda_la_compra() {
        CompraDTO compra = new CompraDTO(123, 456);
        logicaCompra.agregarCompra(compra);
        Mockito.verify(compraRepository).save(Mockito.any());
    }

    @Test
    void Dada_la_cedula_de_un_cliente_que_existe_y_tiene_1_compra_Cuando_obtener_compras_Entonces_obtiene_una_lista_de_size_1() {

        List<CompraBD> compras = new ArrayList<>();
        CompraBD compraBD = new CompraBD();
        compraBD.setCedulaUsuario(12345);
        compraBD.setIdCompra(1);
        compraBD.setIdProdcuto(777);
        compraBD.setFechaTransaccion(LocalDateTime.now());
        compras.add(compraBD);

        ClienteBD clienteBD = new ClienteBD();
        clienteBD.setCedula(12345);
        clienteBD.setCompras(compras);


        Mockito.when(clienteRepository.findById(12345)).thenReturn(Optional.of(clienteBD));

        List<CompraDTO> comprasCliente = logicaCompra.obtenerCompras(12345);

        assertEquals(1, comprasCliente.size());
    }

    @Test
    void Dada_la_cedula_de_un_cliente_que_no_existe_Cuando_obtener_compras_Entonces_arroja_excepcion() {

        int cedula = 1111;
        Mockito.when(clienteRepository.findById(cedula)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            List<CompraDTO> comprasCliente = logicaCompra.obtenerCompras(1111);
        });
    }
}