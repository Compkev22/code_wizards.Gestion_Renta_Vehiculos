package org.code_wizards.Gestion_Renta_Vehiculos.service;
import org.code_wizards.Gestion_Renta_Vehiculos.entity.Cliente;

import java.util.List;

public interface IUsuariosLoginService {
    List<Cliente> listarClientes();
    public void guardarCliente(Cliente cliente);
    Cliente login(String email, String contraseña);
    public Cliente buscarClientePorId(Integer idCliente);
    public void eliminarCliente(Cliente cliente);
}