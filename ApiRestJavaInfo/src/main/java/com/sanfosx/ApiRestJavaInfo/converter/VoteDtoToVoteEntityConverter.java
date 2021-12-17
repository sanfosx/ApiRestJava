package com.sanfosx.ApiRestJavaInfo.converter;

import com.sanfosx.ApiRestJavaInfo.DTO.VoteDTO;
import com.sanfosx.ApiRestJavaInfo.Entity.Vote;
import com.sanfosx.ApiRestJavaInfo.repository.EmprendimentRepository;
import com.sanfosx.ApiRestJavaInfo.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class VoteDtoToVoteEntityConverter implements Converter<VoteDTO, Vote> {

    public VoteDtoToVoteEntityConverter(EmprendimentRepository emprendimentRepository,
                                        UserRepository userRepository) {
    }

    @Override
    public Vote convert(VoteDTO voteDTO){
        Vote vote = new Vote();
        vote.setUserId(voteDTO.getUserId());
        vote.setEmprendimentId(voteDTO.getEmprendimentId());
        vote.setDate(LocalDateTime.now());
        vote.setGeneratedFrom(voteDTO.getGeneratedFrom());
        return vote;
    }
}