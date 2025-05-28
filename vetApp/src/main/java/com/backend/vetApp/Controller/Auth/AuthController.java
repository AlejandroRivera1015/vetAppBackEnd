package com.backend.vetApp.Controller.Auth;

import com.backend.vetApp.DTO.User.UserDTO;
import com.backend.vetApp.Exception.User.UserException;
import com.backend.vetApp.Service.User.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody UserDTO userDTO, HttpServletResponse httpResponse) {
        try{
            Boolean response = userService.logIn(userDTO, httpResponse);
            if (response) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

