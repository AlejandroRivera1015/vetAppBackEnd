package com.backend.vetApp.DTO.User;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private  String email;
    private  String password;
    private  String role;
    private Long id;

    public UserDTO(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(Long id, String role) {
        this.role = role;
        this.id = id;
    }
}