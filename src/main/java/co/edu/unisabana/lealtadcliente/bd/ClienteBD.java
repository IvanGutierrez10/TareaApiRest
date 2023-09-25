package co.edu.unisabana.lealtadcliente.bd;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table (name = "clientes")
public class ClienteBD {
    @Id
    @Column
    private int cedula;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private int puntos;
    @Column (name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column (name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn (name = "cedula_usuario")
    private List<CompraBD> compras;
}
