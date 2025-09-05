package imoveis.api.controller;

import imoveis.api.dto.AppointmentDTO;
import imoveis.api.model.Appointment;
import imoveis.api.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    private AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/customer/{id}")
    public List<Appointment> findAllByCustomer_Id(@PathVariable("id") Long id) {
        return appointmentService.findAllByCustomer_Id(id);
    }

    @GetMapping("/consultant/{id}")
    public List<Appointment> findAllByConsultant_Id(@PathVariable("id") Long id) {
        return appointmentService.findAllByConsultant_Id(id);
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.createAppointment(appointmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteAppointment(id);
    }
}
