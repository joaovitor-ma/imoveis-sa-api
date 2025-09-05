package imoveis.api.service;

import imoveis.api.dto.UserDTO;
import imoveis.api.exception.custom.EmailAlreadyExistsException;
import imoveis.api.model.User;
import imoveis.api.repository.UserRepository;
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

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User create(UserDTO userDTO) {
        if(checkEmail(userDTO.getEmail())) throw new EmailAlreadyExistsException("This email is already in use.");
        return saveUser(null, userDTO);
    }

    public User edit(Long id, UserDTO userDTO) {
        searchById(id);
        if(checkEmail(userDTO.getEmail())) throw new EmailAlreadyExistsException("This email is already in use.");
        return saveUser(id, userDTO);
    }

    public User updateWholeUser(User user) {
        searchById(user.getId());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private User saveUser(Long id, UserDTO userDTO) {
        User userBody = mapper.map(userDTO, User.class);
        userBody.setId(id);
        return userRepository.save(userBody);
    }

    private boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
