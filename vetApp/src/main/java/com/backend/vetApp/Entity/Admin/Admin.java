package com.backend.vetApp.Entity.Admin;

import com.backend.vetApp.Entity.User.User;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends User {


    private LocalDateTime startShitAt;

    public Admin(String email, String name, Long phoneNumber, String password, String role) {
        super(name, email, phoneNumber, password, role);
    }

    public  Admin(String email, String password, String role) {
        super(email, password, role);

    }
    public  Admin(String email, String password, String role, LocalDateTime startShitAt) {
        super(email, password, role);
        this.startShitAt = startShitAt;

    }
}
