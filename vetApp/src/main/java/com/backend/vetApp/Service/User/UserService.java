package com.backend.vetApp.Service.User;


import com.backend.vetApp.DTO.User.UserDTO;
import com.backend.vetApp.Entity.User.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Boolean createUser(UserDTO user);
    UserDTO logIn(UserDTO user, HttpServletResponse response);
}
