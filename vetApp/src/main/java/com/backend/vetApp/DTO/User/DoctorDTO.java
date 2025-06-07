package com.backend.vetApp.DTO.User;

import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Embeddable
@Getter
public class DoctorDTO {

    private String name;
    private String role;
    private String speciality;
    private LocalDateTime startShiftAt;
    private List<LocalDateTime> medicalAppointments;

    public DoctorDTO(String name, String speciality, List<LocalDateTime> medicalAppointments, LocalDateTime startShiftAt, String role ){
        this.name = name;
        this.speciality = speciality;
        this.medicalAppointments = medicalAppointments;
        this.startShiftAt = startShiftAt;
        this.role = role;

    }

}
