package ec.edu.uce.FabricaMusical.models.repository;

import ec.edu.uce.FabricaMusical.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
