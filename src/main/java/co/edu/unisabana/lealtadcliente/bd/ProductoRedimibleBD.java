package co.edu.unisabana.lealtadcliente.bd;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "productos_redimibles")
public class ProductoRedimibleBD {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String categoria;
    @Column
    private int valor;
}
