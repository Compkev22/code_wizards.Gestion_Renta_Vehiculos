package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente autenticar(String correo, String contraseña) {
        // Busca el cliente por correo y contraseña
        return clienteRepository.findByCorreoAndContraseña(correo, contraseña);
    }
}