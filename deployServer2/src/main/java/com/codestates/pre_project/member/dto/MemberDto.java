package com.codestates.pre_project.member.dto;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.validator.NotSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        @Pattern(regexp = "[a-zA-Z]+[0-9]+", message = "비밀번호는 문자와 숫자 1개 이상을 포함해야 합니다.")
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
        @Pattern(regexp = "[a-zA-Z]+[0-9]+", message = "비밀번호는 문자와 숫자 1개 이상을 포함해야 합니다.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class signInPost {
        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        @Pattern(regexp = "[a-zA-Z]+[0-9]+", message = "비밀번호는 문자와 숫자 1개 이상을 포함해야 합니다.")
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
        @Pattern(regexp = "[a-zA-Z]+[0-9]+", message = "비밀번호는 문자와 숫자 1개 이상을 포함해야 합니다.")
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
