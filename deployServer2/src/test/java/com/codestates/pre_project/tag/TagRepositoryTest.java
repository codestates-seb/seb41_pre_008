package com.codestates.pre_project.tag;

import com.codestates.pre_project.tag.entity.Tag;
import com.codestates.pre_project.tag.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @Test
    public void saveTagTest(){
        //given
        Tag tag = new Tag();
        tag.setName("java");
        tag.setContent("java content");

        //when
        Tag savedTag = tagRepository.save(tag);

        //then
        assertNotNull(savedTag);
        assertTrue(tag.getName().equals(savedTag.getName()));
        assertTrue(tag.getContent().equals(savedTag.getContent()));
    }

    @Test
    public void findByIdTest(){
        //given
        Tag tag = new Tag();
        tag.setName("python");
        tag.setContent("python conent");

        //when
        tagRepository.save(tag);
        Optional<Tag> findTag = tagRepository.findById(tag.getTagId());

        //then
        assertTrue(findTag.isPresent());
        assertTrue(findTag.get().getTagId().equals(tag.getTagId()));
    }
}
