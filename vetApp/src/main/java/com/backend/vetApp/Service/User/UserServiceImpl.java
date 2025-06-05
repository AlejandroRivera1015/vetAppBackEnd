package com.backend.vetApp.Service.User;

import com.backend.vetApp.Config.Utils.CookieUtil;
import com.backend.vetApp.DTO.User.UserDTO;
import com.backend.vetApp.Entity.Admin.Admin;
import com.backend.vetApp.Entity.Client.Client;
import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Entity.User.User;
import com.backend.vetApp.Exception.User.UserException;
import com.backend.vetApp.Repository.Admin.AdminRepository;
import com.backend.vetApp.Repository.Client.ClientRepository;
import com.backend.vetApp.Repository.Doctor.DoctorRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import  com.backend.vetApp.Config.Utils.EncryptDataUtil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CookieUtil cookieUtil;

    @Override
    public  Boolean createUser(UserDTO userDTO) {

        try{
            String encryptedPassword = EncryptDataUtil.toEncrypt(userDTO.getPassword());
            userDTO.setPassword(encryptedPassword);
            System.out.println("User Role: " + userDTO.getRole());

            switch (userDTO.getRole()){
                case  "client" : {
                    Client newClient = new Client(userDTO.getEmail(),userDTO.getPassword(),userDTO.getRole());
                    Client client = clientRepository.save(newClient);
                    return  true;

                }
                case "doctor" : {
                    Dr newDoctor = new Dr(userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
                    Dr doctor = doctorRepository.save(newDoctor);
                    return true;

                }
                case "receptionist" : {
                    Admin newAdmin = new Admin(userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
                    Admin admin = adminRepository.save(newAdmin);
                    return true;

                }
                default:{
                    return  false  ;
                }

            }

        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public UserDTO logIn(UserDTO  userDTO, HttpServletResponse response){
        String encryptedPassword = EncryptDataUtil.toEncrypt(userDTO.getPassword());
        Map<String, String> userClaims = new HashMap<>();
        boolean isAuthenticated = false;

        try{
            switch (userDTO.getRole()) {
                case "admin", "receptionist" -> {
                    Optional<Admin> admin = adminRepository.findByEmail(userDTO.getEmail());
                    if(admin.isPresent()){
                        userClaims.put("id", admin.get().getId().toString());
                        userClaims.put("role", admin.get().getRole());
                        cookieUtil.createAuthCookie(userClaims, response);
                        if(EncryptDataUtil.toDecrypt(userDTO.getPassword(),admin.get().getPassword())){
                            isAuthenticated = true;
                            return  new UserDTO(admin.get().getId(), admin.get().getRole());
                        }
                    }
                    else  {
                        throw new UserException("UserNotFound", userDTO.getEmail());
                    }
                }

                case "doctor" ->{
                    Optional<Dr> doctor = doctorRepository.findByEmail(userDTO.getEmail());
                    if(doctor.isPresent()){
                        userClaims.put("id", doctor.get().getId().toString());
                        userClaims.put("role", doctor.get().getRole());
                        cookieUtil.createAuthCookie(userClaims, response);
                        if(EncryptDataUtil.toDecrypt(userDTO.getPassword(),doctor.get().getPassword())){
                            isAuthenticated = true;
                            return  new UserDTO(doctor.get().getId(), doctor.get().getRole());
                        }
                    }
                    else  {
                        throw new UserException("UserNotFound", userDTO.getEmail());
                    }
                }

                case "client" ->{
                    Optional<Client> client = clientRepository.findByEmail(userDTO.getEmail());
                    if(client.isPresent()){
                        userClaims.put("id", client.get().getId().toString());
                        userClaims.put("role", client.get().getRole());
                        cookieUtil.createAuthCookie(userClaims, response);
                        if(EncryptDataUtil.toDecrypt(userDTO.getPassword(),client.get().getPassword())){
                            isAuthenticated = true;
                            return  new UserDTO(client.get().getId(), client.get().getRole());
                            }
                    }
                    else  {
                        throw new UserException("UserNotFound", userDTO.getEmail());
                    }
                }
                default -> {
                    throw new UserException("UserNotFound", userDTO.getEmail());
                }
            }
        }catch (UserException e){
            return  new UserDTO(0L, "UserNotFound");
        }
        return  new UserDTO(0L, "UserNotFound");
    }
}
