import styled from "styled-components";
import googleImg from "../../img/Login/google.png";
import githubImg from "../../img/Login/github.png";
import facebookImg from "../../img/Login/facebook.png";
import icon1 from "../../img/Signup/icon1.png";
import icon2 from "../../img/Signup/icon2.png";
import icon3 from "../../img/Signup/icon3.png";
import icon4 from "../../img/Signup/icon4.png";

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
  height: 350px;
  background-color: white;
  border-radius: 7px;
  padding: 24px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05);
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
  border: 1px solid rgb(186, 191, 196);
  border-radius: 4px;
  font-weight: 400px;
  margin-bottom: 16px;
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
  margin-bottom: 6px;
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

const SignupPage = () => {
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
            <NameForm />
            <Email>Email</Email>
            <EmailForm />
            <Password>Password</Password>
            <PasswordForm />
            <PasswordMessage>
              Passwords must contain at least eight characters, including at
              least 1 letter and 1 number.
            </PasswordMessage>
            <SignupSubmit>Sign up</SignupSubmit>
          </SignupForm>
          <LoginMessage>
            Don't have an account?<LoginLink>Log in</LoginLink>
          </LoginMessage>
        </RightBox>
      </LoginWrap>
    </Container>
  );
};

export default SignupPage;
