package com.example.jwtAuthentication.Controller;


import com.example.jwtAuthentication.Dto.RequsetDto.*;
import com.example.jwtAuthentication.Dto.ResponseDto.AuthResponseDto;
import com.example.jwtAuthentication.Enum.AuthStatus;
import com.example.jwtAuthentication.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // Use * for all origins
public class authController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto){

    var jwtToken = authService.login(authRequestDto.getEmail(),authRequestDto.getPassword());

    var Result = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS,authRequestDto.getEmail());

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(Result);

    }


    @PostMapping("/sign-up")
    public ResponseEntity Signup(@RequestBody UserRequestDto userRequestDto) throws Exception{

        try {
            var jwtToken =authService.signup(userRequestDto);

            var Result = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY, userRequestDto.getEmail());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Result);
        }
        catch (Exception e) {

            var Result = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED,null);

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Result);

        }
    }

    @GetMapping("/getUser/{email}")
    public ResponseEntity getUser(@PathVariable String email)throws Exception{
        try{
            return new ResponseEntity(authService.getUser(email), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/userNameUpdate")
    public ResponseEntity userNameUpdate(@RequestBody UserDetailUpdate userDetailUpdate)throws Exception{
        try{
            return new ResponseEntity(authService.userNameUpdate(userDetailUpdate), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping("/userConnectionUpdate")
    public ResponseEntity userConnectionUpdate(@RequestBody UserConnectionUpdateDTO userConnectionUpdateDTO)throws Exception{
        try{
            return new ResponseEntity(authService.userConnectionUpdate(userConnectionUpdateDTO), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}
