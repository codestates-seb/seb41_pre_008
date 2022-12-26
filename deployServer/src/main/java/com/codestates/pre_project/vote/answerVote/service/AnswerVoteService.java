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

/* AnswerVote service
* 처음에 만들때 createAnswerVote 한번 하고
* 그 다음부터 update로 조정한다.
* */
//@Service
//@RequiredArgsConstructor
//public class AnswerVoteService {
//
//    private final AnswerVoteRepository avRepository;
//    private final MemberRepository memberRepository;
//
//    public AnswerVote createAnswerVote(AnswerVote av){
//        // 만들 때는 멤버를 확인하지 않는다
//        return avRepository.save(av);
//    }
//    public AnswerVote updateAnswerVote(AnswerVote av) {
//        // 업데이트 할때 이미 투표한 멤버인지 확인한다.
//    }
//    public AnswerVote findAnswerVote(long avId){}
//    public Page<AnswerVote> findAnswerVotes(int page, int size){}
//    public void deleteAnswerVote(long avId) {}
//    private AnswerVote findVerifiedAV(long avId){}
//
//    // 이미 체크한 회원인지 확인 true -> 체크한 회원 false-> 이번에 처음 체크한 회원
//    private boolean verifyAlreadyCheckedMember(AnswerVote av){
//         Optional<Member> optionalMember = memberRepository.findById(av.getMember().getMemberId());
//         if(optionalMember.isPresent()) return true;
//         else return false;
//    }
//}
