package com.nttdata.clientes.controller;

import com.nttdata.clientes.dto.ClienteDTO;
import com.nttdata.clientes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void obtenerClienteValido_retorna200() throws Exception {
        ClienteDTO mockCliente = new ClienteDTO("C", "23445322", "Carlos", "Pérez", "carlos.perez@email.com");

        when(clienteService.obtenerCliente("C", "23445322")).thenReturn(mockCliente);

        mockMvc.perform(get("/clientes")
                        .param("tipo", "C")
                        .param("numero", "23445322"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Carlos"));
    }

    @Test
    void obtenerClienteInvalido_retorna404() throws Exception {
        when(clienteService.obtenerCliente("X", "999999")).thenThrow(new RuntimeException("Cliente no encontrado"));

        mockMvc.perform(get("/clientes")
                        .param("tipo", "X")
                        .param("numero", "999999"))
                .andExpect(status().is5xxServerError());
    }
}
