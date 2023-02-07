package com.codestates.pre_project.tag;

import com.codestates.pre_project.tag.dto.TagPostDto;
import com.codestates.pre_project.tag.entity.Tag;
import com.codestates.pre_project.tag.mapper.TagMapper;
import com.codestates.pre_project.tag.service.TagService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    // (1)
    @MockBean
    private TagService tagService;

    // (2)
    @Autowired
    private TagMapper mapper;

    @Test
    void postTagTest() throws Exception {
        // given
        TagPostDto post = new TagPostDto("java", "Java Content");

        Tag tag = mapper.tagPostDtoToTag(post);
        tag.setTagId(1L);

        given(tagService.createTag(Mockito.any(Tag.class)))
                .willReturn(tag);

        String content = gson.toJson(post);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/tags")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.content").value(post.getContent()))
                .andReturn();
    }

    @Test
    void getTagTest() throws Exception {
        //given
        Tag tag = new Tag("java", "java content");
        Long tagId = 1L;
        tag.setTagId(tagId);

        given(tagService.findTag(Mockito.eq(tagId))).willReturn(tag);
        //when
        ResultActions actions = mockMvc.perform(
                get("/tags/" + tagId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("java"))
                .andExpect(jsonPath("$.content").value("java content"))
                .andReturn();

        //System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    void getTagsTest() throws Exception {
        //given
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag("java", "java content");;
        Tag tag2 = new Tag("python", "python content");

        tag1.setTagId(1L);
        tag2.setTagId(2L);

        tags.add(tag1);
        tags.add(tag2);

        int page = 1;
        int size = 2;

        Page<Tag> response = new PageImpl<>(tags,
                PageRequest.of(page-1, size, Sort.by("tagId").descending()), tags.size());

        given(tagService.findTags(Mockito.anyInt(), Mockito.anyInt())).willReturn(response);

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/tags/?page=" + page +"&size=" + size)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteMemberTest() throws Exception {
        //given
        Long tagId = 1L;

        doNothing().when(tagService).deleteTag(tagId);

        //when
        ResultActions actions =
                mockMvc.perform(
                        delete("/tags/" + tagId)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        //then
        MvcResult result = actions
                .andExpect(status().isNoContent())
                .andReturn();

        //System.out.println(result.getResponse().getContentAsString());
    }
}
