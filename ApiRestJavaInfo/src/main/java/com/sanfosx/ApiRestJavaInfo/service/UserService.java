package com.sanfosx.ApiRestJavaInfo.service;

import com.sanfosx.ApiRestJavaInfo.Entity.User;
import com.sanfosx.ApiRestJavaInfo.repository.UserRepository;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(Long idUser, User user) {
        User userDB = userRepository.getById(idUser);
        if(user.getFirstname() != null){
            userDB.setFirstname(user.getFirstname());
        }
        if(user.getLastname() != null){
            userDB.setLastname(user.getLastname());
        }
        if(user.getCity() != null) {
            userDB.setCity(user.getCity());
        }
        if(user.getProvince() != null) {
            userDB.setProvince(user.getProvince());
        }
        if(user.getCountry() != null) {
            userDB.setCity(user.getCountry());
        }
        if(user.getTypeUser() != null) {
            userDB.setTypeUser(user.getTypeUser());
        }
        if(user.getPassword() != null) {
            userDB.setPassword(user.getPassword());
        }
        user.setLasUpdateDate(now());

        return userRepository.save(userDB);
    }

    public void removeUser(Long id, User user) {
        User inDB = userRepository.getById(id);
        userRepository.delete(inDB);
    }

    public User saveUser(User user)  {
        user.setPassword(user.getPassword());
        return this.userRepository.save(user);
    }
}