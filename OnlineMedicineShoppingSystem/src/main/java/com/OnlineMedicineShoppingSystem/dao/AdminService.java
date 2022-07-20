package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Admin;

@Service
public interface AdminService {
    public boolean saveAdmin(Admin a);
    public List<Admin> fetchAdmins();
    public boolean updateAdmin(Admin a, int aid);
    public Admin deleteAdmin(int aid);
    public Admin fetchAdminById(int aid);
}
