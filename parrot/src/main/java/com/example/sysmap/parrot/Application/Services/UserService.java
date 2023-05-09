package com.example.sysmap.parrot.Application.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sysmap.parrot.Application.Dto.UserRequestReponse.UserReponse;
import com.example.sysmap.parrot.Application.Dto.UserRequestReponse.UserRequest;
import com.example.sysmap.parrot.Application.Exception.DatabaseException;
import com.example.sysmap.parrot.Application.Exception.UserAlreadyExistsException;
import com.example.sysmap.parrot.Application.Interfaces.IUserService;
import com.example.sysmap.parrot.Damon.Entities.Profile;
import com.example.sysmap.parrot.Damon.Entities.User;
import com.example.sysmap.parrot.Infrastructure.IUserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public String createUser(UserRequest request) throws IllegalArgumentException, UserAlreadyExistsException, DatabaseException {
        if (request.name.isEmpty() || request.email.isEmpty()) {
            throw new IllegalArgumentException("Nome e email estão vazios");
        }
        if (userRepository.findByEmail(request.email).isPresent()) {
            throw new UserAlreadyExistsException("User com email " + request.email + " ja existe");
        }
        
        try {
            var hash = passwordEncoder.encode(request.password);
            
           
            var profile = new Profile(request.profile);
            var user = new User(request.name, request.email, profile);
            user.setPassword(hash);
           

            userRepository.save(user);
        
            return user.getId().toString();
        } catch (Exception e) {
            throw new DatabaseException("Erro em salvar o usuário", e);
        }
    }
    @Override
    public UserReponse findByEmail(String email) {
        var user = userRepository.findByEmail(email);
        var response = user.map(u -> new UserReponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getPassword(),
                u.getProfile()
            )).orElse(null);
            
        return response;
    }

    @Override
    public String deleteById(String id) throws DatabaseException{
       var idAux =UUID.fromString(id);
       try{
            userRepository.deleteById(idAux);
           return "Usuario Deletado" ;
       }catch (Exception e){
            throw new DatabaseException("Erro em deletar", e);
       }
        
    }
    
    @Override
    public List<UserReponse> findAll() {
        var users = userRepository.findAll();
        var responses = new ArrayList<UserReponse>();
        for (var user : users) {
            var response = new UserReponse(user);
            responses.add(response);
        }
        return responses;
    }
   
    @Override
    public UserReponse updateUser(String id ,UserRequest request){
        UUID idAux = UUID.fromString(id);
        User user = userRepository.findById(idAux).get();
        
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setProfile(request.profile);

        userRepository.save(user);
        
        var response = new UserReponse(user.getId(), user.getName(), user.getEmail(),user.getPassword(),user.getProfile());

        return response;

    }
    @Override
    public User getUserEmail(String email) {
        var user = userRepository.getByEmail(email);
        return user;
 
    }
    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
        
}
}