package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.models.User;
import ge.pozdniakov.kameleoonTest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> showAllUser(){
        return userService.findAll();
    }

    @PostMapping
    public void addNewUser(@RequestBody UserDTO userDTO){
        userService.addNewUser(userDTO);
    }
}
