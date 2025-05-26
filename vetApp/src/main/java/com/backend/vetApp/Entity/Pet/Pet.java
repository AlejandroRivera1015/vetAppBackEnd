package com.backend.vetApp.Entity.Pet;

import com.backend.vetApp.Entity.Client.Client;
import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specie;
    private String breed;
    private LocalDate birthDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "owner_id", nullable = false)
    private Client owner;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "doctor_id", nullable = false)
    private Dr doctor;

    @OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<MedicalAppointment> medicalAppointments;
}
