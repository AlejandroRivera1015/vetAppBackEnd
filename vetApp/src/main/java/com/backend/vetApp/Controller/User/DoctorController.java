package com.backend.vetApp.Controller.User;

import com.backend.vetApp.DTO.User.DoctorDTO;
import com.backend.vetApp.Service.Doctor.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorServiceImpl doctorService;

    @GetMapping("/getAllAvailable")
    public List<DoctorDTO> getAll(){
        try {
            return doctorService.getAvailableDoctors();
        }

        catch (Exception e){
            return  List.of(new DoctorDTO());
        }

    }
}
