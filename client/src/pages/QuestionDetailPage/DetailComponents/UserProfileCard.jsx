import React from "react";
import styled from "styled-components";

const UserProfileContainer = styled.section`
  display: flex;
  flex-direction: column;
  background-color: #d8eaf7;
  border-radius: 3px;
  width: 200px;
  height: 67px;
  padding: 0.2rem 0.5rem;
  .recent {
    color: #676c72;
    font-size: small;
  }
  .userProfile {
    display: flex;
    align-items: center;
  }
  img {
    width: 32px;
    height: 32px;
    border-radius: 3px;
  }
  .userInfo {
    display: flex;
    flex-direction: column;
    margin-left: 0.5rem;
    line-height: 15px;
    font-size: small;
  }
  .userName {
    color: #0e6eb3;
    font-weight: bold;
  }
  .userReputation {
    font-weight: bold;
  }
`;

const UserProfileCard = ({ type = false, time, name, reputation, src }) => {
  return (
    <UserProfileContainer>
      <div className="recent">
        {type === true ? "asked" : "answered"} {time} mins ago
      </div>
      <div className="userProfile">
        <img src={src} alt="profileimg" />
        <div className="userInfo">
          <span className="userName">{name}</span>
          <span className="userReputation">{reputation}</span>
        </div>
      </div>
    </UserProfileContainer>
  );
};

export default UserProfileCard;
