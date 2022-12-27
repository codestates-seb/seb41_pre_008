package com.codestates.pre_project.vote.answerVote.Controller;

import com.codestates.pre_project.vote.answerVote.dto.AnswerVotePatchDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVotePostDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVoteResponseDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVoteUpDownResponseDto;
import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import com.codestates.pre_project.vote.answerVote.mapper.AnswerVoteMapper;
import com.codestates.pre_project.vote.answerVote.service.AnswerVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answervotes")
@Validated
public class AnswerVoteController {

    private final AnswerVoteService avService;
    private final AnswerVoteMapper mapper;

    @PostMapping
    public ResponseEntity postAnswerVote(@RequestBody AnswerVotePostDto postDto){
        AnswerVote answerVote = avService.createAnswerVote(mapper.postToAnswerVote(postDto));
        answerVote.setStatus(0);
        return new ResponseEntity<>(mapper.answerToResponseDto(answerVote), HttpStatus.CREATED);
    }

    @PatchMapping("/{av-id}")
    public ResponseEntity patchAnswerVote(@PathVariable("av-id") @Positive long avId,
                                          @RequestBody AnswerVotePatchDto patchDto) {
        patchDto.setVoteId(avId);
        int sumOfStatus = avService.updateAnswerVote(mapper.patchToAnswerVote(patchDto));
        AnswerVoteUpDownResponseDto response = new AnswerVoteUpDownResponseDto(sumOfStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{av-id}")
    public ResponseEntity getAnswerVote(@PathVariable("av-id") @Positive long avId){
        return new ResponseEntity(mapper.answerToResponseDto(avService.findAnswerVote(avId)),
                HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getAnswerVotes(int page, int size){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{av-id}")
    public ResponseEntity deleteAnswerVote(@PathVariable("av-id") @Positive long avId){
        avService.deleteAnswerVote(avId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
