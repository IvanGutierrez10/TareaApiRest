package co.edu.unisabana.lealtadcliente.bd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<CompraBD, Integer> {
    List<CompraBD> findByClienteBD_Cedula(int cedula);
}
