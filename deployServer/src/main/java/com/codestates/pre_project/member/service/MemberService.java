package com.codestates.pre_project.member.service;


import com.codestates.pre_project.exception.BusinessLogicException;
import com.codestates.pre_project.exception.ExceptionCode;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/* member의 service 계층 구현
* CRUD 기능
* */

@Transactional
@RequiredArgsConstructor // final이나 not null 인 필드의 생성자 자동생성 @
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        // 이메일이 이미 있는지 검증(아이디 중복확인)
//        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail())
//        if(optionalMember.isPresent()){
//            return optionalMember.get();
//        }
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // 회원이 있는지 먼저 검증하고
        Member findMember = findVerifiedMember(member.getMemberId());

        // Request Body에서 받은 정보로 수정하고 싶은 회원의 email, password, status를 변경
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus -> findMember.setMemberStatus(memberStatus));

        findMember.setModifiedAt(LocalDateTime.now());
        // 변경된 정보를 가진 회원을 데이터베이스에 저장(memberId가 있기 때문에 update)
        return memberRepository.save(findMember);
    }

    public Member findMember(String email) {
        // 이메일로 회원을 조회
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public Member findMember(long memberId) {
        // ID로 회원을 조회
        Optional<Member> member = memberRepository.findById(memberId);
        return member.orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }


    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        return optionalMember.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}
