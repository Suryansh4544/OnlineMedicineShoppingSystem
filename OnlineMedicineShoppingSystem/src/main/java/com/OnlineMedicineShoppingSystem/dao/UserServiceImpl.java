package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Users;
import com.OnlineMedicineShoppingSystem.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository urepo;

    @Override
    public boolean saveUser(Users u) {
        if(urepo.findById(u.getUser_id())!=null)
            return false;
        urepo.save(u);
        return true;
    }

    @Override
    public List<Users> fetchUsers() {
        List<Users> users = urepo.findAll();
        users.forEach(s->s.setPassword("*"));
        return users;
    }

    @Override
    public boolean updateUser(Users u, int uid) {
        Users olduser = urepo.findById(uid);
        if(olduser!=null){
             urepo.save(u);
             return true;
        }
        return false;
    }

    @Override
    public Users deleteUser(int uid) {
        Users user = urepo.findById(uid);
        urepo.deleteById(uid);
        return user;
    }

    @Override
    public Users fetchUserById(int uid) {
        Users user = urepo.findById(uid);
        if(user!=null)
            return user;
        return null;
    }
    
}
