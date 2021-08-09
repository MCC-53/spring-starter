/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.details;

import com.MCC53.springsecurity.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/register")
public class AppUserController {
    
    @Autowired
    AppUserDetailsService appUserDetailsService;
    
    
//    public String UserRegister (AppUserDetailsService appUserDetailsService){    
//    }
//  
//    public ResponseEntity <ResponseMessage<AppUserDetails>> register (
//            @RequestBody AppUserDetails appUserDetails) {
//        
////        return new ResponseEntity(new ResponseMessage<AppUserDetails>
//                (appUserDetailsService.(employee), "employee created"), HttpStatus.OK);
//    }
}
