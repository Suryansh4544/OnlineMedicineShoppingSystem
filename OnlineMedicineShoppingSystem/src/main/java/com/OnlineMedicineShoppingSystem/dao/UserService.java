package com.OnlineMedicineShoppingSystem.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlineMedicineShoppingSystem.model.Users;

@Service
public interface UserService {
    public boolean saveUser(Users u);
    public List<Users> fetchUsers();
    public boolean updateUser(Users u, int uid);
    public Users deleteUser(int uid);
    public Users fetchUserById(int uid);
}
