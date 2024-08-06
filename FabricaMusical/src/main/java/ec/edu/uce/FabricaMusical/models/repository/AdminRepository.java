package ec.edu.uce.FabricaMusical.models.repository;

import ec.edu.uce.FabricaMusical.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
