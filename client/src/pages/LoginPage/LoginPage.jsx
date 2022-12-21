import styled from "styled-components";
import logo from "../../img/logo.png";
import googleImg from "../../img/google.png";
import githubImg from "../../img/github.png";
import facebookImg from "../../img/facebook.png";

const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f1f2f3;
`;

const LoginWrap = styled.div`
  width: 290px;
  height: 570px;
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const Logo = styled.img`
  width: 36px;
  margin-bottom: 24px;
`;

const GoogleLoginButton = styled.div`
  width: 288px;
  height: 38px;
  border-radius: 5px;
  border: 1px solid rgb(214, 217, 220);
  padding: 10.4px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 8px;
  background-color: white;
`;
const GoogleImg = styled.img`
  width: 18px;
`;

const GoogleText = styled.span`
  margin-left: 4px;
  margin-top: 2px;
  font-size: 14px;
`;

const GithubLoginButton = styled.div`
  width: 288px;
  height: 38px;
  border-radius: 5px;
  background-color: rgb(47, 51, 55);
  padding: 10.4px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  margin-bottom: 8px;
`;

const GitHubImg = styled.img`
  width: 18px;
`;

const GitHubText = styled.span`
  margin-left: 4px;
  margin-top: 2px;
  font-size: 14px;
`;

const FaceBookLoginButton = styled.div`
  width: 288px;
  height: 38px;
  border-radius: 5px;
  background-color: rgb(56, 84, 153);
  padding: 10.4px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  margin-bottom: 16px;
`;

const FaceBookImg = styled.img`
  width: 18px;
`;

const FaceBookText = styled.span`
  margin-left: 4px;
  margin-top: 2px;
  font-size: 14px;
`;

const LoginForm = styled.div`
  width: 288px;
  height: 236px;
  background-color: white;
  border-radius: 7px;
  padding: 24px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05);
`;

const Email = styled.div`
  width: 240px;
  font-weight: 800px;
  font-size: 15px;
  margin-bottom: 5px;
`;

const EmailForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 16px;
`;

const Password = styled.div`
  font-weight: 800px;
  font-size: 15px;
`;

const PasswordForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 16px;
`;

const PasswordTextWrap = styled.div`
  width: 240px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
`;

const FindPasswordLink = styled.div`
  color: rgb(0, 116, 204);
  font-size: 12px;
`;

const LoginSubmit = styled.div`
  padding: 10.4px;
  width: 240px;
  height: 38px;
  background-color: rgb(10, 149, 255);
  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  font-size: 13px;
`;

const SignupMessage = styled.div`
  font-size: 14px;
  color: #393939;
  margin-top: 40px;
`;

const SignupLink = styled.span`
  color: rgb(0, 116, 204);
`;

const LoginPage = () => {
  return (
    <Container>
      <LoginWrap>
        <Logo src={logo} />
        <GoogleLoginButton>
          <GoogleImg src={googleImg} />
          <GoogleText>Log in with Google</GoogleText>
        </GoogleLoginButton>
        <GithubLoginButton>
          <GitHubImg src={githubImg} />
          <GitHubText>Log in with GitHub</GitHubText>
        </GithubLoginButton>
        <FaceBookLoginButton>
          <FaceBookImg src={facebookImg} />
          <FaceBookText>Log in with Facebook</FaceBookText>
        </FaceBookLoginButton>
        <LoginForm>
          <Email>Email</Email>
          <EmailForm />
          <PasswordTextWrap>
            <Password>Password</Password>
            <FindPasswordLink>Forgot password?</FindPasswordLink>
          </PasswordTextWrap>
          <PasswordForm />
          <LoginSubmit>Log in</LoginSubmit>
        </LoginForm>
        <SignupMessage>
          Don't have an account?<SignupLink> Sign up</SignupLink>
        </SignupMessage>
      </LoginWrap>
    </Container>
  );
};

export default LoginPage;
