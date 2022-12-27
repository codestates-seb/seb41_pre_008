package com.codestates.pre_project.vote.questionVote.mapper;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.vote.questionVote.dto.QuestionVotePatchDto;
import com.codestates.pre_project.vote.questionVote.dto.QuestionVotePostDto;
import com.codestates.pre_project.vote.questionVote.dto.QuestionVoteResponseDto;
import com.codestates.pre_project.vote.questionVote.entity.QuestionVote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionVoteMapper {
    default QuestionVote postToQuestionVote(QuestionVotePostDto post){
        QuestionVote qv = new QuestionVote();
        Question question = new Question();
        question.setQuestionId(post.getQuestionId());
        Member member = new Member();
        member.setMemberId(post.getMemberId());
        qv.setQuestion(question);
        qv.setMember(member);
        return qv;
    };
    default QuestionVote patchToQuestionVote(QuestionVotePatchDto patch) {
        QuestionVote qv = new QuestionVote();
        Member member = new Member();
        member.setMemberId(patch.getMemberId());
        qv.setMember(member);
        qv.setVoteId(patch.getVoteId());
        qv.setStatus(patch.getStatus());
        return qv;
    };

    @Mapping(target="memberId", source = "member.memberId")
    QuestionVoteResponseDto questionVoteToResponseDto(QuestionVote questionVote);
}
