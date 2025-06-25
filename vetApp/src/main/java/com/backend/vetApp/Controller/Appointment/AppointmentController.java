package com.backend.vetApp.Controller.Appointment;


import com.backend.vetApp.DTO.MedicalAppointment.MedicalAppointmentDTO;
import com.backend.vetApp.Exception.Appointment.AppointmentException;
import com.backend.vetApp.Service.MedicalAppointments.MedicalAppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    MedicalAppointmentServiceImpl appointmentService;

    @PostMapping("/create")
    public ResponseEntity<?> getAppointmentRequest(@RequestBody MedicalAppointmentDTO appointment){
        System.out.println("el appointment es" + appointment);
        try{
            boolean validation = appointmentService.validateAppointmentAvailability(appointment.getDate(), appointment.getDoctorId(), appointment.getClientId());
            if (validation){
                return  new ResponseEntity<>(validation, HttpStatus.OK);
            }
            return new ResponseEntity<>(false,HttpStatus.CONFLICT);




        } catch ( Exception es) {
            System.out.println("Error al procesar la solicitud de cita: " + es.getMessage());
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }

    }
}
