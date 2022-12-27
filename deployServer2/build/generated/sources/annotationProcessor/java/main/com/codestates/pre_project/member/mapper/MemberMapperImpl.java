package com.codestates.pre_project.member.mapper;

import com.codestates.pre_project.member.dto.MemberDto.Patch;
import com.codestates.pre_project.member.dto.MemberDto.Post;
import com.codestates.pre_project.member.dto.MemberDto.Response;
import com.codestates.pre_project.member.dto.MemberDto.signInPost;
import com.codestates.pre_project.member.dto.MemberDto.signUpPost;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.entity.Member.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-27T21:22:10+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 11.0.16 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member postToMember(Post post) {
        if ( post == null ) {
            return null;
        }

        Member member = new Member();

        member.setPassword( post.getPassword() );
        member.setEmail( post.getEmail() );
        member.setNickName( post.getNickName() );

        return member;
    }

    @Override
    public Member signUpPostToMember(signUpPost signUp) {
        if ( signUp == null ) {
            return null;
        }

        Member member = new Member();

        member.setPassword( signUp.getPassword() );
        member.setEmail( signUp.getEmail() );
        member.setNickName( signUp.getNickName() );

        return member;
    }

    @Override
    public Member signInPostToMember(signInPost signIn) {
        if ( signIn == null ) {
            return null;
        }

        Member member = new Member();

        member.setPassword( signIn.getPassword() );
        member.setEmail( signIn.getEmail() );

        return member;
    }

    @Override
    public Member patchToMember(Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( patch.getMemberId() );
        member.setPassword( patch.getPassword() );
        member.setEmail( patch.getEmail() );

        return member;
    }

    @Override
    public Response memberToResponseDto(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String email = null;
        String nickName = null;
        MemberStatus memberStatus = null;

        if ( member.getMemberId() != null ) {
            memberId = member.getMemberId();
        }
        email = member.getEmail();
        nickName = member.getNickName();
        memberStatus = member.getMemberStatus();

        Response response = new Response( memberId, email, nickName, memberStatus );

        return response;
    }

    @Override
    public List<Response> membersToResponseDtos(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToResponseDto( member ) );
        }

        return list;
    }
}
