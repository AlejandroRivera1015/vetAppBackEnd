package com.backend.vetApp.Service.Doctor;


import com.backend.vetApp.DTO.User.DoctorDTO;

import java.util.List;

public interface DoctorService {

    public List<DoctorDTO> getAvailableDoctors();

}
