package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.ClienteRepository;
import org.code_wizards.Gestion_Renta_Vehiculos.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Integer id) {
        return this.clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        this.clienteRepository.delete(cliente);
    }

    public Cliente autenticar(String correo, String contraseña) {
        // Busca el cliente por correo y contraseña
        return clienteRepository.findByCorreoAndContraseña(correo, contraseña);
    }

}