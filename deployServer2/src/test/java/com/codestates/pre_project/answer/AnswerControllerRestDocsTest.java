package com.codestates.pre_project.answer;

import com.codestates.pre_project.answer.controller.AnswerController;
import com.codestates.pre_project.answer.dto.AnswerPatchDto;
import com.codestates.pre_project.answer.dto.AnswerPostDto;
import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.answer.mapper.AnswerMapper;
import com.codestates.pre_project.answer.service.AnswerService;
import com.codestates.pre_project.member.service.MemberService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.codestates.pre_project.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.pre_project.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class AnswerControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper mapper;

    @MockBean
    private MemberService memberService;

    @Autowired
    private Gson gson;

    @Test
    public void postAnswerTest() throws Exception {
        // given
        AnswerPostDto post = new AnswerPostDto(1L, 1L, "answerContent");
        String content = gson.toJson(post);

        AnswerResponseDto responseDto =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent", Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        // willReturn()이 최소 null은 아니어야 한다.
        given(mapper.answerPostDtoToAnswer(Mockito.any(AnswerPostDto.class)))
                .willReturn(new Answer());

        Answer mockResultAnswer = new Answer();
        mockResultAnswer.setAnswerId(1L);
        given(answerService.createAnswer(Mockito.any(Answer.class)))
                .willReturn(new Answer());

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/answers")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").value(post.getMemberId()))
                .andExpect(jsonPath("$.questionId").value(post.getQuestionId()))
                .andExpect(jsonPath("$.answerContent").value(post.getAnswerContent()))
                .andDo(document("post-answer",    // =========== (1) API 문서화 관련 코드 시작 ========
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("답변 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        //fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("answerStatus").type(JsonFieldType.STRING).description("답변 상태 : 답안 채택/ 답안 채택 안됨"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")
                                )
                        )
                ));   // =========== (2) API 문서화 관련 코드 끝========
    }
    @Test
    public void patchAnswerTest() throws Exception {
        // given
        long answerId = 1L;
        AnswerPatchDto patch = new AnswerPatchDto(1L, "answerContent", Answer.AnswerStatus.ANSWER_NOTSELECT);
        String content = gson.toJson(patch);

        AnswerResponseDto responseDto =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent", Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());


        // willReturn()이 최소한 null은 아니어야 한다.
        given(mapper.answerPatchDtoToAnswer(Mockito.any(AnswerPatchDto.class))).willReturn(new Answer());

        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(new Answer());

        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/answers/{answer-id}", answerId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerId").value(patch.getAnswerId()))
                .andExpect(jsonPath("$.answerContent").value(patch.getAnswerContent()))
                //.andExpect(jsonPath("$.answerStatus").value(patch.getAnswerStatus().getStatus()))
                .andDo(document("patch-answer",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자").ignored(),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("답변 내용").optional(),
                                        fieldWithPath("answerStatus").type(JsonFieldType.STRING).description("답변 상태: ANSWER_NOTSELECT/ ANSWER_SELECT").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("answerStatus").type(JsonFieldType.STRING).description("답변 상태 : 답안 채택/ 답안 채택 안됨"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")
                                )
                        )
                ));
    }
    @Test
    public void getAnswerTest() throws Exception {
        //given
        long answerId = 1L;
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setAnswerStatus(Answer.AnswerStatus.ANSWER_NOTSELECT);
        answer.setAnswerContent("answerContent");

        AnswerResponseDto response =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent", Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());


        // Stubbing by Mockito
        given(answerService.findAnswer(Mockito.anyLong())).willReturn(new Answer());
        given(mapper.answerToAnswerResponseDto(Mockito.any(Answer.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform
                (RestDocumentationRequestBuilders.get("/answers/{answer-id}", answerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.answerId").value(answer.getAnswerId()))
                //.andExpect(jsonPath("$.answerStatus").value(answer.getAnswerStatus().getStatus()))
                .andExpect(jsonPath("$.answerContent").value(answer.getAnswerContent()))
                .andDo(document("get-answer",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("answer-id").description("답변 식별자")),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("answerStatus").type(JsonFieldType.STRING).description("답변 상태 : 답안 채택/ 답안 채택 안됨"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")
                                )
                        )));
    }

    @Test
    public void getAnswersTest() throws Exception {
        // given
        Answer answer1 = new Answer();
        answer1.setAnswerStatus(Answer.AnswerStatus.ANSWER_NOTSELECT);
        answer1.setAnswerId(1L);
        answer1.setAnswerContent("answerContent");

        Answer answer2 = new Answer();
        answer2.setAnswerStatus(Answer.AnswerStatus.ANSWER_NOTSELECT);
        answer2.setAnswerId(2L);
        answer2.setAnswerContent("answerContent2");

        AnswerResponseDto response1 = new AnswerResponseDto(1L, 1L, "nickName", 1L, "answerContent", Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());
        AnswerResponseDto response2 = new AnswerResponseDto(2L, 2L, "nickName2", 2L, "answerContent2", Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        Page<Answer> pageAnswers =
                new PageImpl<>(List.of(answer1, answer2),
                        PageRequest.of(0, 2, Sort.by("answerId").descending()), 2);
        List<AnswerResponseDto> responses = List.of(response1, response2);


        // Stubbing by Mockito
        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageAnswers);
        given(mapper.answersToAnswerResponseDtos(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.get("/answers")
                .param("page","1")
                .param("size","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("get-answers",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("검색 갯수")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data[].answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("data[].answerStatus").type(JsonFieldType.STRING).description("답변 상태 : 답안 채택/ 답안 채택 안됨"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("딥변 생성 시간"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("딥변 수정 시간"),
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
    public void deleteAnswerTest() throws Exception {
        // given
        long answerId = 1L;

        // Stubbing by Mockito
        doNothing().when(answerService).deleteAnswer(Mockito.anyLong());

        // when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/answers/{answer-id}",answerId));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(document("delete-answer",
                        pathParameters(parameterWithName("answer-id").description("답변 식별자"))));
    }
}
