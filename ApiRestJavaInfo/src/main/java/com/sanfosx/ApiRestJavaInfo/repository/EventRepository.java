package com.sanfosx.ApiRestJavaInfo.repository;

import com.sanfosx.ApiRestJavaInfo.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
