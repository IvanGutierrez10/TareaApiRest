package co.edu.unisabana.lealtadcliente.controller.dto;

import co.edu.unisabana.lealtadcliente.logica.CategoriaProductoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoRedimibleDTO {
    private String nombre;
    private CategoriaProductoEnum categoria;
    private int valor;
}
