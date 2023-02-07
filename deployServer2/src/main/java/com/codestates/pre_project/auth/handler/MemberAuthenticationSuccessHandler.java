package com.codestates.pre_project.auth.handler;

import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  // (1)
    private final Gson gson;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.info("# Authenticated successfully!");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        MemberDto.signInResponse signInResponse = new MemberDto.signInResponse(0, "",true);
        String succeeded = gson.toJson(signInResponse);
        response.getWriter().write(succeeded);
    }
}
