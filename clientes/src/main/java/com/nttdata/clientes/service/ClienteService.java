package com.nttdata.clientes.service;

import com.nttdata.clientes.dto.ClienteDTO;
import com.nttdata.clientes.exception.ClienteNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    public ClienteDTO obtenerCliente(String tipo, String numero) {
        if ("C".equalsIgnoreCase(tipo) && "23445322".equals(numero)) {
            return new ClienteDTO("C", "23445322", "Carlos", "Pérez", "carlos.perez@email.com");
        }
        throw new ClienteNotFoundException(tipo, numero);
    }

}
