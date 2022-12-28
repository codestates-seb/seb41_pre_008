package com.codestates.pre_project.tag;

import com.codestates.pre_project.tag.dto.TagPostDto;
import com.codestates.pre_project.tag.dto.TagResponseDto;
import com.codestates.pre_project.tag.repository.TagRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    private Gson gson;

    TagPostDto post;
    ResultActions postActions;

    TagResponseDto response;

    @BeforeEach
    public void init() throws Exception{
        //테스트 데이터 설정
        post = new TagPostDto("java",
                "java Content");
        String content = gson.toJson(post);

        postActions =
                mockMvc.perform(
                        post("/tags")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
    }


    @Test
    void postMemberTest() throws Exception {
        MvcResult result = postActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.content").value(post.getContent()))
                .andReturn();
    }

    @Test
    void getTagTest() throws Exception {
        //given
        Long tagId = tagRepository.findByName(post.getName()).get().getTagId();

        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/tags/{tagId}" ,tagId)
                                .contentType(MediaType.APPLICATION_JSON)
                );

        //then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("java"))
                .andExpect(jsonPath("$.content").value("java Content"))
                .andReturn();

        //System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void getTagsTest() throws Exception {
        //given
        int page = 1;
        int size = 1;
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

        //System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void deleteTagTest() throws Exception {
        //given
        Long tagId = tagRepository.findByName(post.getName()).get().getTagId();

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
