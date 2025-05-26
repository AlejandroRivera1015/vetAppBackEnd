package com.backend.vetApp.Entity.Admin;

import com.backend.vetApp.Entity.User.User;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Admin extends User {
    public Admin(String email, String name, Long phoneNumber, String password, String role) {
        super(name, email, phoneNumber, password, role);
    }
}
