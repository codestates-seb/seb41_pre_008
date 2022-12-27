package com.codestates.pre_project.vote.questionVote.mapper;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.vote.questionVote.dto.QuestionVoteResponseDto;
import com.codestates.pre_project.vote.questionVote.entity.QuestionVote;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-28T01:52:30+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16 (Azul Systems, Inc.)"
)
@Component
public class QuestionVoteMapperImpl implements QuestionVoteMapper {

    @Override
    public QuestionVoteResponseDto questionVoteToResponseDto(QuestionVote questionVote) {
        if ( questionVote == null ) {
            return null;
        }

        long memberId = 0L;
        long voteId = 0L;
        int status = 0;

        Long memberId1 = questionVoteMemberMemberId( questionVote );
        if ( memberId1 != null ) {
            memberId = memberId1;
        }
        if ( questionVote.getVoteId() != null ) {
            voteId = questionVote.getVoteId();
        }
        status = questionVote.getStatus();

        QuestionVoteResponseDto questionVoteResponseDto = new QuestionVoteResponseDto( voteId, memberId, status );

        return questionVoteResponseDto;
    }

    private Long questionVoteMemberMemberId(QuestionVote questionVote) {
        if ( questionVote == null ) {
            return null;
        }
        Member member = questionVote.getMember();
        if ( member == null ) {
            return null;
        }
        Long memberId = member.getMemberId();
        if ( memberId == null ) {
            return null;
        }
        return memberId;
    }
}
