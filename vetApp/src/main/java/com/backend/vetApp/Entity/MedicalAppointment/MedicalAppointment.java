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
    @JoinColumn(name = "patient_id", nullable = false)
    private Pet patient;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties({ "password", "role"})
    private Client client;

    @ElementCollection
    private List<MedicalSupplyDTO> medicalSupplies;


    private LocalDateTime date;
    private String status;
    private String description;
    private BigDecimal price;

}
