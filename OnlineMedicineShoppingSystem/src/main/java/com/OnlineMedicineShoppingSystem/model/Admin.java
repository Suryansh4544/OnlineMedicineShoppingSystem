package com.OnlineMedicineShoppingSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="admins")
public class Admin {
    @Id
    @NotNull(message = "Admin_id is mandatory")
    private int admin_id;

    @NotEmpty(message = "Password is mandatory")
    private String password;

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(@NotNull(message = "Admin_id is mandatory") int admin_id,
            @NotEmpty(message = "Password is mandatory") String password) {
        this.admin_id = admin_id;
        this.password = password;
    }

    public Admin() {
    }

    
}
