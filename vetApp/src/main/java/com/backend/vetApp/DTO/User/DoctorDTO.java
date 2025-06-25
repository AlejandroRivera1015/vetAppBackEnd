package com.backend.vetApp.DTO.User;

import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
public class DoctorDTO {

    private Long id;
    private String name;
    private String role;
    private String speciality;
    private LocalDateTime startShiftAt;
    private List<LocalDateTime> medicalAppointments;

    public DoctorDTO(Long id, String name, String speciality, List<LocalDateTime> medicalAppointments, LocalDateTime startShiftAt, String role ){
        this.id  = id;
        this.name = name;
        this.speciality = speciality;
        this.medicalAppointments = medicalAppointments;
        this.startShiftAt = startShiftAt;
        this.role = role;

    }

}
