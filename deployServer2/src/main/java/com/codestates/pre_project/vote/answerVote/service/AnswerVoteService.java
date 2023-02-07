package com.codestates.pre_project.vote.answerVote.service;

import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.vote.answerVote.entity.AnswerVote;
import com.codestates.pre_project.vote.answerVote.repository.AnswerVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/* AnswerVote service
* 처음에 만들때 createAnswerVote 한번 하고
* 그 다음부터 update로 조정한다.
* */
@Service
@RequiredArgsConstructor
public class AnswerVoteService {

    private final AnswerVoteRepository avRepository;
//    private final MemberRepository memberRepository;

    public AnswerVote createAnswerVote(AnswerVote av){
        // 만들 때는 멤버를 확인하지 않는다
        return avRepository.save(av);
    }
/*    // 회원에 상관없이 누르면 무한으로 up&down
    public int updateAnswerVote(AnswerVote av){

    }*/

    // 회원마다 1번의 기회를 주는 up&down
    public int updateAnswerVote(AnswerVote av) {
        // 업데이트 할때 이미 투표한 멤버인지 확인한다.
        boolean isAlreadyVoted = false;
        List<AnswerVote> avs = avRepository.findAllByAnswerId(av.getAnswer().getAnswerId());
        for(AnswerVote answerVote :avs) {
            if(Objects.equals(answerVote.getMember().getMemberId(), av.getMember().getMemberId()))
                isAlreadyVoted = true;
        }

        // answerId와 memberId로 AnswerVote를 찾는다.
        AnswerVote findAnswerVote = avRepository.findByMemberIdAndAnswerId(av.getAnswer().getAnswerId(), av.getMember().getMemberId())
                        .orElseThrow(()-> new BusinessLogicException(ExceptionCode.ANSWERVOTE_NOT_FOUND));

        System.out.println("findAnswerVote.getMember().getMemberId(): "+ findAnswerVote.getMember().getMemberId());
        System.out.println("av.getMember().getMemberId(): "+ av.getMember().getMemberId());

        //이미 투표한 멤버라면
        if(isAlreadyVoted) {

            // 1. 처음에 up을 누른 경우
            if(findAnswerVote.getStatus()==1){
                // 1-1) 처음에 up 지금도 up => 변화없음
                if(av.getStatus()==1) {
                    System.out.println("1-1번에 걸렸다");
                    // do nothing
                }
                // 1-2) 처음에 up 지금 down => status -1
                else {
                    System.out.println("1-2번에 걸렸다");
                    findAnswerVote.setStatus(findAnswerVote.getStatus()-1);
                    avRepository.save(findAnswerVote);
                }
            }

            // 2. 처음에 down을 누른 경우
            else {
                // 2-1) 처음에 down 지금 up => status +1
                if(av.getStatus() == 1) {
                    System.out.println("2-1번에 걸렸다");
                    findAnswerVote.setStatus(findAnswerVote.getStatus()+1);
                    avRepository.save(findAnswerVote);
                }
                // 2-2) 처음에 down 지금 down => 변화없음
                else {
                    System.out.println("2-2번에 걸렸다");
                    //do nothing
                }
            }
        }

        // 처음 투표하는 멤버라면
        else{
            // 3. up을 눌렀다면
            if(av.getStatus()==1) {
                System.out.println("3번에 걸렸다");
                // status 1
                AnswerVote beSaved = AnswerVote.builder().voteId(0L).answer(findAnswerVote.getAnswer())
                        .member(findAnswerVote.getMember()).status(1).build();
                avRepository.save(beSaved);
            }
            // 4. down을 눌렀다면
            else{
                System.out.println("4번에 걸렸다");
                AnswerVote beSaved = AnswerVote.builder().voteId(0L).answer(findAnswerVote.getAnswer())
                        .member(findAnswerVote.getMember()).status(-1).build();
                avRepository.save(beSaved);
            }
        }
        // status의 합 리턴
        return avRepository.sumByStatus(av.getMember().getMemberId());
    }

    public AnswerVote findAnswerVote(long avId){
        return findVerifiedAV(avId);
    }
    /*public Page<AnswerVote> findAnswerVotes(int page, int size){}*/
    public void deleteAnswerVote(long avId) {
        avRepository.delete(findVerifiedAV(avId));
    }
    private AnswerVote findVerifiedAV(long avId){
        return avRepository.findById(avId).orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.ANSWERVOTE_NOT_FOUND));
    }

    // 이미 체크한 회원인지 확인 true -> 체크한 회원 false-> 이번에 처음 체크한 회원
/*    private boolean isCheckedMember(AnswerVote av){
         Optional<Member> optionalMember = memberRepository.findById(av.getMember().getMemberId());
        return optionalMember.isPresent();
    }*/
}
