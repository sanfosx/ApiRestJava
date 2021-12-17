package com.sanfosx.ApiRestJavaInfo.service;

import com.sanfosx.ApiRestJavaInfo.DTO.VoteDTO;
import com.sanfosx.ApiRestJavaInfo.Entity.Emprendiment;
import com.sanfosx.ApiRestJavaInfo.Entity.Vote;
import com.sanfosx.ApiRestJavaInfo.repository.EmprendimentRepository;
import org.springframework.core.convert.converter.Converter;
import com.sanfosx.ApiRestJavaInfo.repository.VoteRepository;
import org.springframework.stereotype.Service;


@Service
public class VoteService {

    private final Converter<VoteDTO, Vote> voteDTOVoteConverter;
    private final VoteRepository voteRepository;
    private final EmprendimentRepository emprendimentRepository;

    public VoteService(Converter<VoteDTO, Vote> voteDTOVoteConverter,
                       VoteRepository voteRepository,
                       EmprendimentRepository emprendimentRepository) {
        this.voteDTOVoteConverter = voteDTOVoteConverter;
        this.voteRepository = voteRepository;
        this.emprendimentRepository = emprendimentRepository;
    }

    public boolean createVote(VoteDTO voteDTO) {
        Vote vote = voteDTOVoteConverter.convert(voteDTO);
        if(!checkVote(voteDTO)){
            assert vote != null;
            Emprendiment emprendiment = emprendimentRepository.getById(vote.getEmprendimentId());
            emprendiment.setVotesCount(emprendiment.getVotesCount()+1);
            emprendimentRepository.save(emprendiment);
            voteRepository.save(vote);
            return true;
        }
        return false;
    }

    public boolean checkVote(VoteDTO voteDTO){
        Vote vote = voteDTOVoteConverter.convert(voteDTO);
        return voteRepository.findAll().stream()
                .anyMatch(v -> {
                    assert vote != null;
                    return v.getUserId().equals(vote.getUserId()) && v.getEmprendimentId().equals(vote.getEmprendimentId());
                });
    }
}
