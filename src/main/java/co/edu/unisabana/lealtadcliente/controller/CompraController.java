package co.edu.unisabana.lealtadcliente.controller;

import co.edu.unisabana.lealtadcliente.bd.CompraBD;
import co.edu.unisabana.lealtadcliente.logica.LogicaCompra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraController {

    private LogicaCompra logicaCompra;


    public CompraController(LogicaCompra logicaCompra) {
        this.logicaCompra = logicaCompra;
    }

    @GetMapping(path = "/compras/historial/{cedula}")
    public List<CompraBD> obtenerHistorial(@PathVariable int cedula){
        return this.logicaCompra.obtenerCompras(cedula);
    }
}
