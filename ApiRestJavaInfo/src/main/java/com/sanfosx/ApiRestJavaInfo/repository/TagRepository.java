package com.sanfosx.ApiRestJavaInfo.repository;

import com.sanfosx.ApiRestJavaInfo.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}