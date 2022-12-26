package com.codestates.pre_project.vote.answerVote.service;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.vote.answerVote.dto.AnswerVoteResponseDto;
import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import com.codestates.pre_project.vote.answerVote.repository.AnswerVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerVoteRepository avRepository;
    private final MemberRepository memberRepository;

    public AnswerVote createAnswerVote(AnswerVote av){}
    public AnswerVote updateAnswerVote(AnswerVote av) {}
    public AnswerVote findAnswerVote(long avId){}
    public Page<AnswerVote> findAnswerVotes(int page, int size){}
    public void deleteAnswerVote(long avId) {}
    private AnswerVote findVerifiedAV(long avId){}

    // 이미 체크한 회원인지 확인 true -> 체크한 회원 false->
    private boolean verifyAlreadyCheckedMember(AnswerVote av){
         Optional<Member> optionalMember = memberRepository.findById(av.getMember().getMemberId());
         if(optionalMember.isPresent()) return true;
    }
}
