package co.edu.unisabana.lealtadcliente.bd;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "compras")
public class CompraBD {
    @Id
    @Column
    private int id_compra;
    @Column
    private int cedula_usuario;
    @Column
    private int id_producto;
    @Column
    private LocalDateTime fecha_transaccion;
}
