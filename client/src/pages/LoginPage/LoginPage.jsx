import styled from "styled-components";
import logo from "../../img/Login/logo.png";
import googleImg from "../../img/Login/google.png";
import githubImg from "../../img/Login/github.png";
import facebookImg from "../../img/Login/facebook.png";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
  border: ${({ isError }) =>
    isError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
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
  cursor: pointer;
`;

const SignupMessage = styled.div`
  font-size: 14px;
  color: #393939;
  margin-top: 40px;
`;

const SignupLink = styled.span`
  color: rgb(0, 116, 204);
  cursor: pointer;
`;

const Error = styled.div`
  color: #d0393e;
  font-size: 12px;
  margin-bottom: 13px;
  width: 240px;
`;

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [isError, setIsError] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const emailHandler = (e) => {
    setEmail(e.target.value);
  };

  const passwordHandler = (e) => {
    setPassword(e.target.value);
  };

  const moveSignUp = () => {
    navigate("/signup");
  };

  // {
  //   headers: new Headers({
  //     "ngrok-skip-browser-warning": "69420",
  //   }),
  // }

  const loginHandler = () => {
    axios
      .post("/members/signin", {
        email,
        password,
      })
      .then((res) => res.data)
      .then((res) => {
        if (res.signIn === true) {
          window.localStorage.setItem("user", JSON.stringify(res));
          window.location.replace("/");
        } else {
          setErrorMessage("The email or password is incorrect.");
          console.log();
          setIsError(true);
        }
      });
  };

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
          <EmailForm onChange={emailHandler} isError={isError} />
          {isError ? <Error>{errorMessage}</Error> : null}
          <PasswordTextWrap>
            <Password>Password</Password>
            <FindPasswordLink>Forgot password?</FindPasswordLink>
          </PasswordTextWrap>
          <PasswordForm type="password" onChange={passwordHandler} />
          <LoginSubmit onClick={loginHandler}>Log in</LoginSubmit>
        </LoginForm>
        <SignupMessage>
          Don't have an account?
          <SignupLink onClick={moveSignUp}> Sign up</SignupLink>
        </SignupMessage>
      </LoginWrap>
    </Container>
  );
};

export default LoginPage;
