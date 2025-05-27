package com.backend.vetApp.DTO.UserDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {
    private  String email;
    private  String password;
    private  String role;
}
