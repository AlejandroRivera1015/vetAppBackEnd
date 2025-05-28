package com.backend.vetApp.Controller.User;


import com.backend.vetApp.DTO.User.UserDTO;
import com.backend.vetApp.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try{
            boolean response = userService.createUser(userDTO);
            if(response){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }


        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }
}
