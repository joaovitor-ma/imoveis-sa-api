package com.sa.imoveis.service;

import com.sa.imoveis.dto.UserDTO;
import com.sa.imoveis.exception.custom.EmailAlreadyExistsException;
import com.sa.imoveis.model.User;
import com.sa.imoveis.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User searchById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("The user with this ID do not exist."));
    }

    public User logIn(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User create(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) throw new EmailAlreadyExistsException("This email is already in use.");
        return saveUser(null, userDTO);
    }

    public User edit(Long id, UserDTO userDTO) {
        searchById(id);
        return saveUser(id, userDTO);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private User saveUser(Long id, UserDTO userDTO) {
        User userBody = mapper.map(userDTO, User.class);
        userBody.setId(id);
        return userRepository.save(userBody);
    }
}
