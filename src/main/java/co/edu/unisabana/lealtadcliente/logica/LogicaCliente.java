package co.edu.unisabana.lealtadcliente.logica;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import co.edu.unisabana.lealtadcliente.bd.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogicaCliente {
    private ClienteRepository clienteRepository;

    public LogicaCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void actualizarPuntos(int cedula, int nuevosPuntos){
        ClienteBD cliente = this.clienteRepository.getReferenceById(cedula);
        cliente.setPuntos(nuevosPuntos);
        cliente.setFecha_actualizacion(LocalDateTime.now());
        this.clienteRepository.save(cliente);
    }
}
