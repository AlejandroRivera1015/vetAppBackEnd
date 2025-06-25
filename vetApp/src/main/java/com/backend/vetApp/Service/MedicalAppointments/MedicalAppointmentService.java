package com.backend.vetApp.Service.MedicalAppointments;

import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Repository.Doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface MedicalAppointmentService {
    public List<Dr> getAllAvailableDoctors();
    public boolean validateAppointmentAvailability(LocalDateTime appointmentDateTime, Long doctorId, Long userId);
}
