package com.nttdata.clientes.controller;

import com.nttdata.clientes.dto.ClienteDTO;
import com.nttdata.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<ClienteDTO> obtenerCliente(
            @RequestParam String tipo,
            @RequestParam String numero) {
        log.info("Consulta de cliente - tipo: {}, numero: {}", tipo, numero);
        ClienteDTO cliente = clienteService.obtenerCliente(tipo, numero);
        return ResponseEntity.ok(cliente);
    }

}
