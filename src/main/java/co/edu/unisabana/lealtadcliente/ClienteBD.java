package co.edu.unisabana.lealtadcliente;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "Clientes")
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
    @Column
    private LocalDateTime fecha_creacion;
    @Column
    private LocalDateTime fecha_actualizacion;

}
