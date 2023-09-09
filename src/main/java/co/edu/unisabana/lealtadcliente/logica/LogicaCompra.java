package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.bd.CompraBD;
import co.edu.unisabana.lealtadcliente.bd.CompraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogicaCompra {
    private CompraRepository compraRepository;

    public LogicaCompra(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public int generarID(){
        int id;
        List<CompraBD> compras = this.compraRepository.findAll();
        if(compras.isEmpty()){
            id=1;
        }
        else {
            int tamano = compras.size();
            CompraBD ultimaCompra = compras.get(tamano);
            id=ultimaCompra.getId_compra()+1;
        }

        return id;
    }

    public void agregarCompra(int id, ClienteBD cliente){
        CompraBD compraBD = new CompraBD();
        compraBD.setId_compra(generarID());
        compraBD.setClienteBD(cliente);
        compraBD.setId_producto(id);
        compraBD.setFecha_transaccion(LocalDateTime.now());
        this.compraRepository.save(compraBD);
    }

    public List<CompraBD> obtenerCompras(int cedula){
        return compraRepository.findByClienteBD_Cedula(cedula);
    }
}
