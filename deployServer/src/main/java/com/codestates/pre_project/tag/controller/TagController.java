package com.codestates.pre_project.tag.controller;

import com.codestates.pre_project.question.dto.QuestionPatchDto;
import com.codestates.pre_project.question.dto.QuestionPostDto;
import com.codestates.pre_project.question.dto.QuestionResponseDto;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.mapper.QuestionMapper;
import com.codestates.pre_project.question.service.QuestionService;
import com.codestates.pre_project.tag.dto.TagPostDto;
import com.codestates.pre_project.tag.dto.TagResponseDto;
import com.codestates.pre_project.tag.entity.Tag;
import com.codestates.pre_project.tag.mapper.TagMapper;
import com.codestates.pre_project.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;


    public TagController(TagService tagService,
                         TagMapper tagMapper){
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }


    @PostMapping
    public ResponseEntity postTag(@Valid @RequestBody TagPostDto tagPostDto) {
        Tag tag = tagService.createTag(tagMapper.tagPostDtoToTag(tagPostDto));

        return new ResponseEntity<>(tagMapper.tagToTagResponseDto(tag), HttpStatus.CREATED);
    }


    @GetMapping("/{tag-id}")
    public ResponseEntity getTag(@PathVariable("tag-id") @Positive long tagId) {
        Tag tag = tagService.findTag(tagId);

        return new ResponseEntity<>(tagMapper.tagToTagResponseDto(tag), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTags() {
        List<Tag> tags = tagService.findTags();

        List<TagResponseDto> response =
                tags.stream()
                        .map(tag -> tagMapper.tagToTagResponseDto(tag))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{tag-id}")
    public ResponseEntity deleteTag(@PathVariable("tag-id") @Positive long tagId) {
        tagService.deleteTag(tagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTags() {
        tagService.deleteTags();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
