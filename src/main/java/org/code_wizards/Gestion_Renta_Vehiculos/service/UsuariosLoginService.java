package org.code_wizards.Gestion_Renta_Vehiculos.service;

import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;
import org.code_wizards.Gestion_Renta_Vehiculos.repository.LoginUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosLoginService implements IUsuariosLoginService {

    @Autowired
    private LoginUsuariosRepository loginRepository;

    @Override
    public List<Cliente> listarClientes() {
        return loginRepository.findAll();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        loginRepository.save(cliente);
    }

    @Override
    public Cliente login(String correo, String contraseña) {
        return loginRepository.findByCorreoAndContraseña(correo, contraseña).orElse(null);
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        return null;
    }

    @Override
    public void eliminarCliente(Cliente cliente) {

    }


}