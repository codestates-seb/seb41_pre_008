package com.codestates.pre_project.question.mapper;

import com.codestates.pre_project.question.dto.QuestionPatchDto;
import com.codestates.pre_project.question.dto.QuestionPostDto;
import com.codestates.pre_project.question.dto.QuestionResponseDto;
import com.codestates.pre_project.question.dto.QuestionTagResponseDto;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.entity.QuestionTag;
import com.codestates.pre_project.tag.entity.Tag;
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

        //Member member = new Member();
        //member.setMemberId(questionPostDto.getMemberId());

        List<QuestionTag> questionTags = questionPostDto.getQuestionTags().stream()
                .map(questionTagDto -> {
                    QuestionTag questionTag = new QuestionTag();
                    Tag tag = new Tag();
                    tag.setTagId(questionTagDto.getTagId());
                    questionTag.setQuestion(question);
                    questionTag.setTag(tag);
                    return questionTag;
                }).collect(Collectors.toList());
        //question.setMember(member);
        question.setQuestionTags(questionTags);
        return question;
    }

    QuestionResponseDto questionToQuestionResponseDto(Question question);

    @Mapping(source = "tag.tagId", target = "tagId")
    @Mapping(source = "tag.name", target = "name")
    QuestionTagResponseDto questionTagToQuestionTagResponseDto(QuestionTag questionTag);


}
