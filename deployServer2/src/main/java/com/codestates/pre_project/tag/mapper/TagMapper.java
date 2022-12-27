package com.codestates.pre_project.tag.mapper;

import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.tag.dto.TagPostDto;
import com.codestates.pre_project.tag.dto.TagResponseDto;
import com.codestates.pre_project.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag tagPostDtoToTag(TagPostDto tagPostDto);
    TagResponseDto tagToTagResponseDto(Tag tag);
    List<TagResponseDto> tagsToTagResponseDtos(List<Tag> tags);

}
