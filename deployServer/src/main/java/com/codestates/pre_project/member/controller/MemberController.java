package com.codestates.pre_project.member.controller;

import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.dto.MultiResponseDto;
import com.codestates.pre_project.member.dto.SingleResponseDto;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.mapper.MemberMapper;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;


/**
 * api 요청을 받아들이는 역할을 하는 controller
 * 8080 포트에 기본적으로 연결됨
 * signup : 회원가입
 * signin : 로그인
 * get : 회원 조회
 * patch : 회원 수정
 * delete : 회원 삭제
 * e.g) [get] {호스트주소}:8080/members/1 | Id가 1인 회원 조회
 * e.g) [get]{호스트주소}:8080/members | 모든 회원 조회
 * e.g) [post]{호스트주소}:8080/members/signup | 회원가입
 * e.g) [post]{호스트주소}:8080/members/signin | 로그인
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin
@Validated
@Slf4j
public class MemberController {

    private final MemberMapper mapper;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

/*    // 회원가입 핸들러 메서드 - memberId, nickName, email을 body로 응답하는 responseDto("/members/signup")
    @PostMapping("/signup")
    public ResponseEntity signUpMember(@Valid @RequestBody MemberDto.Post post){
        Member member = mapper.postToMember(post);
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(mapper.memberToResponseDto(createdMember), HttpStatus.CREATED);
    }*/

    // 회원가입 핸들러 메서드 - id,isSignUp을 body로 응답하는 responseDto("/members/signup")
    @PostMapping("/signup")
    public ResponseEntity signUpMember(@Valid @RequestBody MemberDto.Post post) {

        Member member = mapper.postToMember(post);

        // 데이터베이스에서 email로 회원 조회
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());

        // 이미 있는 회원이라면
        if (optionalMember.isPresent()){
            Member member1 = optionalMember.get();
            MemberDto.signUpResponse upResponse = new MemberDto.signUpResponse(member1.getMemberId(), false);
            // isSignUp에 false를 담아 보낸다.
            return new ResponseEntity<>(upResponse, HttpStatus.OK);
        }

        // 새로운 회원이라면 회원 정보를 데이터베이스에 저장하고
        Member created = memberService.createMember(member);
        // isSignUp에 true를 담아서 보낸다.
        MemberDto.signUpResponse upResponse2 = new MemberDto.signUpResponse(created.getMemberId(), true);
        return new ResponseEntity<>(upResponse2, HttpStatus.CREATED);
    }

/*    // 로그인 핸들러 메서드 - memberId, nickName, email을 body로 응답하는 responseDto("/members/signup")
    @PostMapping("/signin")
    public ResponseEntity signInMember(@Valid @RequestBody MemberDto.Post post) {
        Member member = mapper.postToMember(post);
        Member foundMember = memberService.findMember(member.getEmail());

        return new ResponseEntity(mapper.memberToResponseDto(foundMember), HttpStatus.OK);
    }*/

    // 로그인 핸들러 메서드 - id,email, isSignIn을 body로 응답하는 responseDto("/members/signin")
    @PostMapping("/signin")
    public ResponseEntity signInMember(@Valid @RequestBody MemberDto.Post post) {

        // entity 클래스로 변환
        Member member = mapper.postToMember(post);

        // 데이터베이스에서 email로 회원 조회
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());

        // 이미 있는 회원이라면
        if (optionalMember.isPresent()){
            Member alreadyExists = optionalMember.get();
            MemberDto.signInResponse inResponse = new MemberDto.signInResponse(alreadyExists.getMemberId(), alreadyExists.getNickName(), alreadyExists.getEmail(), true);
            // isSignIn에 true를 담아 보낸다.
            return new ResponseEntity<>(inResponse, HttpStatus.OK);
        }

        // 새로운 회원이라면
        // isSignIn에 false를 담아서 보낸다.
        MemberDto.signInResponse inResponse2 = new MemberDto.signInResponse(0, null, null, false);
        return new ResponseEntity<>(inResponse2, HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(
            @PathVariable("member-id") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch patch) {
        patch.setMemberId(memberId);
        Member member =
                memberService.updateMember(mapper.patchToMember(patch));
        return new ResponseEntity<>(mapper.memberToResponseDto(member),
                HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(mapper.memberToResponseDto(member), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> memberPage = memberService.findMembers(page-1, size);
        List<Member> members = memberPage.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToResponseDtos(members), memberPage),
                HttpStatus.OK);

    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
