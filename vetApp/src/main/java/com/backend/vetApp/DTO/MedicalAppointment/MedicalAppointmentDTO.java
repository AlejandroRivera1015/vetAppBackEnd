package com.backend.vetApp.DTO.MedicalAppointment;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Embeddable
@NoArgsConstructor
@ToString
@Setter
@Getter
public class MedicalAppointmentDTO {

    private Long id;
    private Long doctorId;
    private Long clientId;
    private Long petId;
    private LocalDateTime date;


    public MedicalAppointmentDTO(Long  doctorId, Long clientId, LocalDateTime date){
        this.doctorId = doctorId;
        this.clientId = clientId;
        this.date = date;
    }
    public  MedicalAppointmentDTO(Long id, Long doctorId, Long clientId, LocalDateTime date){
        this.id = id;
        this.doctorId = doctorId;
        this.clientId = clientId;
        this.date = date;
    }
}
