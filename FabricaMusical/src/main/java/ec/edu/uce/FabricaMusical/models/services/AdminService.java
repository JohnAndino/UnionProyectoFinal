package ec.edu.uce.FabricaMusical.models.services;


import ec.edu.uce.FabricaMusical.models.Admin;

import java.util.List;

public interface AdminService {

    Admin saveAdmin(Admin admin);

    Admin getAdminById(Long id);

    Admin updateAdmin(Long id, Admin admin);

    List<Admin> getAllAdmini();

    void deleteAdmin(Long id);
}
