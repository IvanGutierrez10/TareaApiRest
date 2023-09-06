package co.edu.unisabana.lealtadcliente.controller;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaCliente;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClienteController {

    private LogicaCliente logica;

    public ClienteController(LogicaCliente logica) {
        this.logica = logica;
    }

    @PutMapping(path="/cliente/actualizarpuntos")
    public RespuestaDTO actualizarPuntos(@RequestParam int cedula, @RequestParam int nuevosPuntos){
        this.logica.actualizarPuntos(cedula,nuevosPuntos);
        return new RespuestaDTO("Puntos actualizados con exito");
    }
}
