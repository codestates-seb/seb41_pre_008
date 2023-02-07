package com.codestates.pre_project.tag;

import com.codestates.pre_project.tag.controller.TagController;
import com.codestates.pre_project.tag.dto.TagPostDto;
import com.codestates.pre_project.tag.dto.TagResponseDto;
import com.codestates.pre_project.tag.entity.Tag;
import com.codestates.pre_project.tag.mapper.TagMapper;
import com.codestates.pre_project.tag.service.TagService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.List;

import static com.codestates.pre_project.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.pre_project.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class TagControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @MockBean
    private TagMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void postTagTest() throws Exception {
        // given
        TagPostDto post = new TagPostDto("java", "java content");
        String content = gson.toJson(post);

        TagResponseDto responseDto =
                new TagResponseDto(1L, "java", "java content");

        // willReturn()이 최소 null은 아니어야 한다.
        given(mapper.tagPostDtoToTag(Mockito.any(TagPostDto.class)))
                .willReturn(new Tag());

        Tag mockResultTag = new Tag();
        mockResultTag.setTagId(1L);
        given(tagService.createTag(Mockito.any(Tag.class)))
                .willReturn(new Tag());

        given(mapper.tagToTagResponseDto(Mockito.any(Tag.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/tags")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(post.getName()))
                .andExpect(jsonPath("$.content").value(post.getContent()))
                .andDo(document("post-tag",    // =========== (1) API 문서화 관련 코드 시작 ========
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("태그이름"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("태그 설명")
                                )
                        ),
                        responseFields(
                                List.of(
                                        //fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("태그 설명")
                                )
                        )
                ));   // =========== (2) API 문서화 관련 코드 끝========
    }

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void getTagTest() throws Exception {
        // given
        long tagId = 1L;
        Tag tag = new Tag("java", "java content");
        tag.setTagId(tagId);

        TagResponseDto response = new TagResponseDto(1L, "java","java content");

        // Stubbing by Mockito
        given(tagService.findTag(Mockito.anyLong())).willReturn(new Tag());
        given(mapper.tagToTagResponseDto(Mockito.any(Tag.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform
                (RestDocumentationRequestBuilders.get("/tags/{tag-id}", tagId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tag.getName()))
                .andExpect(jsonPath("$.content").value(tag.getContent()))
                .andDo(document("get-tag",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("tag-id").description("태그 식별자")),
                        responseFields(List.of(
                                //fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                fieldWithPath("tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("태그 이름"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("태그 설명")
                        ))));
    }

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void getTagsTest() throws Exception {
        // given
        Tag tag1 = new Tag("java", "java content");
        tag1.setTagId(1L);

        Tag tag2 = new Tag("python", "python content");
        tag2.setTagId(2L);

        TagResponseDto response1 = new TagResponseDto(1L, "java", "java content");
        TagResponseDto response2 = new TagResponseDto(2L, "python", "python content");

        Page<Tag> pageTags =
                new PageImpl<>(List.of(tag1, tag2),
                        PageRequest.of(0, 2, Sort.by("tagId").descending()), 2);
        List<TagResponseDto> responses = List.of(response1, response2);


        // Stubbing by Mockito
        given(tagService.findTags(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageTags);
        given(mapper.tagsToTagResponseDtos(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.get("/tags")
                .param("page","1")
                .param("size","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("get-tags",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("검색 갯수")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("태그 설명"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 번호"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 갯수"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 페이지 요소"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 갯수")
                                )
                        )
                ));
    }

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void deleteTagTest() throws Exception {
        // given
        long tagId = 1L;

        // Stubbing by Mockito
        doNothing().when(tagService).deleteTag(Mockito.anyLong());

        // when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/tags/{tag-id}",tagId).with(csrf()));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(document("delete-tag",
                        pathParameters(parameterWithName("tag-id").description("태그 식별자"))));
    }
}
