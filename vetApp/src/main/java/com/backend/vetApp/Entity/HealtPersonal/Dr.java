package com.backend.vetApp.Entity.HealtPersonal;

import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import com.backend.vetApp.Entity.Pet.Pet;
import com.backend.vetApp.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dr extends User {

    private String specialty;
    private Boolean isAvailable;
    private LocalDateTime starShiftAt;

    @OneToMany( mappedBy = "doctor")
    @JsonManagedReference
    private  List<Pet> patients;

    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private  List<MedicalAppointment> medicalAppointments;

    public Dr(String name, String email, Long phoneNumber, String password, String role, String specialty) {
        super(name, email, phoneNumber, password, role);
        this.specialty = specialty;
    }

        public Dr(String email, String password, String role) {
        super(email, password, role);
    }
}
