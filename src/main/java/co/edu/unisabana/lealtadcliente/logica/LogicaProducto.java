package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.*;
import co.edu.unisabana.lealtadcliente.controller.dto.ProductoRedimibleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogicaProducto {

    private ProductoRepository productoRepository;
    private ClienteRepository clienteRepository;
    private LogicaCompra logicaCompra;
    private LogicaCliente logicaCliente;

    public void agregarProducto(ProductoRedimibleDTO productoRedimibleDTO){
        ProductoRedimibleBD nuevoProducto = new ProductoRedimibleBD();
        nuevoProducto.setId(generarID());
        nuevoProducto.setNombre(productoRedimibleDTO.getNombre());
        nuevoProducto.setCategoria(String.valueOf(productoRedimibleDTO.getCategoria()));
        nuevoProducto.setValor(productoRedimibleDTO.getValor());
        this.productoRepository.save(nuevoProducto);
    }

    public int generarID(){
        int id=0;
        List<ProductoRedimibleBD> productos = this.productoRepository.findAll();
        if(productos.isEmpty()){
            id=1;
        }
        else {
            int tamano = productos.size()-1;
            ProductoRedimibleBD ultimoProducto = productos.get(tamano);
            id=ultimoProducto.getId()+1;
        }
        return id;
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
        this.logicaCliente.actualizarPuntos(cedula, nuevosPuntos);
        this.logicaCompra.agregarCompra(id, cedula);
    }

}
