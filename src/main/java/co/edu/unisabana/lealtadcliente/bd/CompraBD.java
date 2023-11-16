package co.edu.unisabana.lealtadcliente.bd;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "compras")
public class CompraBD {
    @Id
    @Column(name = "id_compra")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCompra;
    @Column(name = "cedula_usuario")
    private int cedulaUsuario;
    @Column(name = "id_producto")
    private int idProdcuto;
    @Column(name = "fecha_transaccion")
    private LocalDateTime fechaTransaccion;
}
