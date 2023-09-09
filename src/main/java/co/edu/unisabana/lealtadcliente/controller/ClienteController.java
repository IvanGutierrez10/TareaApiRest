package co.edu.unisabana.lealtadcliente.controller;
import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaCliente;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
public class ClienteController {

    private LogicaCliente logica;

    @PutMapping(path="/cliente/actualizarpuntos/{cedula}/{nuevosPuntos}")
    public RespuestaDTO actualizarPuntos(@PathVariable int cedula, @PathVariable int nuevosPuntos){
        this.logica.actualizarPuntos(cedula,nuevosPuntos);
        return new RespuestaDTO("Puntos actualizados con exito");
    }
    @PostMapping(path="/cliente/agregar")
    public RespuestaDTO agregarCliente(@RequestBody ClienteDTO clienteDTO){
        this.logica.agregarUsuario(clienteDTO);
        return new RespuestaDTO("Cliente agregado con éxito");
    }
}
