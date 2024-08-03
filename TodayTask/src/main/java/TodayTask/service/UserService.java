package TodayTask.service;


import TodayTask.Secuirty.JwtUtil;
import TodayTask.dto.LoginDto;
import TodayTask.dto.LoginResponseDto;
import TodayTask.dto.SignUpDto;
import TodayTask.dto.SignUpResponseDto;
import TodayTask.exception.ErrorCode;
import TodayTask.exception.TodayTaskException;
import TodayTask.model.User;
import TodayTask.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<SignUpResponseDto> saveTheDataOfNewUser(SignUpDto signUpDto) throws Exception {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();

        Optional<User> existingUser = userRepo.findByEmail(signUpDto.getEmail());
        if (existingUser.isPresent()) {
            throw new TodayTaskException("Email is already registered, please sign in", ErrorCode.USER_IS_ALREADY_PRESENT);
        }
        Optional<User> existingUserMobile = userRepo.findTop1ByMobileNo(signUpDto.getMobileNo());
        if (existingUserMobile.isPresent()) {
            throw new TodayTaskException("Mobile is already registered, please sign in", ErrorCode.USER_IS_ALREADY_PRESENT);

        }

        User newUser = new User();
        newUser.setName(signUpDto.getName());
        newUser.setEmail(signUpDto.getEmail());
        newUser.setCreatedDate(new Date());
        newUser.setAddress(signUpDto.getAddress());
        newUser.setMobileNo(signUpDto.getMobileNo());
        newUser.setGender(signUpDto.getGender());
        newUser.setActive(true);
        if (signUpDto.getRole() == null) {
            newUser.setRole("User");
        } else {
            newUser.setRole(signUpDto.getRole());
        }
        // first we encode the password and save it to the DB

        newUser.setPassword(cryptoService.encryptData(signUpDto.getPassword()));

        User createdUser = userRepo.save(newUser);
        signUpResponseDto.setUser(createdUser);
        signUpResponseDto.setSignUpStatus("USER_CREATED_SUCCESSFULLY");
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);


    }

    public ResponseEntity<LoginResponseDto> loginExistingUser(LoginDto loginDto) throws Exception {


        String email = loginDto.getEmail();

        Optional<User> user = userRepo.findTop1ByEmail(email);
        if (!user.isPresent()) {
            throw new TodayTaskException("No such email found, please register", ErrorCode.USER_IS_NOT_PRESENT);
        } else {
            try {
                String encryptPassword = user.get().getPassword();
                String originalPassword = cryptoService.decryptData(encryptPassword);
                if (loginDto.getPassword().equals(originalPassword)) {
                    LoginResponseDto loginResponseDto = new LoginResponseDto();

                    //Generate jwt token for this user
                    final String token = jwtUtil.generateToken(user.get().getEmail());
                    loginResponseDto.setJwtToken(token);
                    loginResponseDto.setUserName(user.get().getName());
                    loginResponseDto.setRole(user.get().getRole());
                    loginResponseDto.setEmail(loginDto.getEmail());
                    loginResponseDto.setUserId(user.get().getId());
                    loginResponseDto.setLoginStatus("LOGIN_SUCCESSFUL");
                    return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);

                } else {
                    throw new TodayTaskException("incorrect password", ErrorCode.INCORRECT_PASSWORD);
                }
            } catch (TodayTaskException ex) {
                // Handle the custom exception, log the error, or perform other actions
                log.error("Parking Lot Exception: " + ex.getErrorCode() + " - " + ex.getMessage(), ex);
                throw ex;
            } catch (Exception ex) {
                log.error("Unexpected error: " + ex.getMessage(), ex);
                throw new TodayTaskException("Internal Server Error", ErrorCode.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
