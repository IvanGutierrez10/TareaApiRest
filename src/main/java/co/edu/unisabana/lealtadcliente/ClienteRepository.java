package co.edu.unisabana.lealtadcliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteBD, Integer> {
}
