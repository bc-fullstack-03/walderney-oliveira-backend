package com.example.sysmap.parrot.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sysmap.parrot.Application.Interfaces.IUserService;
import com.example.sysmap.parrot.Application.Request.UserReponse;
import com.example.sysmap.parrot.Application.Request.UserRequest;

@RestController
@RequestMapping("/api/vi/user")
public class UserController {
    
    @Autowired
    private IUserService userService;


    @PostMapping()
    private ResponseEntity<String> CreateUser(@RequestBody UserRequest request){
        
        var response = userService.createUser(request);

        if(response!=null){
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping()
    private ResponseEntity<String> Delete(@RequestParam String id){

        var reponse = userService.deleteById(id);

        if(reponse!=null){
            return ResponseEntity.ok(reponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    private ResponseEntity<UserReponse>findByemail(@RequestParam String email){
        var reponse = userService.findByEmail(email);
        
        if(reponse!=null){
            return ResponseEntity.ok(reponse);
        }
        return ResponseEntity.badRequest().build();

    }
    
    
    @GetMapping("/all")
    private ResponseEntity<List<UserReponse>> findByAll(){

        var reponse= userService.findAll();
        if(reponse!=null){
            return ResponseEntity.ok(reponse);
        }
        return ResponseEntity.badRequest().build();

    }
   
    @PutMapping()
    private ResponseEntity<UserReponse> updateUsers(@RequestParam String id, @RequestBody UserRequest request){
        
        var reponse = userService.updateUser(id, request);
        
        if(reponse!=null){
            return ResponseEntity.ok(reponse);
        }
        return ResponseEntity.badRequest().build();

    }

    
}