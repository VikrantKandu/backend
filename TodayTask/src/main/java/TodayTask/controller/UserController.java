package TodayTask.controller;

import TodayTask.dto.LoginDto;
import TodayTask.dto.LoginResponseDto;
import TodayTask.dto.SignUpDto;
import TodayTask.dto.SignUpResponseDto;
import TodayTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign/up")
    public ResponseEntity<SignUpResponseDto> signUpUser(@RequestBody SignUpDto signUpDto) throws Exception {

        return userService.saveTheDataOfNewUser(signUpDto);

    }

    @PostMapping("/sign/in")
    public ResponseEntity<LoginResponseDto> signInUser(@RequestBody LoginDto loginDto) throws Exception {
        return userService.loginExistingUser(loginDto);
    }
}