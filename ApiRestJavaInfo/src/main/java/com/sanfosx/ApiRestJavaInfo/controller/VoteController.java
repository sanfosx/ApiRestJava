package com.sanfosx.ApiRestJavaInfo.controller;

import com.sanfosx.ApiRestJavaInfo.DTO.VoteDTO;
import com.sanfosx.ApiRestJavaInfo.repository.VoteRepository;
import com.sanfosx.ApiRestJavaInfo.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    private final VoteService voteService;
    private final VoteRepository voteRepository;

    public VoteController(VoteService voteService, VoteRepository voteRepository) {
        this.voteService = voteService;
        this.voteRepository = voteRepository;
    }
    //nuevo voto
    @PostMapping
    @RequestMapping
    public ResponseEntity<?> vote(@Valid @RequestBody VoteDTO voteDTO) {
        return new ResponseEntity<>(voteService.createVote(voteDTO), HttpStatus.CREATED);
    }
    //obtener los votos de un usuario
    @GetMapping
    @RequestMapping("/{userId}")
    public ResponseEntity<?> getVotesFromUser(
            @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(voteRepository.findByUserId(userId), HttpStatus.OK);
    }

}
