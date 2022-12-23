package com.codestates.pre_project.tag.service;

import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.tag.entity.Tag;
import com.codestates.pre_project.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Tag createTag(Tag tag) {
        Tag savedTag = saveTag(tag);

        return savedTag;
    }


    public Tag findTag(long tagId) {
        return findVerifiedTag(tagId);
    }

    public List<Tag> findTags() {
        return (List<Tag>) tagRepository.findAll();
    }

    public void deleteTag(long tagId) {
        Tag tag = findVerifiedTag(tagId);

        tagRepository.delete(tag);
    }

    public void deleteTags (){
        tagRepository.deleteAll();
    }

    public Tag findVerifiedTag(long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findTag =
                optionalTag.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
        return findTag;
    }

    private Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
