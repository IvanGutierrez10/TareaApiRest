package co.edu.unisabana.lealtadcliente.bd;

import co.edu.unisabana.lealtadcliente.bd.ProductoRedimibleBD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoRedimibleBD, Integer> {
}
