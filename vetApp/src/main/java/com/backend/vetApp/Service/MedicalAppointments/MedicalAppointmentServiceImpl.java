package com.backend.vetApp.Service.MedicalAppointments;

import com.backend.vetApp.Entity.Client.Client;
import com.backend.vetApp.Entity.HealtPersonal.Dr;
import com.backend.vetApp.Entity.MedicalAppointment.MedicalAppointment;
import com.backend.vetApp.Repository.Appointment.AppointmentRepository;
import com.backend.vetApp.Repository.Client.ClientRepository;
import com.backend.vetApp.Repository.Doctor.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class MedicalAppointmentServiceImpl implements  MedicalAppointmentService{

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<Dr> getAllAvailableDoctors(){
        List<Dr> availableDrs = doctorRepository.getAllAvailableDoctors();

        if (!availableDrs.isEmpty()){
            return  availableDrs;
        }
        else {
            return List.of();
        }
    }

    @Override
    public boolean validateAppointmentAvailability(LocalDateTime appointmentDateTime, Long doctorId, Long userId) {
        try{
            LocalDateTime localDateTime = LocalDateTime.parse(appointmentDateTime.toString());
            List<MedicalAppointment> appointments = doctorRepository.getDoctorAppointments(doctorId).stream()
                    .filter( appointment -> appointment.getDate().isEqual(appointmentDateTime)).toList();

            if(appointments.isEmpty()){
                Dr doctor = doctorRepository.findById(doctorId).get();
                Client client = clientRepository.findById(userId).get();
                appointmentRepository.save(new MedicalAppointment(client,doctor, localDateTime));
            }
            return appointments.isEmpty();

        } catch (Exception e) {
            System.out.println("la excepcion es" + e);
            return  false;
        }
    }
}
