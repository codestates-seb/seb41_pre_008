import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import { SideSeciton } from "../Question/Question";
import { useNavigate } from "react-router-dom";
import LinkModal from "../LinkModal/LinkModal";
import UserProfileCard from "../DetailComponents/UserProfileCard";
import {
  SideButtonSection,
  SideButton,
} from "../DetailComponents/ButtonBundle";
import { Viewer } from "@toast-ui/react-editor";
import axios from "axios";

const AnswerContentSection = styled.section`
  display: flex;
  padding: 1rem 0 1rem 1rem;
  align-items: flex-start;
  border-bottom: 1px solid rgb(210, 212, 219);
`;

const Vote = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: x-large;
  color: rgb(116, 117, 122);
  .icon {
    color: #b6b8bd;
    &:active {
      color: #f48225;
    }
  }
`;

const AnswerContentContainer = styled.section`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 0;
  width: 100%;
  justify-content: space-between;
`;

const Answer = ({ answer }) => {
  const [isBookMark, setIsBookMark] = useState(false);
  const navigate = useNavigate();
  // let createObjDate = new Date(
  //   `${answer.createdAt.substr(0, 4)}-${answer.createdAt.substr(
  //     5,
  //     2
  //   )}-${answer.createdAt.substr(8, 11)}`
  // );
  // console.log(createObjDate);
  // const createDate = createObjDate.toString();
  // const time = ``

  // 링크 공유 모달 핸들러
  const handleHideShareModal = (e) => {
    e.stopPropagation();
    document.getElementById(`${answer.answerId}`).classList.add("hide");
  };

  const handleShowShareModal = (e) => {
    e.stopPropagation();
    document.getElementById(`${answer.answerId}`).classList.remove("hide");
  };

  const handleDelete = () => {
    axios
      .delete(`http://3.39.203.17:8080/answers/${answer.answerId}`)
      .then(() => {
        window.location.reload();
      })
      .catch((err) => console.log(err));
  };

  return (
    <AnswerContentSection onClick={handleHideShareModal}>
      <Vote>
        <RiArrowUpSFill className="icon" size={64} />
        <span>{answer.likes}</span>
        <RiArrowDownSFill className="icon" size={64} />
        {isBookMark ? (
          <FaBookmark
            size={16}
            color="#F48225"
            onClick={() => setIsBookMark(!isBookMark)}
          />
        ) : (
          <FaRegBookmark
            size={16}
            color="#b6b8bd"
            onClick={() => setIsBookMark(!isBookMark)}
          />
        )}
      </Vote>
      <AnswerContentContainer>
        <Viewer initialValue={answer.answerContent} />
        {/* {answer.answerContent} */}
        <SideSeciton>
          <SideButtonSection>
            <SideButton onClick={handleShowShareModal}>Share</SideButton>
            {window.localStorage.getItem("user") ? (
              <SideButton
                onClick={() =>
                  navigate(
                    `/questions/:questionId/answer/edit/${answer.answerId}`
                  )
                }
              >
                Edit
              </SideButton>
            ) : (
              <SideButton onClick={() => window.location.replace("/login")}>
                Edit
              </SideButton>
            )}
            <SideButton onClick={handleDelete}>Delete</SideButton>
            <LinkModal modalId={answer.answerId} isAnswer={true} />
          </SideButtonSection>
          <UserProfileCard
            time="2"
            name={answer.nickName}
            reputation="992"
            src="https://i.pinimg.com/474x/d7/70/33/d7703333ad8ba85827b60fccf42f9c25.jpg"
          />
        </SideSeciton>
      </AnswerContentContainer>
    </AnswerContentSection>
  );
};

export default Answer;
