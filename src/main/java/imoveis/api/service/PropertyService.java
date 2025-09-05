package imoveis.api.service;

import imoveis.api.dto.PropertyDTO;
import imoveis.api.model.Property;
import imoveis.api.model.User;
import imoveis.api.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final UserService userService;
    private final ModelMapper mapper = new ModelMapper();

    // TODO: Para o futuro: prever possíveis erros e criar tratamento adequado para eles

    private PropertyService(PropertyRepository propertyRepository, UserService userService) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("The property with this ID do not exist."));
    }

    public Property create(PropertyDTO propertyDTO) {
        return saveProperty(null, propertyDTO);
    }

    public Property edit(Long id, PropertyDTO propertyDTO) {
        findById(id);
        return saveProperty(id, propertyDTO);
    }

    public void delete(Long id) {
        Property property = findById(id);
        User user = userService.searchById(property.getConsultantId()); // Busca o usuário pelo customerId passado

        user.setProperties(
                user
                        .getProperties() // Puxa a lista de favoritos
                        .stream()
                        .filter(p -> !p.getId().equals(id)) // Filtra para deixar apenas os imóveis que tiverem o ID diferente de propertyId
                        .toList() // Transforma em uma lista
        );

        propertyRepository.deleteById(id);
    }

    private Property saveProperty(Long id, PropertyDTO propertyDTO) {
        Long consultantID = propertyDTO.getConsultantId();

        User consultant = userService.searchById(consultantID); // Busca o consultor com o id passado no body
        Property propertyBody = mapper.map(propertyDTO, Property.class); // Mapea propertyDTO e transforma ele em um Property

        propertyBody.setId(id); // Define o id tanto quando é null quanto quando está definido

        if(id == null) {
            consultant.getProperties().add(propertyBody); // Vincula o imóvel às propriedades do consultor
        }

        return propertyRepository.save(propertyBody); // Salva o imóvel no banco
    }
}
