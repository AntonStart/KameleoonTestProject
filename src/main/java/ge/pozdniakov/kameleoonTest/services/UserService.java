package ge.pozdniakov.kameleoonTest.services;

import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addNewUser(User user){
        userRepository.save(user);
    }

    public User findUserByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
