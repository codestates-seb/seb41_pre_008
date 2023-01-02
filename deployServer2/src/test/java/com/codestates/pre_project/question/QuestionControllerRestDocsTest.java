package com.codestates.pre_project.question;

import com.codestates.pre_project.answer.dto.AnswerLikesResponseDto;
import com.codestates.pre_project.answer.dto.AnswerPostDto;
import com.codestates.pre_project.answer.dto.AnswerResponseDto;
import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.answer.mapper.AnswerMapper;
import com.codestates.pre_project.answer.service.AnswerService;
import com.codestates.pre_project.comment.mapper.CommentMapper;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.question.controller.QuestionController;
import com.codestates.pre_project.question.dto.*;
import com.codestates.pre_project.question.entity.Question;
import com.codestates.pre_project.question.mapper.QuestionMapper;
import com.codestates.pre_project.question.service.QuestionService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionControllerRestDocsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @MockBean
    private CommentMapper commentMapper;

    @Autowired
    private Gson gson;

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void postQuestionTest() throws Exception {
        // given
        QuestionTagDto questionTagDto1 = new QuestionTagDto();
        questionTagDto1.setTagId(1L);
        QuestionTagDto questionTagDto2 = new QuestionTagDto();
        questionTagDto2.setTagId(2L);
        List<QuestionTagDto> questionTagDtos = new ArrayList<>();
        questionTagDtos.add(questionTagDto1);
        questionTagDtos.add(questionTagDto2);

        QuestionPostDto post = new QuestionPostDto(1L, "title title title","problemContent problemContent problemContent", "expectContent expectContent expectContent", questionTagDtos );
        String content = gson.toJson(post);


        AnswerResponseDto responseDto1 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent", 0, Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        AnswerResponseDto responseDto2 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent",0, Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        List<AnswerResponseDto> answerResponseDtos= new ArrayList<>();
        answerResponseDtos.add(responseDto1);
        answerResponseDtos.add(responseDto2);


        QuestionTagResponseDto questionTagResponseDto1 = new QuestionTagResponseDto(1L, "java");
        QuestionTagResponseDto questionTagResponseDto2 = new QuestionTagResponseDto(2L, "python");

        List<QuestionTagResponseDto> questionTagResponseDtos = new ArrayList<>();
        questionTagResponseDtos.add(questionTagResponseDto1);
        questionTagResponseDtos.add(questionTagResponseDto2);


        QuestionResponseDto responseDto =
                new QuestionResponseDto
                        (1L, 1L, "nickName", "title title title", "problemContent problemContent problemContent",
                                "expectContent expectContent expectContent", 0,Question.QuestionStatus.QUESTION_NOTSELECT, questionTagResponseDtos,
                                LocalDateTime.now(), LocalDateTime.now(), answerResponseDtos);

        // willReturn()이 최소 null은 아니어야 한다.
        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionPostDto.class)))
                .willReturn(new Question());

        Question mockResultQuestion = new Question();
        mockResultQuestion.setQuestionId(1L);
        given(questionService.createQuestion(Mockito.any(Question.class)))
                .willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.memberId").value(post.getMemberId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.problemContent").value(post.getProblemContent()))
                .andExpect(jsonPath("$.expectContent").value(post.getExpectContent()))
                .andDo(document("post-question",    // =========== (1) API 문서화 관련 코드 시작 ========
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("problemContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("expectContent").type(JsonFieldType.STRING).description("원하는 해결 방향"),
                                        fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("질문과 관련된 태그들"),
                                        fieldWithPath("questionTags[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자")
                                )
                        ),
                        responseFields(
                                List.of(
                                        //fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("problemContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("expectContent").type(JsonFieldType.STRING).description("원하는 해결 방향"),
                                        fieldWithPath("likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("questionStatus").type(JsonFieldType.STRING).description("질문 상태 : 채택된 답안이 있는 문의/ 채택된 답안이 없는 문의"),
                                        fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("질문과 관련된 태그들"),
                                        fieldWithPath("questionTags[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                        fieldWithPath("questionTags[].name").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변 리스트"),
                                        fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("answers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("answers[].nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("answers[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answers[].answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("answers[].likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("answers[].answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("answers[].createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("answers[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")

                                )
                        )
                ));   // =========== (2) API 문서화 관련 코드 끝========
    }

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void upQuestionLikesTes() throws Exception {
        long questionId = 1L;
        QuestionLikesResponseDto response = new QuestionLikesResponseDto();
        response.setLikes(1);

        given(questionService.upQuestion(Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionLikesResponseDto(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        post("/questions/up/{question-id}", questionId)
                                .with(csrf())
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("likes").value(response.getLikes()))
                .andDo(document("up-question",
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        responseFields(
                                fieldWithPath("likes").type(JsonFieldType.NUMBER).description("좋아요 수")
                        )
                ));
    }
    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void downQuestionLikesTes() throws Exception {
        long questionId = 1L;
        QuestionLikesResponseDto response = new QuestionLikesResponseDto();
        response.setLikes(1);

        given(questionService.downQuestion(Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionLikesResponseDto(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        post("/questions/down/{question-id}", questionId)
                                .with(csrf())
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("likes").value(response.getLikes()))
                .andDo(document("down-question",
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        responseFields(
                                fieldWithPath("likes").type(JsonFieldType.NUMBER).description("좋아요 수")
                        )
                ));
    }
    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void patchQuestionTest() throws Exception {
        // given
        long questionId = 1L;

        QuestionTagDto questionTagDto1 = new QuestionTagDto();
        questionTagDto1.setTagId(1L);
        QuestionTagDto questionTagDto2 = new QuestionTagDto();
        questionTagDto2.setTagId(2L);
        List<QuestionTagDto> questionTagDtos = new ArrayList<>();
        questionTagDtos.add(questionTagDto1);
        questionTagDtos.add(questionTagDto2);

        QuestionPatchDto patch = new QuestionPatchDto();
        patch.setQuestionId(questionId);
        patch.setTitle("title title title");
        patch.setProblemContent("problemContent problemContent problemContent");
        patch.setExpectContent("expectContent expectContent expectContent");
        patch.setQuestionStatus(Question.QuestionStatus.QUESTION_NOTSELECT);
        patch.setQuestionTags(questionTagDtos);
        String content = gson.toJson(patch);

        AnswerResponseDto responseDto1 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent",0, Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        AnswerResponseDto responseDto2 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent", 0,Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        List<AnswerResponseDto> answerResponseDtos= new ArrayList<>();
        answerResponseDtos.add(responseDto1);
        answerResponseDtos.add(responseDto2);


        QuestionTagResponseDto questionTagResponseDto1 = new QuestionTagResponseDto(1L, "java");
        QuestionTagResponseDto questionTagResponseDto2 = new QuestionTagResponseDto(2L, "python");

        List<QuestionTagResponseDto> questionTagResponseDtos = new ArrayList<>();
        questionTagResponseDtos.add(questionTagResponseDto1);
        questionTagResponseDtos.add(questionTagResponseDto2);


        QuestionResponseDto responseDto =
                new QuestionResponseDto
                        (1L, 1L, "nickName", "title title title", "problemContent problemContent problemContent",
                                "expectContent expectContent expectContent", 0, Question.QuestionStatus.QUESTION_NOTSELECT, questionTagResponseDtos,
                                LocalDateTime.now(), LocalDateTime.now(), answerResponseDtos);


        // willReturn()이 최소한 null은 아니어야 한다.
        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionPatchDto.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/questions/{question-id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(patch.getTitle()))
                .andExpect(jsonPath("$.problemContent").value(patch.getProblemContent()))
                .andExpect(jsonPath("$.expectContent").value(patch.getExpectContent()))
                .andDo(document("patch-question",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자").ignored(),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("problemContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("expectContent").type(JsonFieldType.STRING).description("원하는 해결 방향"),
                                        fieldWithPath("questionStatus").type(JsonFieldType.STRING).description("질문 상태 : QUESTION_SELECT/ QUESTION_NOTSELECT / QUESTION_DELETE"),
                                        fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("질문과 관련된 태그들"),
                                        fieldWithPath("questionTags[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자")
                                )
                        ),
                        responseFields(
                                List.of(
                                        //fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("problemContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("expectContent").type(JsonFieldType.STRING).description("원하는 해결 방향"),
                                        fieldWithPath("likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("questionStatus").type(JsonFieldType.STRING).description("질문 상태 : 채택된 답안이 있는 문의/ 채택된 답안이 없는 문의"),
                                        fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("질문과 관련된 태그들"),
                                        fieldWithPath("questionTags[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                        fieldWithPath("questionTags[].name").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변 리스트"),
                                        fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("answers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("answers[].nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("answers[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answers[].answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("answers[].likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("answers[].answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("answers[].createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("answers[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")

                                )
                        )
                ));
    }

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void getQuestionTest() throws Exception {
        long questionId = 1L;
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setTitle("title title title");
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_NOTSELECT);
        question.setExpectContent("expectContent expectContent expectContent");
        question.setProblemContent("problemContent problemContent problemContent");

        AnswerResponseDto responseDto1 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent",0, Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        AnswerResponseDto responseDto2 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent",0, Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        List<AnswerResponseDto> answerResponseDtos= new ArrayList<>();
        answerResponseDtos.add(responseDto1);
        answerResponseDtos.add(responseDto2);


        QuestionTagResponseDto questionTagResponseDto1 = new QuestionTagResponseDto(1L, "java");
        QuestionTagResponseDto questionTagResponseDto2 = new QuestionTagResponseDto(2L, "python");

        List<QuestionTagResponseDto> questionTagResponseDtos = new ArrayList<>();
        questionTagResponseDtos.add(questionTagResponseDto1);
        questionTagResponseDtos.add(questionTagResponseDto2);


        QuestionResponseDto response =
                new QuestionResponseDto
                        (1L, 1L, "nickName", "title title title", "problemContent problemContent problemContent",
                                "expectContent expectContent expectContent", 0,Question.QuestionStatus.QUESTION_NOTSELECT, questionTagResponseDtos,
                                LocalDateTime.now(), LocalDateTime.now(), answerResponseDtos);

        // Stubbing by Mockito
        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(mapper.questionToQuestionResponseDto(Mockito.any(Question.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform
                (RestDocumentationRequestBuilders.get("/questions/{question-id}", questionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.problemContent").value(question.getProblemContent()))
                .andExpect(jsonPath("$.expectContent").value(question.getExpectContent()))
                .andDo(document("get-question",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(parameterWithName("question-id").description("질문 식별자")),
                        responseFields(
                                List.of(
                                        //fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("problemContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("expectContent").type(JsonFieldType.STRING).description("원하는 해결 방향"),
                                        fieldWithPath("likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("questionStatus").type(JsonFieldType.STRING).description("질문 상태 : 채택된 답안이 있는 문의/ 채택된 답안이 없는 문의"),
                                        fieldWithPath("questionTags").type(JsonFieldType.ARRAY).description("질문과 관련된 태그들"),
                                        fieldWithPath("questionTags[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                        fieldWithPath("questionTags[].name").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변 리스트"),
                                        fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("answers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("answers[].nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("answers[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("answers[].answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("answers[].likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("answers[].answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("answers[].createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("answers[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")
                                )
                        )
                ));
    }

    @Test
    @WithMockUser(username = "patient@gmail.com", roles ={"USER"})
    public void getQuestionsTest() throws Exception {
        Question question1 = new Question();
        question1.setQuestionStatus(Question.QuestionStatus.QUESTION_NOTSELECT);
        question1.setQuestionId(1L);
        question1.setTitle("title title title");

        Question question2 = new Question();
        question2.setQuestionStatus(Question.QuestionStatus.QUESTION_NOTSELECT);
        question2.setQuestionId(2L);
        question2.setTitle("title title title");

        AnswerResponseDto responseDto1 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent", 0,Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        AnswerResponseDto responseDto2 =
                new AnswerResponseDto
                        (1L, 1L, "nickName", 1L, "answerContent answerContent answerContent",0, Answer.AnswerStatus.ANSWER_NOTSELECT, LocalDateTime.now(), LocalDateTime.now());

        List<AnswerResponseDto> answerResponseDtos= new ArrayList<>();
        answerResponseDtos.add(responseDto1);
        answerResponseDtos.add(responseDto2);


        QuestionTagResponseDto questionTagResponseDto1 = new QuestionTagResponseDto(1L, "java");
        QuestionTagResponseDto questionTagResponseDto2 = new QuestionTagResponseDto(2L, "python");

        List<QuestionTagResponseDto> questionTagResponseDtos = new ArrayList<>();
        questionTagResponseDtos.add(questionTagResponseDto1);
        questionTagResponseDtos.add(questionTagResponseDto2);


        QuestionResponseDto response1 =
                new QuestionResponseDto
                        (1L, 1L, "nickName", "title title title", "problemContent problemContent problemContent",
                                "expectContent expectContent expectContent", 0, Question.QuestionStatus.QUESTION_NOTSELECT, questionTagResponseDtos,
                                LocalDateTime.now(), LocalDateTime.now(), answerResponseDtos);

        QuestionResponseDto response2 =
                new QuestionResponseDto
                        (2L, 2L, "nickName2", "title title title", "problemContent problemContent problemContent",
                                "expectContent expectContent expectContent", 0,Question.QuestionStatus.QUESTION_NOTSELECT, questionTagResponseDtos,
                                LocalDateTime.now(), LocalDateTime.now(), answerResponseDtos);

        Page<Question> pageQuestions =
                new PageImpl<>(List.of(question1, question2),
                        PageRequest.of(0, 2, Sort.by("questionId").descending()), 2);
        List<QuestionResponseDto> responses = List.of(response1, response2);

        // Stubbing by Mockito
        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt())).willReturn(pageQuestions);
        given(mapper.questionsToQuestionResponseDtos(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.get("/questions")
                .param("page","1")
                .param("size","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("get-questions",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호"),
                                parameterWithName("size").description("검색 갯수")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("data[].problemContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("data[].expectContent").type(JsonFieldType.STRING).description("원하는 해결 방향"),
                                        fieldWithPath("data[].likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("data[].questionStatus").type(JsonFieldType.STRING).description("질문 상태 : 채택된 답안이 있는 문의/ 채택된 답안이 없는 문의"),
                                        fieldWithPath("data[].questionTags").type(JsonFieldType.ARRAY).description("질문과 관련된 태그들"),
                                        fieldWithPath("data[].questionTags[].tagId").type(JsonFieldType.NUMBER).description("태그 식별자"),
                                        fieldWithPath("data[].questionTags[].name").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간"),
                                        fieldWithPath("data[].answers").type(JsonFieldType.ARRAY).description("답변 리스트"),
                                        fieldWithPath("data[].answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        fieldWithPath("data[].answers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        fieldWithPath("data[].answers[].nickName").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("data[].answers[].questionId").type(JsonFieldType.NUMBER).description("질문 식별자"),
                                        fieldWithPath("data[].answers[].answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("data[].answers[].likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("data[].answers[].answerStatus").type(JsonFieldType.STRING).description("답변 상태"),
                                        fieldWithPath("data[].answers[].createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("data[].answers[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간"),
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
    public void deleteQUestionTest() throws Exception {
        // given
        long questionId = 1L;

        // Stubbing by Mockito
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        // when
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/questions/{question-id}",questionId).with(csrf()));

        // then
        actions.andExpect(status().isNoContent())
                .andDo(document("delete-question",
                        pathParameters(parameterWithName("question-id").description("질문 식별자"))));
    }
}
