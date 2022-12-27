package com.codestates.pre_project.member.mapper;

import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member postToMember(MemberDto.Post post);
    Member signUpPostToMember(MemberDto.signUpPost signUp);
    Member signInPostToMember(MemberDto.signInPost signIn);
    Member patchToMember(MemberDto.Patch patch);
    MemberDto.Response memberToResponseDto(Member member);
    List<MemberDto.Response> membersToResponseDtos(List<Member> members);
}
