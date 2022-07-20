package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Admin;
import com.OnlineMedicineShoppingSystem.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository arepo;

    @Override
    public boolean saveAdmin(Admin u) {
        if(arepo.findById(u.getAdmin_id())!=null)
            return false;
        arepo.save(u);
        return true;
    }

    @Override
    public List<Admin> fetchAdmins() {
        List<Admin> Admin = arepo.findAll();
        Admin.forEach(s->s.setPassword("*"));
        return Admin;
    }

    @Override
    public boolean updateAdmin(Admin u, int uid) {
        Admin oldAdmin = arepo.findById(uid);
        if(oldAdmin!=null){
             arepo.save(u);
             return true;
        }
        return false;
    }

    @Override
    public Admin deleteAdmin(int uid) {
        Admin Admin = arepo.findById(uid);
        arepo.deleteById(uid);
        return Admin;
    }

    @Override
    public Admin fetchAdminById(int uid) {
        Admin Admin = arepo.findById(uid);
        if(Admin!=null)
            return Admin;
        return null;
    }
}
