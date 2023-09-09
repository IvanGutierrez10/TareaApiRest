package co.edu.unisabana.lealtadcliente.controller;

import co.edu.unisabana.lealtadcliente.controller.dto.ProductoRedimibleDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaProducto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductoController {

    private LogicaProducto Logica;

    @PostMapping(path="/productos/agregar")
    public RespuestaDTO agregarProducto(@RequestBody ProductoRedimibleDTO productoRedimibleDTO){
        this.Logica.agregarProducto(productoRedimibleDTO);
        return new RespuestaDTO("Producto creado exitosamente");
    }
    @PutMapping(path = "/productos/redimirproducto/{id}/{cedula}")
    public RespuestaDTO redimirProducto(@PathVariable int id, @PathVariable int cedula){
        return this.Logica.realizarCanjeo(id, cedula);
    }
}
