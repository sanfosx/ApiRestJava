package com.sanfosx.ApiRestJavaInfo.controller;

import com.sanfosx.ApiRestJavaInfo.Entity.User;
import com.sanfosx.ApiRestJavaInfo.repository.UserRepository;
import com.sanfosx.ApiRestJavaInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    //nuevo usuario
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    //modificar usuario por Id
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id, user);
    }
    //borrar usuario
    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable Long id){
        User user = userRepository.getById(id);
        this.userService.removeUser(id, user);
    }
    //obtener usuario por Id
    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }
    //obtener todos los usuarios- filtro por fecha de creacion y filtro por ciudad
    @GetMapping
    public ResponseEntity<?> getAllUsers(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateAfter, String city) {
        if (dateAfter != null) {
            List<User> users = userRepository.findByCreatedDateAfter(dateAfter.atStartOfDay());
            return new ResponseEntity(users, HttpStatus.OK);
        }else if (city!=null){
            return new ResponseEntity<>(userRepository.findByCity(city),HttpStatus.OK);
        }
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }
}
