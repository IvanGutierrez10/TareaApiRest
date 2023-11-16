package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.bd.CompraBD;
import co.edu.unisabana.lealtadcliente.bd.CompraRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.CompraDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LogicaCompra {

    private CompraRepository compraRepository;
    private ClienteRepository clienteRepository;

    public void agregarCompra(CompraDTO compra) {
        CompraBD compraBD = new CompraBD();
        compraBD.setCedulaUsuario(compra.getCedulaUsuario());
        compraBD.setIdProdcuto(compra.getIdProdcuto());
        compraBD.setFechaTransaccion(LocalDateTime.now());
        this.compraRepository.save(compraBD);
    }

    public List<CompraDTO> obtenerCompras(int cedula) {
        ClienteBD cliente = this.clienteRepository.findById(cedula).orElseThrow(() ->
                new RuntimeException("No existe ningun usuario registrado con esta cedula"));
        return cliente.getCompras()
                .stream()
                .map(CompraBD -> new CompraDTO(CompraBD.getCedulaUsuario(), CompraBD.getIdProdcuto()))
                .collect(Collectors.toList());
    }

}
