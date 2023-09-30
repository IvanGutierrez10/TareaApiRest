package co.edu.unisabana.lealtadcliente.controller.dto;


import lombok.Data;

@Data
public class RespuestaDTO {
    String mensaje;

    public RespuestaDTO() {

    }

    public RespuestaDTO(String mensaje) {
        this.mensaje = mensaje;
    }

}
