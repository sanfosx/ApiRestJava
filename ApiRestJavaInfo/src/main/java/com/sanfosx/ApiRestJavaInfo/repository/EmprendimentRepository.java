package com.sanfosx.ApiRestJavaInfo.repository;

import com.sanfosx.ApiRestJavaInfo.Entity.Emprendiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmprendimentRepository extends JpaRepository<Emprendiment, Long> {

    List<Emprendiment> findByUserId(Long id);
    @Query("SELECT e FROM Emprendiment e join fetch e.tags t WHERE t.name LIKE %:tag%")
    List<Emprendiment> findByTag(@Param("tag") String tag);
    List<Emprendiment> findByPublished(boolean published);
}