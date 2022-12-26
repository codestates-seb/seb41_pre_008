package com.codestates.pre_project.question.mapper;

import com.codestates.pre_project.comment.dto.CommentResponseDto;
import com.codestates.pre_project.comment.dto.CommentResponseDto.CommentResponseDtoBuilder;
import com.codestates.pre_project.comment.entity.Comment;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.dto.QuestionPatchDto;
import com.codestates.pre_project.question.dto.QuestionResponseDto;
import com.codestates.pre_project.question.dto.QuestionTagDto;
import com.codestates.pre_project.question.dto.QuestionTagResponseDto;
import com.codestates.pre_project.question.dto.QuestionTagResponseDto.QuestionTagResponseDtoBuilder;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.entity.QuestionTag;
import com.codestates.pre_project.tag.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-26T17:06:07+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatchDto.getQuestionId() );
        question.setTitle( questionPatchDto.getTitle() );
        question.setProblemContent( questionPatchDto.getProblemContent() );
        question.setExpectContent( questionPatchDto.getExpectContent() );
        question.setQuestionStatus( questionPatchDto.getQuestionStatus() );
        question.setQuestionTags( questionTagDtoListToQuestionTagList( questionPatchDto.getQuestionTags() ) );

        return question;
    }

    @Override
    public List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionResponseDto> list = new ArrayList<QuestionResponseDto>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }

    @Override
    public QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto questionResponseDto = new QuestionResponseDto();

        Long memberId = questionMemberMemberId( question );
        if ( memberId != null ) {
            questionResponseDto.setMemberId( memberId );
        }
        questionResponseDto.setNickName( questionMemberNickName( question ) );
        if ( question.getQuestionId() != null ) {
            questionResponseDto.setQuestionId( question.getQuestionId() );
        }
        questionResponseDto.setTitle( question.getTitle() );
        questionResponseDto.setProblemContent( question.getProblemContent() );
        questionResponseDto.setExpectContent( question.getExpectContent() );
        questionResponseDto.setQuestionStatus( question.getQuestionStatus() );
        questionResponseDto.setQuestionTags( questionTagListToQuestionTagResponseDtoList( question.getQuestionTags() ) );
        questionResponseDto.setCreatedAt( question.getCreatedAt() );
        questionResponseDto.setModifiedAt( question.getModifiedAt() );
        questionResponseDto.setComments( commentListToCommentResponseDtoList( question.getComments() ) );

        return questionResponseDto;
    }

    @Override
    public QuestionTagResponseDto questionTagToQuestionTagResponseDto(QuestionTag questionTag) {
        if ( questionTag == null ) {
            return null;
        }

        QuestionTagResponseDtoBuilder questionTagResponseDto = QuestionTagResponseDto.builder();

        Long tagId = questionTagTagTagId( questionTag );
        if ( tagId != null ) {
            questionTagResponseDto.tagId( tagId );
        }
        questionTagResponseDto.name( questionTagTagName( questionTag ) );

        return questionTagResponseDto.build();
    }

    protected QuestionTag questionTagDtoToQuestionTag(QuestionTagDto questionTagDto) {
        if ( questionTagDto == null ) {
            return null;
        }

        QuestionTag questionTag = new QuestionTag();

        return questionTag;
    }

    protected List<QuestionTag> questionTagDtoListToQuestionTagList(List<QuestionTagDto> list) {
        if ( list == null ) {
            return null;
        }

        List<QuestionTag> list1 = new ArrayList<QuestionTag>( list.size() );
        for ( QuestionTagDto questionTagDto : list ) {
            list1.add( questionTagDtoToQuestionTag( questionTagDto ) );
        }

        return list1;
    }

    private Long questionMemberMemberId(Question question) {
        if ( question == null ) {
            return null;
        }
        Member member = question.getMember();
        if ( member == null ) {
            return null;
        }
        Long memberId = member.getMemberId();
        if ( memberId == null ) {
            return null;
        }
        return memberId;
    }

    private String questionMemberNickName(Question question) {
        if ( question == null ) {
            return null;
        }
        Member member = question.getMember();
        if ( member == null ) {
            return null;
        }
        String nickName = member.getNickName();
        if ( nickName == null ) {
            return null;
        }
        return nickName;
    }

    protected List<QuestionTagResponseDto> questionTagListToQuestionTagResponseDtoList(List<QuestionTag> list) {
        if ( list == null ) {
            return null;
        }

        List<QuestionTagResponseDto> list1 = new ArrayList<QuestionTagResponseDto>( list.size() );
        for ( QuestionTag questionTag : list ) {
            list1.add( questionTagToQuestionTagResponseDto( questionTag ) );
        }

        return list1;
    }

    protected CommentResponseDto commentToCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDtoBuilder commentResponseDto = CommentResponseDto.builder();

        if ( comment.getCommentId() != null ) {
            commentResponseDto.commentId( comment.getCommentId() );
        }
        commentResponseDto.content( comment.getContent() );
        commentResponseDto.createdAt( comment.getCreatedAt() );
        commentResponseDto.modifiedAt( comment.getModifiedAt() );

        return commentResponseDto.build();
    }

    protected List<CommentResponseDto> commentListToCommentResponseDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponseDto> list1 = new ArrayList<CommentResponseDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentToCommentResponseDto( comment ) );
        }

        return list1;
    }

    private Long questionTagTagTagId(QuestionTag questionTag) {
        if ( questionTag == null ) {
            return null;
        }
        Tag tag = questionTag.getTag();
        if ( tag == null ) {
            return null;
        }
        Long tagId = tag.getTagId();
        if ( tagId == null ) {
            return null;
        }
        return tagId;
    }

    private String questionTagTagName(QuestionTag questionTag) {
        if ( questionTag == null ) {
            return null;
        }
        Tag tag = questionTag.getTag();
        if ( tag == null ) {
            return null;
        }
        String name = tag.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
