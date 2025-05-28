package com.backend.vetApp.Service.User;

import com.backend.vetApp.DTO.User.UserDTO;
import com.backend.vetApp.Entity.Client.Client;
import com.backend.vetApp.Repository.Client.ClientRepository;
import com.backend.vetApp.Repository.Doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import  com.backend.vetApp.Config.Utils.EncryptDataUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public  Boolean createUser(UserDTO userDTO) {

        try{
            String encryptedPassword = EncryptDataUtil.toEncrypt(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);

            switch (userDTO.getRole()){
                case  "client" : {
                    Client newClient = new Client(userDTO.getEmail(),userDTO.getPassword(),userDTO.getRole());
                    Client client = clientRepository.save(newClient);
                    return  true;
                }
                case "doctor" : {

                }
                case "receptionist" : {

                }
                default:{

                }

            }

        }catch (Exception e){
            return  false;

        }

        return  false;

    }
}
