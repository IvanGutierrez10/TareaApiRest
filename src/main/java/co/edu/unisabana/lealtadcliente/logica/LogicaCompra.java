package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.bd.CompraBD;
import co.edu.unisabana.lealtadcliente.bd.CompraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LogicaCompra {

    private CompraRepository compraRepository;
    private ClienteRepository clienteRepository;

    public int generarID() {
        int id = 0;
        List<CompraBD> compras = this.compraRepository.findAll();
        if (compras.isEmpty()) {
            id = 1;
        } else {
            int tamano = compras.size() - 1;
            CompraBD ultimaCompra = compras.get(tamano);
            id = ultimaCompra.getIdCompra() + 1;
        }

        return id;
    }

    public void agregarCompra(int id, int cedula) {
        CompraBD compraBD = new CompraBD();
        compraBD.setCedulaUsuario(cedula);
        compraBD.setIdCompra(generarID());
        compraBD.setIdProdcuto(id);
        compraBD.setFechaTransaccion(LocalDateTime.now());
        this.compraRepository.save(compraBD);
    }

    public List<CompraBD> obtenerCompras(int cedula) {
        ClienteBD cliente = this.clienteRepository.findById(cedula).orElseThrow(() ->
                new RuntimeException("No existe ningun usuario registrado con esta cedula"));
        return cliente.getCompras();
    }

}
