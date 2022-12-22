import React from "react";
import styled from "styled-components";
import { RiPencilFill } from "react-icons/ri";

const Main = styled.main`
  display: flex;
  flex-direction: column;
`;

const ProfileSection = styled.section`
  display: flex;
  margin: 1rem;
  padding: 1rem;
  /* align-items: center; */
`;

const ProfileImg = styled.img`
  width: 150px;
  height: 150px;
  border-radius: 8px;
  box-shadow: 1px 1px 5px;
`;

const Introduction = styled.div`
  display: flex;
  width: 80%;
  flex-direction: column;
  justify-content: center;
  margin: 1rem;
`;

const ProfileIntro = styled.h1`
  font-size: xx-large;
  padding-bottom: 1rem;
`;

const ProfileIntroDetail = styled.p`
  color: #3b4045;
`;

const ProfileEditButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  border: none;
  border: 1px solid #b6b8bd;
  width: 150px;
  height: 40px;
  padding: 0.7rem 1rem;
  border-radius: 5px;
  background-color: white;
  color: #3b4045;
  &:hover {
    filter: brightness(90%);
  }
`;

const ProfileDetailSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 0 1rem;
`;

const ProfileDetailTitle = styled.h2`
  margin: 1rem;
  font-size: x-large;
`;

const ProfileBox = styled.section`
  display: flex;
`;

const ProfileDetailBox = styled.div`
  width: ${(props) => (props.width ? props.width : "auto")};
  height: ${(props) => (props.height ? props.height : "auto")};
  border: 1px solid #b6b8bd;
  margin: 1rem;
  padding: 1rem;
  border-radius: 5px;
`;

const ButtonBox = styled.div`
  display: flex;
`;

const Button = styled.button`
  border: none;
  border-radius: 20px;
  color: #3b4045;
  padding: 0.5rem 1rem;
  margin: 0 0 0 2rem;
  background-color: white;
  &:hover {
    color: white;
    background-color: #f48225;
  }
`;

const Profile = () => {
  return (
    <Main>
      <ProfileSection>
        <ProfileImg src="https://images.unsplash.com/photo-1544967082-d9d25d867d66?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1160&q=80" />
        <Introduction>
          <ProfileIntro>NeNaD</ProfileIntro>
          <ProfileIntroDetail>
            Member for 3 days Last seen this week Visited 3 days, 3 consecutive
          </ProfileIntroDetail>
        </Introduction>

        <ProfileEditButton>
          <RiPencilFill size={15} />
          Edit Profile
        </ProfileEditButton>
      </ProfileSection>
      <ButtonBox>
        <Button>Profile</Button>
        <Button>Active</Button>
      </ButtonBox>
      <ProfileDetailSection>
        <ProfileDetailTitle>Summary</ProfileDetailTitle>
        <ProfileBox>
          <ProfileDetailBox width="45%" height="300px"></ProfileDetailBox>
          <ProfileDetailBox width="30%" height="300px"></ProfileDetailBox>
          <ProfileDetailBox width="25%" height="300px"></ProfileDetailBox>
        </ProfileBox>
      </ProfileDetailSection>
      <ProfileDetailSection>
        <ProfileDetailTitle>Answers</ProfileDetailTitle>
        <ProfileBox>
          <ProfileDetailBox width="100%" height="100px"></ProfileDetailBox>
        </ProfileBox>
      </ProfileDetailSection>
      <ProfileDetailSection>
        <ProfileDetailTitle>Questions</ProfileDetailTitle>
        <ProfileBox>
          <ProfileDetailBox width="100%" height="100px"></ProfileDetailBox>
        </ProfileBox>
      </ProfileDetailSection>
      <ProfileDetailSection>
        <ProfileDetailTitle>Tags</ProfileDetailTitle>
        <ProfileBox>
          <ProfileDetailBox width="100%" height="100px"></ProfileDetailBox>
        </ProfileBox>
      </ProfileDetailSection>
    </Main>
  );
};

export default Profile;
