package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.repositories.UserRepository;
import ge.pozdniakov.kameleoonTest.util.Converter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addNewUser(UserDTO userDTO) {
        User user = Converter.convertToUser(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }


    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(Converter::convertToUserDTO)
                .collect(Collectors.toList());
    }

}
