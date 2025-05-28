package com.backend.vetApp.Repository.Doctor;

import com.backend.vetApp.Entity.HealtPersonal.Dr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Dr, Long> {

    Optional<Dr> findByEmail(String email);
    Optional<Dr> findByEmailAndPassword(String email, String password);

}
