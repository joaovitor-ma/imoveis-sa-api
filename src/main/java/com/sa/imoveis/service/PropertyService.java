package com.sa.imoveis.service;

import com.sa.imoveis.dto.PropertyDTO;
import com.sa.imoveis.model.Property;
import com.sa.imoveis.model.User;
import com.sa.imoveis.repository.PropertyRepository;
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
        return propertyRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Property create(PropertyDTO propertyDTO) {
        return saveProperty(null, propertyDTO);
    }

    public Property edit(Long id, PropertyDTO propertyDTO) {
        findById(id);
        return saveProperty(id, propertyDTO);
    }

    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }

    private Property saveProperty(Long id, PropertyDTO propertyDTO) {
        User consultant = userService.searchById(propertyDTO.getConsultantID()); // busca o consultor com o id passado no body
        Property propertyBody = mapper.map(propertyDTO, Property.class); // mapea propertyDTO e transforma ele em um Property
        propertyBody.setId(id); // define o id tanto quando é null quanto quando está definido
        propertyBody.setConsultant(consultant); // vincula o consultor buscado anteriormente ao imóvel
        return propertyRepository.save(propertyBody); // salva o imóvel no banco
    }
}
