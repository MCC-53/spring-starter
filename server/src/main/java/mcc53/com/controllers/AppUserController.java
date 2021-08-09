package mcc53.com.controllers;

import mcc53.com.models.ResponseMessage;
import mcc53.com.models.dto.RegisterDTO;
import mcc53.com.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AppUserController{
    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<RegisterDTO>> register(@Valid @RequestBody RegisterDTO registerDTO, Errors errors){
        ResponseMessage<RegisterDTO> responseData = new ResponseMessage<>();
        if (errors.hasErrors()){
            for (ObjectError error:errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        else {
            responseData.setStatus(true);
            responseData.setData(appUserService.registerAppUser(registerDTO));
            responseData.getMessage().add("Register Success");
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<ResponseMessage<AppUser>>login(@RequestBody LoginDTO loginDTO){
//
//    }
}
