package com.codestates.pre_project.vote.questionVote.service;

import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.vote.questionVote.entity.QuestionVote;
import com.codestates.pre_project.vote.questionVote.repository.QuestionVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {
    private final QuestionVoteRepository qvRepository;

    public QuestionVote createQuestionVote(QuestionVote qv){
        // 만들 때는 멤버를 확인하지 않는다
        return qvRepository.save(qv);
    }

    // 회원마다 1번의 기회를 주는 up&down
    public int updateQuestionVote(QuestionVote qv) {
        // 업데이트 할때 이미 투표한 멤버인지 확인한다.

        // 받은 QuestionVoteId로 QuestionVote를 찾고
        QuestionVote findQuestionVote = findVerifiedAV(qv.getVoteId());

        // 찾은 questionVote의 memberId를 입력받은 questionvote의 memberId와 비교
        boolean isIdenticalMember = findQuestionVote.getMember().getMemberId().equals(qv.getMember().getMemberId());

/*        // questionVote를 하지 않은 멤버인데 up&down을 시도할 경우
        long num = avRepository.findAll().stream().filter(avote->
            avote.getMember().getMemberId().equals(findQuestionVote.getMember().getMemberId())).count();
        boolean isAlreadyVotedMember = (int) num >= 1;

        List<QuestionVote> questionVoteList = qvRepository.findAll();
        System.out.println("findQuestionVote.getMember().getMemberId(): "+ findQuestionVote.getMember().getMemberId());
        System.out.println("av.getMember().getMemberId(): "+ qv.getMember().getMemberId());*/

        //이미 투표한 멤버라면
        if(isIdenticalMember) {

            // 1. 처음에 up을 누른 경우
            if(findQuestionVote.getStatus()==1){
                // 1-1) 처음에 up 지금도 up => 변화없음
                if(qv.getStatus()==1) {
                    System.out.println("1-1번에 걸렸다");
                    // do nothing
                }
                // 1-2) 처음에 up 지금 down => status -1
                else {
                    System.out.println("1-2번에 걸렸다");
                    findQuestionVote.setStatus(findQuestionVote.getStatus()-1);
                    qvRepository.save(findQuestionVote);
                }
            }

            // 2. 처음에 down을 누른 경우
            else {
                // 2-1) 처음에 down 지금 up => status +1
                if(qv.getStatus() == 1) {
                    System.out.println("2-1번에 걸렸다");
                    findQuestionVote.setStatus(findQuestionVote.getStatus()+1);
                    qvRepository.save(findQuestionVote);
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
            if(qv.getStatus()==1) {
                System.out.println("3번에 걸렸다");
                // status 1
                QuestionVote beSaved = QuestionVote.builder().voteId(0L).question(findQuestionVote.getQuestion())
                        .member(findQuestionVote.getMember()).status(1).build();
                qvRepository.save(beSaved);
            }
            // 4. down을 눌렀다면
            else{
                System.out.println("4번에 걸렸다");
                QuestionVote beSaved = QuestionVote.builder().voteId(0L).question(findQuestionVote.getQuestion())
                        .member(findQuestionVote.getMember()).status(-1).build();
                qvRepository.save(beSaved);
            }
        }
        // status의 합 리턴
        return qvRepository.sumByStatus();
    }

    public QuestionVote findQuestionVote(long qvId){
        return findVerifiedAV(qvId);
    }
    /*public Page<QuestionVote> findQuestionVotes(int page, int size){}*/
    public void deleteQuestionVote(long qvId) {
        qvRepository.delete(findVerifiedAV(qvId));
    }
    private QuestionVote findVerifiedAV(long qvId){
        return qvRepository.findById(qvId).orElseThrow(
                ()-> new BusinessLogicException(ExceptionCode.ANSWERCOMMENT_NOT_FOUND));
    }

    // 이미 체크한 회원인지 확인 true -> 체크한 회원 false-> 이번에 처음 체크한 회원
/*    private boolean isCheckedMember(QuestionVote av){
         Optional<Member> optionalMember = memberRepository.findById(av.getMember().getMemberId());
        return optionalMember.isPresent();
    }*/
}

