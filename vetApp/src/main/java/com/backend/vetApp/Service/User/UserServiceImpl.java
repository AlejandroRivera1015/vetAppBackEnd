package com.backend.vetApp.Service.User;

import com.backend.vetApp.Entity.User.User;
import com.backend.vetApp.Exception.User.UserException;
import com.backend.vetApp.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public  Boolean createUser(User user) {
        try {
            User createdUser = userRepository.save(user);
            if(createdUser.equals(user)){
                return  true;
            }
            else {
                throw new UserException("cantCreateUser", 0L);
            }
        } catch(UserException e) {
            return false;
        }
    }
}
