package com.codestates.pre_project.vote.answerVote.mapper;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVotePatchDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVotePostDto;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVoteResponseDto;
import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    default AnswerVote postToAnswerVote(AnswerVotePostDto post){
        AnswerVote av = new AnswerVote();
        Member member = new Member();
        member.setMemberId(post.getMemberId());
        Answer answer = new Answer();
        answer.setAnswerId(post.getAnswerId());
        av.setAnswer(answer);
        av.setMember(member);
        return av;
    };
    default AnswerVote patchToAnswerVote(AnswerVotePatchDto patch){
        AnswerVote av = new AnswerVote();
        Member member = new Member();
        member.setMemberId(patch.getMemberId());
        av.setMember(member);
        av.setVoteId(patch.getVoteId());
        av.setStatus(patch.getStatus());
        return av;
    };
    @Mapping(target = "memberId", source = "member.memberId")
    AnswerVoteResponseDto answerToResponseDto(AnswerVote answerVote);
}
