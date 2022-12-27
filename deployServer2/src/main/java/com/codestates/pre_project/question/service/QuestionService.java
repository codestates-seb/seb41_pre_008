package com.codestates.pre_project.question.service;

import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.comment.repository.CommentRepository;
import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.entity.QuestionTag;
import com.codestates.pre_project.question.repository.QuestionRepository;
import com.codestates.pre_project.tag.repository.TagRepository;
import com.codestates.pre_project.tag.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagService tagService;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public QuestionService(QuestionRepository questionRepository,
                           TagService tagService,
                           MemberService memberService,
                           MemberRepository memberRepository,
                           CommentRepository commentRepository){
        this.questionRepository = questionRepository;
        this.tagService = tagService;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
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


    public Page<Question> findQuestions(int page, int size) {

        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }
    /*
    public List<Question> findQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

     */

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);
        String status = question.getQuestionStatus().getStatus();
        if(status.equals("채택된 답안이 있는 문의"))
            throw new BusinessLogicException(ExceptionCode.CANNOT_DELETE_QUESTION);
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_DELETE);
        questionRepository.save(question);
    }

    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        String status = optionalQuestion.get().getQuestionStatus().getStatus();
        if(status.equals("삭제된 질문"))
            throw new BusinessLogicException(ExceptionCode.ALREADY_DELETE_QUESTION);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    private void verifyQuestion(Question question) {
        // 회원이 존재하는지 확인
        memberService.findVerifiedMember(question.getMember().getMemberId());

        //tag가 존재하는지 확인
        question.getQuestionTags().stream()
                .forEach(questionTag -> tagService.findVerifiedTag(questionTag.getTag().getTagId()));
    }

    private Question saveQuestion(Question question) {
        Member member = memberRepository.findById(question.getMember().getMemberId())
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        question.setMember(member);

        return questionRepository.save(question);
    }
}
