package co.edu.unisabana.lealtadcliente.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private int cedula
    private String nombre;
    private String apellido;
}
