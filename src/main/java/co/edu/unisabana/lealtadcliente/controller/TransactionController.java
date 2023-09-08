package co.edu.unisabana.lealtadcliente.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final LogicaTransaction logicaTransaction;

    // Punto final para consultar el historial de transacciones de un cliente
    @GetMapping("/{customerId}")
    public List<Transaction> getTransactions(@PathVariable Long customerId) {
        List<Transaction> transactions = logicaTransaction.getTransactionsByCustomerId(customerId);
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron transacciones para este cliente");
        }
        return transactions;
    }
}