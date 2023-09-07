package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import co.edu.unisabana.lealtadcliente.controller.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogicaCliente {
    private ClienteRepository clienteRepository;

    public LogicaCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void agregarUsuario(ClienteDTO clienteDTO){
        ClienteBD clienteBD = new ClienteBD();
        clienteBD.setCedula(clienteDTO.getCedula());
        clienteBD.setNombre(clienteDTO.getNombre());
        clienteBD.setApellido(clienteDTO.getApellido());
        clienteBD.setPuntos(0);
        clienteBD.setFecha_creacion(LocalDateTime.now());
        clienteBD.setFecha_actualizacion(LocalDateTime.now());
        this.clienteRepository.save(clienteBD);
    }

    public void actualizarPuntos(int cedula, int nuevosPuntos){
        ClienteBD cliente = this.clienteRepository.getReferenceById(cedula);
        cliente.setPuntos(nuevosPuntos);
        cliente.setFecha_actualizacion(LocalDateTime.now());
        this.clienteRepository.save(cliente);
    }
}
