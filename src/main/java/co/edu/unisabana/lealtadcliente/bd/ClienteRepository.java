package co.edu.unisabana.lealtadcliente.bd;

import co.edu.unisabana.lealtadcliente.bd.ClienteBD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteBD, Integer> {
}
