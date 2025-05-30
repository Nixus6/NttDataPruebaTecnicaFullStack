package com.nttdata.clientes.service;

import com.nttdata.clientes.dto.ClienteDTO;
import com.nttdata.clientes.exception.ClienteNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ClienteServiceTest {
    private final ClienteService service = new ClienteService();

    @Test
    void obtenerCliente_existente_retornaCliente() {
        ClienteDTO cliente = service.obtenerCliente("C", "23445322");

        assertNotNull(cliente);
        assertEquals("Carlos", cliente.getNombres());
    }

    @Test
    void obtenerCliente_noExistente_lanzaExcepcion() {
        assertThrows(ClienteNotFoundException.class, () ->
                service.obtenerCliente("X", "999999"));
    }
}
