package com.backend.vetApp.Service.User;

import com.backend.vetApp.DTO.User.UserDTO;
import com.backend.vetApp.Entity.Admin.Admin;
import com.backend.vetApp.Entity.Client.Client;
import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Exception.User.UserException;
import com.backend.vetApp.Repository.Admin.AdminRepository;
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
    @Autowired
    AdminRepository adminRepository;

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

    @Override
    public Boolean logIn(UserDTO  userDTO){
        String encryptedPassword = EncryptDataUtil.toEncrypt(userDTO.getPassword());
        System.out.println("Encrypted Password: " + encryptedPassword);
        try{
            switch (userDTO.getRole()) {
                case "admin", "receptionist" -> {
                    Optional<Admin> admin = adminRepository.findByEmail(userDTO.getEmail());
                    if(admin.isPresent()){
                        return EncryptDataUtil.toDecrypt(userDTO.getPassword(),admin.get().getPassword());
                    }
                    else  {
                        throw new UserException("UserNotFound", userDTO.getEmail());
                    }

                }

                case "doctor" ->{
                    Optional<Dr> doctor = doctorRepository.findByEmail(userDTO.getEmail());
                    if ((doctor.isPresent())){
                        return  EncryptDataUtil.toDecrypt(userDTO.getEmail(), doctor.get().getPassword());
                    }
                    else {
                        throw new UserException("UserNotFound", userDTO.getEmail());
                    }

                }

                case "client" ->{
                    Optional<Client> client = clientRepository.findByEmail(userDTO.getEmail());
                    if(client.isPresent()){
                        return EncryptDataUtil.toDecrypt(userDTO.getPassword(), client.get().getPassword());
                    }
                    else {
                        throw new UserException("UserNotFound", userDTO.getEmail());
                    }

                }

                default -> {
                    throw new UserException("UserNotFound", userDTO.getEmail());
                }
            }
        }catch (UserException e){
            return  false;
        }
    }
}
