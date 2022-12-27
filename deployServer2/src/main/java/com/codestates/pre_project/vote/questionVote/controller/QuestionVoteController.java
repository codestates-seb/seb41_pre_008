package com.codestates.pre_project.vote.questionVote.controller;

import com.codestates.pre_project.vote.questionVote.dto.QuestionVotePatchDto;
import com.codestates.pre_project.vote.questionVote.dto.QuestionVotePostDto;
import com.codestates.pre_project.vote.questionVote.dto.QuestionVoteUpDownResponseDto;
import com.codestates.pre_project.vote.questionVote.entity.QuestionVote;
import com.codestates.pre_project.vote.questionVote.mapper.QuestionVoteMapper;
import com.codestates.pre_project.vote.questionVote.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questionvotes")
@Validated
public class QuestionVoteController {

    private final QuestionVoteService qvService;
    private final QuestionVoteMapper mapper;

    @PostMapping
    public ResponseEntity postQuestionVote(@RequestBody QuestionVotePostDto postDto){
        QuestionVote questionVote = qvService.createQuestionVote(mapper.postToQuestionVote(postDto));
        questionVote.setStatus(0);
        return new ResponseEntity<>(mapper.questionVoteToResponseDto(questionVote), HttpStatus.CREATED);
    }

    @PatchMapping("/{qv-id}")
    public ResponseEntity patchQuestionVote(@PathVariable("qv-id") @Positive long qvId,
                                          @RequestBody QuestionVotePatchDto patchDto) {
        patchDto.setVoteId(qvId);
        int sumOfStatus = qvService.updateQuestionVote(mapper.patchToQuestionVote(patchDto));
        QuestionVoteUpDownResponseDto response = new QuestionVoteUpDownResponseDto(sumOfStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{qv-id}")
    public ResponseEntity getQuestionVote(@PathVariable("qv-id") @Positive long qvId){
        return new ResponseEntity(mapper.questionVoteToResponseDto(qvService.findQuestionVote(qvId)),
                HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getQuestionVotes(int page, int size){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{qv-id}")
    public ResponseEntity deleteQuestionVote(@PathVariable("av-id") @Positive long qvId){
        qvService.deleteQuestionVote(qvId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
