package com.example.sysmap.parrot.Application.User.Services;

import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.sysmap.parrot.Application.Exception.DatabaseException;
import com.example.sysmap.parrot.Application.Exception.Exceptions;
import com.example.sysmap.parrot.Application.User.Dto.UserRequestReponse.UserReponse;
import com.example.sysmap.parrot.Application.User.Dto.UserRequestReponse.UserRequest;
import com.example.sysmap.parrot.Config.Aws.IAwsFileService;
import com.example.sysmap.parrot.Damon.Entities.User;
import com.example.sysmap.parrot.Infrastructure.IUserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAwsFileService awsFileService;
    
    @Override
    public String createUser(UserRequest request) throws IllegalArgumentException, Exceptions, DatabaseException {
        if (request.name.isEmpty() || request.email.isEmpty()) {
            throw new IllegalArgumentException("Nome e email estão vazios");
        }
        if (userRepository.findByEmail(request.email).isPresent()) {
            throw new Exceptions("User com email " + request.email + " ja existe");
        }
        
        try {
            var hash = passwordEncoder.encode(request.password);
            
           
            var user = new User(request.name, request.email, request.username);
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
                u.getImageUrl(),
                u.getUsername()
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
        var hash = passwordEncoder.encode(request.password);

        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(hash);
        user.setUsername(request.username);
        user.setImageUrl(request.imageUrl);
        user.setUsername(request.username);

        userRepository.save(user);
        
        var response = new UserReponse(user.getId(), 
        user.getName(), 
        user.getEmail(),
        user.getPassword(),
        user.getImageUrl(),
        user.getUsername()
        );

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
    @Override
    public void uploadPhoto(MultipartFile photo) throws Exceptions {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        var photoUri = "";

        try {
            var fileName = user.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
            photoUri = awsFileService.upload(photo, fileName);
        } catch (Exceptions e) {
            throw new Exceptions(e.getMessage());
        }

        user.setImageUrl(photoUri);
        userRepository.save(user);
    }

    @Override
    public String follow(String id) {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        
        if (user.getId().equals(id)) {
            throw new Exceptions("Não é possível seguir seu próprio perfil");
        }else{
            user.followUser(id);
            userRepository.save(user);
            return "Seguindo o amigo " + id.toString();
        }   
        

    }

}

