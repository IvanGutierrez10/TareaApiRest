package co.edu.unisabana.lealtadcliente;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ClienteController {

    ClienteRepository usuarioRepository;

    @GetMapping(path="/estudiantes")
    public List<ClienteBD> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }
}
