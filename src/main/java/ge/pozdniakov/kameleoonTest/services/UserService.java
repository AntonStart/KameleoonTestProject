package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public User findUserByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
