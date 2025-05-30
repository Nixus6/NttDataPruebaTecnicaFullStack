package com.nttdata.clientes.exception;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(String tipo, String numero) {
        super("Cliente no encontrado: tipo=" + tipo + ", numero=" + numero);
    }
}
