package com.codestates.pre_project.member;

import com.codestates.pre_project.member.controller.MemberController;
import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.mapper.MemberMapper;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.member.service.MemberService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

import static com.codestates.pre_project.util.ApiDocumentUtils.getDocumentRequest;
import static com.codestates.pre_project.util.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void signUpMemberTest() throws Exception{

        MemberDto.signUpPost post = new MemberDto.signUpPost("Doctor","patient@gmail.com","healme");

        String content = gson.toJson(post);

        MemberDto.signUpResponse response = new MemberDto.signUpResponse(1L,false);

        given(mapper.signUpPostToMember(Mockito.any(MemberDto.signUpPost.class))).willReturn(new Member());
        Member mockResultMember = new Member();
        mockResultMember.setMemberId(1L);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(mockResultMember);
        given(memberRepository.findByEmail(Mockito.any(String.class))).willReturn(Optional.of(new Member()));

        ResultActions actions =
                mockMvc.perform(
                        post("/members/signup")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("memberId").value(response.getMemberId()))
                .andDo(document(
                        "signup-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일 주소"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                                        fieldWithPath("signUp").type(JsonFieldType.BOOLEAN).description("회원가입 성공 여부")
                                )
                        )
                ));
    }

    @Test
    public void signInMemberTest() throws Exception{

        MemberDto.signInPost post = new MemberDto.signInPost("patient@gmail.com","healme");

        String content = gson.toJson(post);

        MemberDto.signInResponse response = new MemberDto.signInResponse(1L,"patient@gmail.com",true);

        given(mapper.signInPostToMember(Mockito.any(MemberDto.signInPost.class))).willReturn(new Member());
        Member mockResultMember = new Member();
        mockResultMember.setMemberId(1L);
        mockResultMember.setNickName("milhouse");
//        given(memberService.createMember(Mockito.any(Member.class))).willReturn(mockResultMember);
        given(memberRepository.findByEmail(Mockito.any(String.class))).willReturn(Optional.of(mockResultMember));

        ResultActions actions =
                mockMvc.perform(
                        post("/members/signin")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("memberId").value(0))
                .andDo(document(
                        "signin-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일 주소"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                                        fieldWithPath("nickName").type(JsonFieldType.NULL).description("회원 닉네임"),
                                        fieldWithPath("signIn").type(JsonFieldType.BOOLEAN).description("로그인 성공 여부")
                                )
                        )
                ));
    }

    @Test
    public void patchMemberTest() throws Exception{
        long memberId = 1L;
        MemberDto.Patch patch = new MemberDto.Patch(memberId,"patient@gmail.com","healme");
        String content = gson.toJson(patch);

        MemberDto.Response response =
                new MemberDto.Response(1L,
                        "patient@gmail.com","Brown", Member.MemberStatus.MEMBER_ACTIVE);
        given(mapper.patchToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());
        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());
        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        patch("/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("memberId").value(response.getMemberId()))
                .andExpect(jsonPath("email").value(response.getEmail()))
                .andExpect(jsonPath("nickName").value(response.getNickName()))
                .andExpect(jsonPath("memberStatus").value(response.getMemberStatus()))
                .andDo(document(
                        "patch-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 ID")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("memberStatus").type(JsonFieldType.STRING).description("회원 상태")
                                )
                        )

                ));
    }

    @Test
    public void getMemberTest() throws Exception{
        long memberId = 1L;
        Member member = new Member();
        member.setMemberId(memberId);
        member.setNickName("Doctor");
        member.setEmail("patient@gmail.com");
        member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        MemberDto.Response response = new MemberDto.Response(1L,"patient@gmail.com","Doctor",Member.MemberStatus.MEMBER_ACTIVE);

        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());

        given(mapper.memberToResponseDto(Mockito.any(Member.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders.get("/members/{member-id}", memberId)
                );

        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("memberId").value(response.getMemberId()))
                .andExpect(jsonPath("email").value(response.getEmail()))
                .andExpect(jsonPath("nickName").value(response.getNickName()))
                .andExpect(jsonPath("memberStatus").value(response.getMemberStatus()))
                .andDo(document(
                        "get-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 ID")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("memberStatus").type(JsonFieldType.STRING).description("회원 상태")
                                )
                        )
                ));
    }

    @Test
    public void getMembersTest() throws Exception{

        Member member1 = new Member();
        member1.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        member1.setEmail("patient6@gmail.com");
        member1.setNickName("Doctor6");
        member1.setMemberId(6L);

        Member member2 = new Member();
        member2.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
        member2.setEmail("patient7@gmail.com");
        member2.setNickName("Doctor7");
        member2.setMemberId(7L);


        //given 테스트 데이터 생성
        List<MemberDto.Response> memberList = List.of(
                new MemberDto.Response(1L, "patient1@gmail.com", "Doctor1", Member.MemberStatus.MEMBER_ACTIVE),
                new MemberDto.Response(2L,"patient2@gmail.com", "Doctor2", Member.MemberStatus.MEMBER_ACTIVE),
                new MemberDto.Response(3L,"patient3@gmail.com", "Doctor3", Member.MemberStatus.MEMBER_ACTIVE),
                new MemberDto.Response(4L,"patient4@gmail.com", "Doctor4", Member.MemberStatus.MEMBER_ACTIVE),
                new MemberDto.Response(5L, "patient5@gmail.com", "Doctor5", Member.MemberStatus.MEMBER_ACTIVE)
        );
        Page<Member> members = new PageImpl<>(List.of(member1,member2), PageRequest.of(0,5, Sort.by("memberId").descending()),2);


        //mockmvc 동작 설정
        given(memberService.findMembers(Mockito.anyInt(),Mockito.anyInt())).willReturn(members);
        given(mapper.membersToResponseDtos(Mockito.anyList())).willReturn(memberList);


        String page = "1";
        String size = "5";
        MultiValueMap<String, String> queryParam = new LinkedMultiValueMap<>();
        queryParam.add("page", page);
        queryParam.add("size", size);

        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders
                                .get("/members")
                                .params(queryParam)
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "get-members",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                List.of(parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("사이즈"))
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 ID"),
                                        fieldWithPath("data[].email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("data[].nickName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                        fieldWithPath("data[].memberStatus").type(JsonFieldType.STRING).description("회원 상태: 활동중 / 휴면 상태 / 탈퇴 상태"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("현재 페이지 요소 개수"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 요소의 개수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )
                ));
    }

    @Test
    public void deleteMemberTest() throws Exception{
        long memberId = 1L;

        //deleteMember가 아무것도 돌려주지 않아도 HttpStatus.NO_CONTENT를 반환한다.
        doNothing().when(memberService).deleteMember(Mockito.anyLong());
        ResultActions actions =
                mockMvc.perform(
                        RestDocumentationRequestBuilders
                                .delete("/members/{member-id}", memberId)
                                .accept(MediaType.APPLICATION_JSON)
                );

        actions
                .andExpect(status().isNoContent())
                .andDo(document(
                        "delete-member",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("member-id").description("회원 ID")
                        )
                ));
    }
}

