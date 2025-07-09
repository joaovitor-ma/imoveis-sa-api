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
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public User logIn(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User create(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) throw new EmailAlreadyExistsException();
        User userToCreate = mapper.map(userDTO, User.class);
        return userRepository.save(userToCreate);
    }

    public User edit(Long id, UserDTO userDTO) {
        searchById(id);
        User userBody = mapper.map(userDTO, User.class);
        userBody.setId(id);
        return userRepository.save(userBody);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
