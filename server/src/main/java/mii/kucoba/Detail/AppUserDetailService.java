/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.Detail;

import mii.kucoba.models.Privilege;
import mii.kucoba.models.User;
import mii.kucoba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author abiyo
 */
@Service
public class AppUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Username tidak di temukan");
        }
        return new AppUserDetail(user); //To change body of generated methods, choose Tools | Templates.
    }
//    
//    public User getIdUserEmployeeById(Integer id){
//        return userRepository.findById(id)
//            .orElseThrow( () 
//                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Role Tidak Ditemukan")
//            );
//    }
//    
//    public String createAkunEmployee(User p){
//        p.setPassword(passwordEncoder.encode(p.getPassword()));
////        userRepository.save(p);
//        userRepository.createAkun(7, "user", "user");
//        return "Register success";
//    }
//    
//    public User update(Integer id, User p) {
//        getIdUserEmployeeById(id);
//        p.setId(id);
//        return userRepository.save(p);
//    }
    
}
