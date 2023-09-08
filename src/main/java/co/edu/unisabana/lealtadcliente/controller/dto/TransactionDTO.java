package co.edu.unisabana.lealtadcliente.controller.dto;

import lombok.Data;

@Data public class TransactionDTO {
    private Long id;
    private Long customerId;
    private double cantidad;
    private int puntosObtenidos;
}