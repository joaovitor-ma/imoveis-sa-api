package com.sa.imoveis.repository;

import com.sa.imoveis.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByCustomer_Id(Long customerId);
    List<Appointment> findAllByConsultant_Id(Long customerId);
}
