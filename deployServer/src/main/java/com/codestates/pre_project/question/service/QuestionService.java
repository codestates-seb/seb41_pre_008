package com.codestates.pre_project.question.service;

import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.repository.QuestionRepository;
import com.codestates.pre_project.tag.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagService tagService;

    public QuestionService(QuestionRepository questionRepository,
                           TagService tagService){
        this.questionRepository = questionRepository;
        this.tagService = tagService;
    }

    public Question createQuestion(Question question) {
        verifyQuestion(question);
        Question savedQuestion = saveQuestion(question);

        return savedQuestion;
    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        //질문 상태 변경
        Optional.ofNullable(question.getQuestionStatus())
                .ifPresent(questionStatus -> findQuestion.setQuestionStatus(questionStatus));

        //질문 제목 변경
        Optional.ofNullable(question.getTitle())
                .ifPresent(questionTitle -> findQuestion.setTitle(questionTitle));

        //질문 problemContent 변경
        Optional.ofNullable(question.getProblemContent())
                .ifPresent(questionProblemContent -> findQuestion.setProblemContent(questionProblemContent));

        //질문 expectContent 변경
        Optional.ofNullable(question.getExpectContent())
                .ifPresent(questionExpectContent -> findQuestion.setExpectContent(questionExpectContent));

        Optional.ofNullable(question.getQuestionTags())
                .ifPresent(questionTags -> findQuestion.setQuestionTags(questionTags));


        findQuestion.setModifiedAt(LocalDateTime.now()); // 글 수정하면 수정 시간 변경

        return saveQuestion(findQuestion);
    }

    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }

    /*
    public Page<Question> findQuestions(int page, int size) {

        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }

     */
    public List<Question> findQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);

        questionRepository.delete(question);
    }

    public void deleteQuestions (){
        questionRepository.deleteAll();
    }
    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    private void verifyQuestion(Question question) {
        //memberService 작성되면 주석 풀기
        // 회원이 존재하는지 확인
        //memberService.findVerifiedMember(question.getMember().getMemberId());

        //tag가 존재하는지 확인
        question.getQuestionTags().stream()
                .forEach(questionTag -> tagService.findVerifiedTag(questionTag.getTag().getTagId()));
    }

    private Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
}
