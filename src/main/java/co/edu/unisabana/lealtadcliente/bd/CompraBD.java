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
    @ManyToOne
    @JoinColumn(name = "cedula_usuario")
    private ClienteBD clienteBD;
    @Column
    private int id_producto;
    @Column
    private LocalDateTime fecha_transaccion;
}
