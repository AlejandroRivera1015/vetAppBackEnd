package com.backend.vetApp.Repository.Doctor;

import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Dr, Long> {

    Optional<Dr> findByEmail(String email);
    Optional<Dr> findByEmailAndPassword(String email, String password);
    @Query("SELECT dr FROM Dr dr WHERE dr.isAvailable = true")
    List<Dr> getAllAvailableDoctors();
    @Query("SELECT medicalAppointments FROM Dr dr WHERE dr.id = :doctorId")
    List<MedicalAppointment> getDoctorAppointments(@Param("doctorId") Long doctorId);

}
