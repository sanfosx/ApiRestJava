package com.sanfosx.ApiRestJavaInfo.repository;


import com.sanfosx.ApiRestJavaInfo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {


    List<User> findByCreatedDateAfter(LocalDateTime dateAfter);
    List<User> findByCity(String city);

}