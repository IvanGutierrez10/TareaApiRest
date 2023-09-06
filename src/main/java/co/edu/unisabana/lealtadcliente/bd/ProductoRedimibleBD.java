package co.edu.unisabana.lealtadcliente.bd;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "productos_redimibles")
public class ProductoRedimibleBD {
    @Id
    @Column
    private int id;
    @Column
    private String nombre;
    @Column
    private String categoria;
    @Column
    private int valor;
}
