package com.OnlineMedicineShoppingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Users")
public class Users {

    @Id
    @NotNull(message = "User_id is mandatory")
    private int user_id;

    @NotEmpty(message = "Password is mandatory")
    private String password;

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Users(int user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
    public Users() {
    }
    
    
}
