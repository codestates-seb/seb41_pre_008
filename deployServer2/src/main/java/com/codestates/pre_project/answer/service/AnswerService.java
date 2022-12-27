package com.codestates.pre_project.answer.service;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.answer.repository.AnswerRepository;
import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;

    public Answer createAnswer(Answer answer){
        verifyAnswer(answer);
        // answer는 중복체크할 필요가 없으니 그냥 repository에 저장
        return answerRepository.save(answer);
    }


    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        // answer는 answerContent만 고칠 수 있다.
        Optional.ofNullable(answer.getAnswerContent())
                .ifPresent(findAnswer::setAnswerContent);

        findAnswer.setModifiedAt(LocalDateTime.now());
        return answerRepository.save(findAnswer);
    }


    public Answer findAnswer(long answerId){
        return findVerifiedAnswer(answerId);
    }

    public Page<Answer> findAnswers(int page, int size){
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }

    public void deleteAnswer(long answerId){
        answerRepository.delete(findVerifiedAnswer(answerId));
    }
    private void verifyAnswer(Answer answer) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(answer.getMember().getMemberId());
    }

    public Answer findVerifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        return optionalAnswer.orElseThrow(()-> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    private void verifyExistsAnswer(Answer answer){
        Optional<Answer> optionalAnswer = answerRepository.findById(answer.getAnswerId());
        if(optionalAnswer.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND);
        }
    }

}
