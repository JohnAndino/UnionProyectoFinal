package ec.edu.uce.FabricaMusical.models.services;

import ec.edu.uce.FabricaMusical.models.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente saveCliente(Cliente cliente);

    Cliente getClienteById(Long id);

    Cliente updateCliente(Long id, Cliente cliente);

    List<Cliente> getAllClientes();

    void deleteCliente(Long id);
}