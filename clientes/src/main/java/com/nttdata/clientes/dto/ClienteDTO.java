package com.nttdata.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private String tipo;
    private String numero;
    private String nombres;
    private String apellidos;
    private String correo;
}
