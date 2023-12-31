package co.edu.unisabana.lealtadcliente.controller;

import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import co.edu.unisabana.lealtadcliente.logica.LogicaCliente;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
public class ClienteController {

    private LogicaCliente logica;

    @GetMapping(path = "/cliente/mostrartodos")
    public List<ClienteDTO> mostrarClientes() {
        return this.logica.mostrarClientes();
    }

    @PutMapping(path = "/cliente/actualizarpuntos/{cedula}/{nuevosPuntos}")
    public RespuestaDTO actualizarPuntos(@PathVariable int cedula, @PathVariable int nuevosPuntos) {
        return this.logica.actualizarPuntos(cedula, nuevosPuntos);
    }

    @PostMapping(path = "/cliente/agregar")
    public RespuestaDTO agregarCliente(@RequestBody ClienteDTO clienteDTO) {
        return this.logica.agregarUsuario(clienteDTO);
    }
}
