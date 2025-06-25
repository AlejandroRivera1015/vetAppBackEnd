package com.backend.vetApp.Service.Doctor;

import com.backend.vetApp.DTO.User.DoctorDTO;
import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Repository.Doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<DoctorDTO> getAvailableDoctors(){

        List<Dr> availableDoctors = doctorRepository.getAllAvailableDoctors();
        List<DoctorDTO> availableDoctorsResponse =  new ArrayList<>();

       return  availableDoctorsResponse = availableDoctors.stream().map(dr -> {
            return new DoctorDTO(
                    dr.getId(),
                    dr.getName(),
                    dr.getSpecialty(),
                    dr.getMedicalAppointments().stream().map(medicalAppointment -> medicalAppointment.getDate()) .toList(),
                    dr.getStarShiftAt(),
                    dr.getRole()
            );
       }).toList();
    }
}
