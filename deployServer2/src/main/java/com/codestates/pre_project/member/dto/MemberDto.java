package com.codestates.pre_project.member.dto;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    public static class Post {

        private String nickName;

        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        @NotBlank(message = "비밀번호는 문자, 숫자 특수문자가 최소 1개 이상이고, 8~16자리 사이여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,16}$")
        private String password;
    }
    @Getter
    @AllArgsConstructor
    public static class signUpPost {

        @NotBlank
        private String nickName;

        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        @NotBlank(message = "비밀번호는 문자, 숫자 특수문자가 최소 1개 이상이고, 8~16자리 사이여야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,16}$")
        private String password;
    }

    @Getter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class signInPost {
        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,16}$", message = "비밀번호는 문자, 숫자 특수문자가 최소 1개 이상이고, 8~16자리 사이여야 합니다.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long memberId;

        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        @Pattern(regexp = "^(?=.*[a-zA-Z])((?=.*\\d)(?=.*\\W)).{8,16}$", message = "비밀번호는 문자, 숫자 특수문자가 최소 1개 이상이고, 8~16자리 사이여야 합니다.")
        private String password;

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private long memberId;
        private String email;
        private String nickName;
        private Member.MemberStatus memberStatus;

        public String getMemberStatus() {
            return memberStatus.getStatus();
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class signUpResponse {
        private long memberId;
        private boolean isSignUp;

    }
    @AllArgsConstructor
    @Getter
    @Setter
    public static class signInResponse {
        private long memberId;
        private String nickName;
        private boolean isSignIn;
    }
}
