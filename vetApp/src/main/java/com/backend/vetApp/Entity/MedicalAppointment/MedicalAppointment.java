package com.backend.vetApp.Entity.MedicalAppointment;

import com.backend.vetApp.Entity.Client.Client;
import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Entity.Pet.Pet;
import com.backend.vetApp.Entity.Products.MedicalSupply.MedicalSupplyDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dr_id", nullable = false)
    @JsonIgnoreProperties({ "password", "role"})
    private Dr doctor;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "patient_id", nullable = true)
    private Pet patient;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties({ "password", "role"})
    private Client client;

    @Column(nullable = true)
    @ElementCollection
    private List<MedicalSupplyDTO> medicalSupplies;

    @Column(nullable = true)
    private LocalDateTime date;
    @Column(nullable = true)
    private String status;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private BigDecimal price;

    public MedicalAppointment(Client client,  Dr doctor, LocalDateTime appointment){
        this.client = client;
        this.doctor = doctor;
        this.date = appointment;
    }



}
