package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.services.UserService;
import ge.pozdniakov.kameleoonTest.util.Converter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    ///this method is required for debug
    @GetMapping
    public List<UserDTO> showAllUser(){
        return userService.findAll().stream().map(user -> Converter.convertToUserDTO(user)).collect(Collectors.toList());
    }

    @PostMapping
    public void addNewUser(@RequestBody @Valid UserDTO userDTO){
        userService.addNewUser(userDTO);
    }
}
