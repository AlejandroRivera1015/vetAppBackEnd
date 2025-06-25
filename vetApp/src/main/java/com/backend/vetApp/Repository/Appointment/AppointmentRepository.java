package com.backend.vetApp.Repository.Appointment;

import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    
}
