package com.backend.vetApp.Controller.Auth;

import com.backend.vetApp.DTO.UserDTO.UserDTO;
import com.backend.vetApp.Exception.User.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody UserDTO userDTO) {
        try{
             switch (userDTO.getRole()) {
                case "admin" ->{
                    return  new ResponseEntity<>("admin", HttpStatus.OK);

                }
                case "doctor" ->{
                    return  new ResponseEntity<>("doctor", HttpStatus.OK);

                }

                case "client" ->{
                    return  new ResponseEntity<>("client", HttpStatus.OK);
                }

                default -> {
                   throw new UserException("Invalid role", userDTO.getEmail());
                }
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);        }
    }
}

