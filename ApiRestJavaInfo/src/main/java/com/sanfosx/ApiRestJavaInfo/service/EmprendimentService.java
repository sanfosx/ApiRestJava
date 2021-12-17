package com.sanfosx.ApiRestJavaInfo.service;

import com.sanfosx.ApiRestJavaInfo.DTO.EmprendimentDTO;
import com.sanfosx.ApiRestJavaInfo.Entity.Emprendiment;
import com.sanfosx.ApiRestJavaInfo.Entity.Tag;
import com.sanfosx.ApiRestJavaInfo.Entity.User;
import com.sanfosx.ApiRestJavaInfo.repository.EmprendimentRepository;
import com.sanfosx.ApiRestJavaInfo.repository.TagRepository;
import com.sanfosx.ApiRestJavaInfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EmprendimentService {

    private final EmprendimentRepository emprendimentRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EmprendimentService(EmprendimentRepository emprendimentRepository,
                               UserRepository userRepository,
                               TagRepository tagRepository) {
        this.emprendimentRepository = emprendimentRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public Emprendiment createEmprendiment(EmprendimentDTO emprendimentDTO) {
        User user = userRepository.findById(emprendimentDTO.getUserId()).
                orElseThrow(() -> new EntityNotFoundException("Usuario No Encontrado"));;
        List<Tag> tags = tagRepository.findAllById(emprendimentDTO.getTags());
        Emprendiment emprendiment = new Emprendiment();
        emprendiment.setName(emprendimentDTO.getName());
        emprendiment.setDescription(emprendimentDTO.getDescription());
        emprendiment.setObjective(emprendimentDTO.getObjective());
        emprendiment.setContent(emprendimentDTO.getContent());
        emprendiment.setPublished(emprendimentDTO.getPublished());
        emprendiment.setUserId(user);
        emprendiment.getTags().addAll(tags);

        return emprendimentRepository.save(emprendiment);
    }

    public Emprendiment updateEmprendiment(Long id, Emprendiment emprendiment) {
        Emprendiment inDB = emprendimentRepository.getById(id);
        if(emprendiment.getName() != null){
            inDB.setName(emprendiment.getName());
        }
        if(emprendiment.getDescription() != null){
            inDB.setDescription(emprendiment.getDescription());
        }
        if(emprendiment.getContent() != null) {
            inDB.setContent(emprendiment.getContent());
        }
        if(emprendiment.getPublished() != null) {
            inDB.setPublished(emprendiment.getPublished());
        }
        if(emprendiment.getObjective() != null) {
            inDB.setObjective(emprendiment.getObjective());
        }

        return emprendimentRepository.save(inDB);
    }

    public void  removeEmprendiment(Long id, Emprendiment emprendiment) {
        Emprendiment inDB = emprendimentRepository.getById(id);
        emprendimentRepository.delete(inDB);
    }
}
