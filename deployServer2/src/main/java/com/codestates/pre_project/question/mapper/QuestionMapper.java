package com.codestates.pre_project.question.mapper;

import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.comment.dto.CommentResponseDto;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.dto.*;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.entity.QuestionTag;
import com.codestates.pre_project.tag.entity.Tag;
import com.codestates.pre_project.tag.repository.TagRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    /*
    Question questionPostDtoToQuestion(QuestionPostDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);
    QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);
     */



    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);
    List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);

    default Question questionPostDtoToQuestion(QuestionPostDto questionPostDto) {
        Question question = new Question();
        question.setTitle(questionPostDto.getTitle());
        question.setProblemContent(questionPostDto.getProblemContent());
        question.setExpectContent(questionPostDto.getExpectContent());

        Member member = new Member();
        member.setMemberId(questionPostDto.getMemberId());

        List<QuestionTag> questionTags = questionPostDto.getQuestionTags().stream()
                .map(questionTagDto -> {
                    QuestionTag questionTag = new QuestionTag();
                    Tag tag = new Tag();
                    tag.setTagId(questionTagDto.getTagId());
                    questionTag.setQuestion(question);
                    questionTag.setTag(tag);
                    return questionTag;
                }).collect(Collectors.toList());
        question.setMember(member);
        question.setQuestionTags(questionTags);
        return question;
    }
/*
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "member.nickName", target = "nickName")
    QuestionResponseDto questionToQuestionResponseDto(Question question);

 */
     //question과 comment 임시 매핑 삭제
    default QuestionResponseDto questionToQuestionResponseDto(Question question) {
        Member member = question.getMember();
       // List<Comment> comments = question.getComments();
        List<QuestionTag> tags = question.getQuestionTags();
        List<Answer> answers = question.getAnswers();
        //List<CommentResponseDto> answerComments = answers.getAnswerComment(answers);
        /*
        List<CommentResponseDto> commentResponse
                = comments.stream()
                .map(comment ->
                        new CommentResponseDto(comment.getCommentId(),
                                comment.getMember().getMemberId(),
                                comment.getMember().getNickName(),
                                comment.getQuestion().getQuestionId(),
                                //comment.getAnswer().getAnswerId(),
                                comment.getContent(),
                                comment.getCreatedAt(),
                                comment.getModifiedAt()))
                .collect(Collectors.toList());

     */

        /*
        List<CommentResponseDto> commentResponse2
                = answers.stream()
                .map(comment ->
                        new CommentResponseDto(comment.getCommentId(),
                                comment.getMember().getMemberId(),
                                comment.getMember().getNickName(),
                                comment.getQuestion().getQuestionId(),
                                comment.getAnswer().getAnswerId(),
                                comment.getContent(),
                                comment.getCreatedAt(),
                                comment.getModifiedAt()))
                .collect(Collectors.toList());

         */


        List<QuestionTagResponseDto> questionTags
                = tags.stream()
                .map(tag ->
                        new QuestionTagResponseDto(tag.getTag().getTagId(),
                                tag.getTag().getName()))
                .collect(Collectors.toList());

        /*
        List<AnswerResponseDto> answerResponse =
                answers.stream()
                        .map(answer ->
                                new AnswerResponseDto(answer.getAnswerId(),
                                        answer.getMember().getMemberId(),
                                        answer.getMember().getNickName(),
                                        answer.getQuestion().getQuestionId(),
                                        answer.getAnswerContent(),
                                        answer.getAnswerStatus(),
                                        answer.getCreatedAt(),
                                        answer.getModifiedAt(),
                                        commentResponse2)
                        ).collect(Collectors.toList());


         */
        List<AnswerResponseDto> answerResponse =
                answers.stream()
                        .map(answer ->
                                new AnswerResponseDto(answer.getAnswerId(),
                                        answer.getMember().getMemberId(),
                                        answer.getMember().getNickName(),
                                        answer.getQuestion().getQuestionId(),
                                        answer.getAnswerContent(),
                                        answer.getLikes(),
                                        answer.getAnswerStatus(),
                                        answer.getCreatedAt(),
                                        answer.getModifiedAt())).collect(Collectors.toList());

        return QuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .memberId(question.getMember().getMemberId())
                .nickName(question.getMember().getNickName())
                .title(question.getTitle())
                .problemContent(question.getProblemContent())
                .expectContent(question.getExpectContent())
                .likes(question.getLikes()) // 12.29 pm 1:31 추가
                .questionStatus(question.getQuestionStatus())
                .questionTags(questionTags)
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                //.comments(commentResponse)
                .answers(answerResponse)
                .build();

        /*
        List<CommentResponseDto> commentResponse
                = comments.stream()
                .filter(a -> a.getCommentType().equals(Comment.CommentType.QUESTION))
                .map(comment ->
                        new CommentResponseDto(comment.getCommentId(),
                                comment.getMember().getMemberId(),
                                comment.getMember().getNickName(),
                                comment.getContent(),
                                comment.getCreatedAt(),
                                comment.getModifiedAt(),
                                comment.getCommentType()))
                .collect(Collectors.toList());

        List<CommentResponseDto> commentResponse2
                = comments.stream()
                .filter(a -> a.getCommentType().equals(Comment.CommentType.ANSWER))
                .map(comment ->
                        new CommentResponseDto(comment.getCommentId(),
                                comment.getMember().getMemberId(),
                                comment.getMember().getNickName(),
                                comment.getContent(),
                                comment.getCreatedAt(),
                                comment.getModifiedAt(),
                                comment.getCommentType()))
                .collect(Collectors.toList());

        List<QuestionTagResponseDto> questionTags
                = tags.stream()
                .map(tag ->
                        new QuestionTagResponseDto(tag.getTag().getTagId(),
                                tag.getTag().getName()))
                .collect(Collectors.toList());

        List<AnswerResponseDto> answerResponse =
                answers.stream()
                        .map(answer ->
                                new AnswerResponseDto(answer.getAnswerId(),
                                        answer.getMember().getMemberId(),
                                        answer.getMember().getNickName(),
                                        answer.getQuestion().getQuestionId(),
                                        answer.getAnswerContent(),
                                        answer.getAnswerStatus(),
                                        answer.getCreatedAt(),
                                        answer.getModifiedAt(),
                                        commentResponse2)).collect(Collectors.toList());



        return QuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .memberId(question.getMember().getMemberId())
                .nickName(question.getMember().getNickName())
                .title(question.getTitle())
                .problemContent(question.getProblemContent())
                .expectContent(question.getExpectContent())
                .questionStatus(question.getQuestionStatus())
                .questionTags(questionTags)
                .createdAt(question.getCreatedAt())
                .modifiedAt(question.getModifiedAt())
                .comments(commentResponse)
                .answers(answerResponse)
                .build();

         */
    }


    @Mapping(source = "tag.tagId", target = "tagId")
    @Mapping(source = "tag.name", target = "name")
    QuestionTagResponseDto questionTagToQuestionTagResponseDto(QuestionTag questionTag);

    QuestionLikesResponseDto questionToQuestionLikesResponseDto(Question question);
}
