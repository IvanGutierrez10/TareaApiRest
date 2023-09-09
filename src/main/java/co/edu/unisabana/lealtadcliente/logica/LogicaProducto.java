package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.bd.ProductoRedimibleBD;
import co.edu.unisabana.lealtadcliente.bd.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogicaProducto {
    private ProductoRepository productoRepository;
    private ClienteRepository clienteRepository;
    private LogicaCompra logicaCompra;

    public LogicaProducto(ProductoRepository productoRepository, ClienteRepository clienteRepository, LogicaCompra logicaCompra) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.logicaCompra = logicaCompra;
    }

    public boolean puntosSuficientes(int id, int cedula){
        ClienteBD cliente = this.clienteRepository.getReferenceById(cedula);
        ProductoRedimibleBD producto = this.productoRepository.getReferenceById(id);
        if(cliente.getPuntos()>=producto.getValor()){
            return true;
        }
        return false;
    }

    public void realizarCanjeo(int id, int cedula){
        ClienteBD cliente = this.clienteRepository.getReferenceById(cedula);
        ProductoRedimibleBD producto = this.productoRepository.getReferenceById(id);
        int nuevosPuntos = cliente.getPuntos()-producto.getValor();
        cliente.setPuntos(nuevosPuntos);
        cliente.setFecha_actualizacion(LocalDateTime.now());
        this.clienteRepository.save(cliente);
        this.logicaCompra.agregarCompra(id, cliente);
    }


}
