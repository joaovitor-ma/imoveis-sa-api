package imoveis.api.service;

import imoveis.api.dto.AppointmentDTO;
import imoveis.api.exception.custom.InvalidDateException;
import imoveis.api.model.Appointment;
import imoveis.api.model.Property;
import imoveis.api.model.User;
import imoveis.api.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PropertyService propertyService;
    private final UserService userService;

    public AppointmentService(AppointmentRepository appointmentRepository, PropertyService propertyService, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.propertyService = propertyService;
        this.userService = userService;
    }

    public List<Appointment> findAllByCustomer_Id(Long id) {
        userService.searchById(id);
        return appointmentRepository.findAllByCustomer_Id(id);
    }

    public List<Appointment> findAllByConsultant_Id(Long id) {
        userService.searchById(id);
        return appointmentRepository.findAllByConsultant_Id(id);
    }

    public Appointment createAppointment(AppointmentDTO appointmentDTO) throws InvalidDateException {
        Long customerId = appointmentDTO.getCustomerId();
        Long propertyId = appointmentDTO.getPropertyId();
        LocalDateTime enteredDate = appointmentDTO.getDate();

        propertyService.findById(propertyId); // Checa se o imóvel com o ID passado existe, se não existir joga uma exception
        userService.searchById(customerId); // Checa se o cliente com o ID passado existe, se não existir joga uma exception

        LocalDateTime currentTime = LocalDateTime.now(); // Pega a data atual
        if(enteredDate.isBefore(currentTime)) { // Checa se a data inserida é antes da data atual, se sim, joga uma exception
            throw new InvalidDateException("The entered date is not valid.");
        }

        Property property = propertyService.findById(propertyId); // Busca um imóvel pelo ID passado
        Long consultantId = property.getConsultantId();

        User customer = userService.searchById(customerId); // Busca cliente pelo ID passado
        User consultant = userService.searchById(consultantId); // Busca consultor pelo ID vinculado ao imóvel

        Appointment appointmentToCreate = newAppointment(property, enteredDate, customer, consultant);

        Appointment savedAppointment = appointmentRepository.save(appointmentToCreate);

        customer.getAppointments().add(savedAppointment.getId()); // Vincula o agendamento ao perfil do usuário que o fez
        consultant.getAppointments().add(savedAppointment.getId()); // Vincula o agendamento ao perfil do consultor responsável pelo imóvel

        userService.updateWholeUser(customer);
        userService.updateWholeUser(consultant);

        // TODO: Criar uma forma de limitar as datas e horarios possíveis para agendamento

        return savedAppointment;
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private static Appointment newAppointment(Property property, LocalDateTime enteredDate, User customer, User consultant) {
        Appointment newAppointment = new Appointment();
        newAppointment.setId(null);
        newAppointment.setDate(enteredDate); // Define a data para a data passada
        newAppointment.setCustomer(customer); // Define o ID do cliente para o ID passado
        newAppointment.setConsultant(consultant); // Define o ID do consultor pelo ID vinculado ao imóvel
        newAppointment.setProperty(property); // Vincula o imóvel ao agendamento
        return newAppointment;
    }
}
