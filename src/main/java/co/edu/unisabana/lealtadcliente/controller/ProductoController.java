package co.edu.unisabana.lealtadcliente.controller;

import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaProducto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductoController {

    private LogicaProducto Logica;

    @PutMapping(path = "/productos/redimirproducto/{id}/{cedula}")
    public RespuestaDTO redimirProducto(@PathVariable int id, @PathVariable int cedula){
        if(this.Logica.puntosSuficientes(id,cedula)){
            this.Logica.realizarCanjeo(id,cedula);
            return new RespuestaDTO("Canjeo realizado con exito");
        }
        return new RespuestaDTO("Error: Puntos insuficientes");
    }
}
