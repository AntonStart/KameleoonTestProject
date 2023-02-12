package ge.pozdniakov.kameleoonTest.controllers;

import ge.pozdniakov.kameleoonTest.dto.UserDTO;
import ge.pozdniakov.kameleoonTest.services.UserService;
import ge.pozdniakov.kameleoonTest.util.KameleoonTestErrorResponse;
import ge.pozdniakov.kameleoonTest.util.UserNotCreatedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
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

    @PostMapping
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
        userService.addNewUser(userDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    ///this method is required for debug
    @GetMapping
    public List<UserDTO> showAllUser(){
        return userService.findAll();
    }

    @ExceptionHandler
    private ResponseEntity<KameleoonTestErrorResponse> handleException(UserNotCreatedException userNotCreatedException){
        KameleoonTestErrorResponse userErrorResponse = new KameleoonTestErrorResponse(
                userNotCreatedException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(userErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
