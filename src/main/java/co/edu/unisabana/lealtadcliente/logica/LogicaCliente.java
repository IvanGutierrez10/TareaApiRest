package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import co.edu.unisabana.lealtadcliente.controller.dto.RespuestaDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LogicaCliente {

    private ClienteRepository clienteRepository;

    public RespuestaDTO agregarUsuario(ClienteDTO clienteDTO){
        if(clienteExiste(clienteDTO.getCedula())){
            return new RespuestaDTO("Ya existe un usuario registrado con esa cedula");
        }
        else{
            ClienteBD clienteBD = new ClienteBD();
            clienteBD.setCedula(clienteDTO.getCedula());
            clienteBD.setNombre(clienteDTO.getNombre());
            clienteBD.setApellido(clienteDTO.getApellido());
            clienteBD.setPuntos(0);
            clienteBD.setFecha_creacion(LocalDateTime.now());
            clienteBD.setFecha_actualizacion(LocalDateTime.now());
            this.clienteRepository.save(clienteBD);
            return new RespuestaDTO("Cliente creado con exito");
        }
    }

    public RespuestaDTO actualizarPuntos(int cedula, int nuevosPuntos){
        if(clienteExiste(cedula)){
            ClienteBD cliente = this.clienteRepository.getReferenceById(cedula);
            cliente.setPuntos(nuevosPuntos);
            cliente.setFecha_actualizacion(LocalDateTime.now());
            this.clienteRepository.save(cliente);
            return new RespuestaDTO("Los puntos del cliente han sido actualizados");
        }
        else {
            return new RespuestaDTO("No existe ningun usuario registrado con esa cedula");
        }
    }

    public boolean clienteExiste (int cedula){
        return this.clienteRepository.existsById(cedula);
    }
}
