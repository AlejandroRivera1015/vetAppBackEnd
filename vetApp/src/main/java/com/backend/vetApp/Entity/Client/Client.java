package com.backend.vetApp.Entity.Client;

import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import com.backend.vetApp.Entity.Pet.Pet;
import com.backend.vetApp.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Pet> pets;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private  List<MedicalAppointment> medicalAppointments;


    public  Client(String name, String email, Long phoneNumber, String password, String role) {
        super(name, email, phoneNumber, password, role);
    }
    public  Client(String email, String password, String role) {
        super(email, password, role);
    }



}
