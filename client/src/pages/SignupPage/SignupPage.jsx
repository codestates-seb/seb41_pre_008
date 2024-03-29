import styled from "styled-components";
import googleImg from "../../img/Login/google.png";
import githubImg from "../../img/Login/github.png";
import facebookImg from "../../img/Login/facebook.png";
import icon1 from "../../img/Signup/icon1.png";
import icon2 from "../../img/Signup/icon2.png";
import icon3 from "../../img/Signup/icon3.png";
import icon4 from "../../img/Signup/icon4.png";
import { useEffect, useState } from "react";
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
  width: 775px;
  height: 934px;
  display: flex;
  align-items: center;
`;

const LeftBox = styled.div`
  width: 410px;
  height: 285px;
`;

const RightBox = styled.div`
  width: 316px;
  height: 934px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;

const GoogleSignupButton = styled.div`
  width: 316px;
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

const GithubSignupButton = styled.div`
  width: 316px;
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

const FaceBookSignupButton = styled.div`
  width: 316px;
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

const SignupForm = styled.div`
  width: 316px;
  height: 410px;
  background-color: white;
  border-radius: 7px;
  padding: 24px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05);
  display: flex;
  align-items: center;
  flex-direction: column;
`;

const Name = styled.div`
  width: 240px;
  font-weight: 800px;
  font-size: 15px;
  margin-bottom: 5px;
`;

const NameForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: ${({ isNameError }) =>
    isNameError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
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
  border: ${({ isEmailError }) =>
    isEmailError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
`;

const Password = styled.div`
  width: 240px;
  font-weight: 800px;
  font-size: 15px;
  margin-bottom: 5px;
`;

const PasswordForm = styled.input`
  width: 240px;
  height: 33px;
  font-size: 13px;
  padding: 7.8px 9.1px;
  border: ${({ isPwError }) =>
    isPwError ? "1px solid #d0393e" : "1px solid rgb(186, 191, 196)"};
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 10px;
`;

const PasswordMessage = styled.div`
  width: 240px;
  height: 48px;
  font-size: 12px;
  color: #4b4b4b;
  margin-bottom: 16px;
`;

const SignupSubmit = styled.div`
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
  margin-top: 6px;
  cursor: pointer;
`;

const LoginMessage = styled.div`
  font-size: 14px;
  color: #393939;
  margin-top: 40px;
  display: flex;
  justify-content: center;
`;

const LoginLink = styled.span`
  color: rgb(0, 116, 204);
  margin-left: 5px;
  cursor: pointer;
`;

const Title = styled.div`
  font-size: 32px;
  margin-bottom: 32px;
`;

const SubTitleWrap = styled.div`
  margin-bottom: 24px;
  display: flex;
  align-items: center;
`;

const SubTitle = styled.span`
  font-size: 15px;
  color: #313131;
  margin-left: 9px;
`;

const Icon = styled.img`
  width: 26px;
`;

const Desc = styled.div`
  font-size: 13px;
  color: #656565;
  margin-top: 30px;
`;

const DescLink = styled.div`
  color: #0074cc;
  margin-top: 5px;
`;

const Error = styled.div`
  color: #d0393e;
  font-size: 12px;
  margin-bottom: 13px;
  width: 240px;
`;

const SignupPage = () => {
  const [nickName, setNickName] = useState("");
  const [isNameError, setIsNameError] = useState(false);
  const [nameState, setNameState] = useState(false);

  const [email, setEmail] = useState("");
  const [isEmailError, setIsEmailError] = useState(false);
  const [emailErrorMessage, setEmailErrorMessage] = useState("");
  const [emailState, setEmailState] = useState(false);

  const [password, setPassword] = useState("");
  const [isPwError, setIsPwError] = useState(false);
  const [pwErrorMessage, setPwErrorMessage] = useState("");
  const [pwState, setPwState] = useState(false);

  const navigate = useNavigate();

  const nickNameHandler = (e) => {
    setNickName(e.target.value);
  };

  const emailHandler = (e) => {
    setEmail(e.target.value);
  };

  const passwordHandler = (e) => {
    setPassword(e.target.value);
  };

  const moveLogin = () => {
    navigate("/login");
  };

  useEffect(() => {
    if (nickName === "") {
      setIsNameError(false);
      setNameState(false);
    } else {
      setIsNameError(false);
      setNameState(true);
    }

    if (email === "") {
      setIsEmailError(false);
    } else if (
      email.match(
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
      )
    ) {
      setIsEmailError(false);
      setEmailState(true);
    } else {
      setEmailErrorMessage("Please enter the correct email format");
      setIsEmailError(true);
      setEmailState(false);
    }

    if (password === "") {
      setIsPwError(false);
    } else if (password.length >= 8 && password.match(/[a-zA-Z]+[0-9]+/)) {
      setIsPwError(false);
      setPwState(true);
    } else if (!password.match(/[0-9]+/) && password.match(/[a-zA-z]+/)) {
      setPwErrorMessage(
        "Please add one of the following things to make your password stronger: number"
      );
      setIsPwError(true);
      setPwState(false);
    } else if (password.match(/[0-9]+/) && !password.match(/[a-zA-z]+/)) {
      setPwErrorMessage(
        "Please add one of the following things to make your password stronger: letters"
      );
      setIsPwError(true);
      setPwState(false);
    } else if (password.length < 8) {
      setPwErrorMessage("Please enter at least 8 characters");
      setIsPwError(true);
      setPwState(false);
    }
  }, [nickName, email, password]);

  const signUpHandler = () => {
    if (pwState === true && emailState === true && nameState === true) {
      axios
        .post("http://3.39.203.17:8080/members/signup", {
          nickName,
          email,
          password,
        })
        .then((res) => res.data)
        .then((res) => {
          console.log(res);
          if (res.signUp === true) {
            navigate("/login");
          } else {
            console.log("이미 가입된 회원입니다.");
          }
        });
      // axios.get("/members/1").then((res) => console.log(res.data));
    }
  };

  // 1. 기본 회원가입 요청 Api 구현(백엔드) - post 요청, url 필요
  //   1) 가입 되지 않은 유저정보의 경우: id, isSignUp: true 전달 요청
  //   2) 가입 되어 있는 유저정보의 경우: id, isSignUp: false 전달 요청
  //  * 이메일 인증 기능 백엔드 구현 할것인지? 불가

  //  2. 기본 로그인 요청 Api 구현(백엔드) - post 요청, url 필요
  //   1) 가입 되지 않은 유저정보의 경우: id, isLogin: false 전달 요청
  //   2) 가입 되어 있는 유저정보의 경우: id, nickName, email, isLogin: true 전달 요청

  return (
    <Container>
      <LoginWrap>
        <LeftBox>
          <Title>Join the Stack Overflow community</Title>
          <SubTitleWrap>
            <Icon src={icon1} />
            <SubTitle>Get unstuck - ask a question</SubTitle>
          </SubTitleWrap>
          <SubTitleWrap>
            <Icon src={icon2} />
            <SubTitle>
              Unlock new privileges like voting and commenting
            </SubTitle>
          </SubTitleWrap>
          <SubTitleWrap>
            <Icon src={icon3} />
            <SubTitle>Save your favorite tags, filters, and jobs</SubTitle>
          </SubTitleWrap>
          <SubTitleWrap>
            <Icon src={icon4} />
            <SubTitle>Earn reputation and badges</SubTitle>
          </SubTitleWrap>
          <Desc>
            Collaborate and share knowledge with a private group for FREE.
            <DescLink>
              Get Stack Overflow for Teams free for up to 50 users
            </DescLink>
          </Desc>
        </LeftBox>
        <RightBox>
          <GoogleSignupButton>
            <GoogleImg src={googleImg} />
            <GoogleText>Sign up with Google</GoogleText>
          </GoogleSignupButton>
          <GithubSignupButton>
            <GitHubImg src={githubImg} />
            <GitHubText>Sign up with GitHub</GitHubText>
          </GithubSignupButton>
          <FaceBookSignupButton>
            <FaceBookImg src={facebookImg} />
            <FaceBookText>Sign up with Facebook</FaceBookText>
          </FaceBookSignupButton>
          <SignupForm>
            <Name>Display name</Name>
            <NameForm onChange={nickNameHandler} isNameError={isNameError} />
            <Email>Email</Email>
            <EmailForm onChange={emailHandler} isEmailError={isEmailError} />
            {isEmailError ? <Error>{emailErrorMessage}</Error> : null}
            <Password>Password</Password>
            <PasswordForm
              type="password"
              onChange={passwordHandler}
              isPwError={isPwError}
            />
            {isPwError ? <Error>{pwErrorMessage}</Error> : null}
            <PasswordMessage>
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number.
            </PasswordMessage>
            <SignupSubmit className="signup" onClick={signUpHandler}>
              Sign up
            </SignupSubmit>
          </SignupForm>
          <LoginMessage>
            Don't have an account?
            <LoginLink onClick={moveLogin}>Log in</LoginLink>
          </LoginMessage>
        </RightBox>
      </LoginWrap>
    </Container>
  );
};

export default SignupPage;
