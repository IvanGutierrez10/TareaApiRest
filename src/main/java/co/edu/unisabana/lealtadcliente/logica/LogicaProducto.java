package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.bd.ProductoRedimibleBD;
import co.edu.unisabana.lealtadcliente.bd.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogicaProducto {

    private ProductoRepository productoRepository;
    private ClienteRepository clienteRepository;
    private LogicaCompra logicaCompra;
    private LogicaCliente logicaCliente;

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
        this.logicaCliente.actualizarPuntos(cedula, nuevosPuntos);
        this.logicaCompra.agregarCompra(id, cedula);
    }

}
