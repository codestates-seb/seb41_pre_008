import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import { SideSeciton } from "../QuestionDetailPage";
import { useNavigate } from "react-router-dom";
import LinkModal from "../LinkModal/LinkModal";
import UserProfileCard from "../DetailComponents/UserProfileCard";
import TagCard from "../DetailComponents/TagCard";
import {
  SideButtonSection,
  SideButton,
} from "../DetailComponents/ButtonBundle";
import { Viewer } from "@toast-ui/react-editor";

const dummytags = [
  { id: 5, tag: "php" },
  { id: 6, tag: "android" },
  { id: 7, tag: "html" },
  { id: 8, tag: "jquery" },
];

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
  margin: 0.5rem 1rem;
  width: 100%;
`;

const Answer = ({ answer }) => {
  const [isBookMark, setIsBookMark] = useState(false);
  const navigate = useNavigate();
  const handleShare = (e) => {
    e.stopPropagation();
    document.getElementById(`${answer.id}`).classList.add("hide");
    console.log(document.getElementById(`${answer.id}`).classList);
  };

  const handleShowModal = (e) => {
    e.stopPropagation();
    document.getElementById(`${answer.id}`).classList.remove("hide");
    console.log(document.getElementById(`${answer.id}`).classList);
  };

  return (
    <AnswerContentSection onClick={handleShare}>
      <Vote>
        <RiArrowUpSFill className="icon" size={64} />
        <span>{answer.vote}</span>
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
        <Viewer initialValue={answer.content} />
        {/* {answer.content} */}
        <TagCard tags={dummytags} />
        <SideSeciton>
          <SideButtonSection>
            <SideButton onClick={handleShowModal}>Share</SideButton>
            <SideButton
              onClick={() =>
                navigate("/questions/:questionId/answer/edit/:answerId")
              }
            >
              Edit
            </SideButton>
            <SideButton>Delete</SideButton>
            <LinkModal modalId={answer.id} isAnswer={true} />
          </SideButtonSection>
          <UserProfileCard
            time="2"
            name="lord stock"
            reputation="992"
            src="https://i.pinimg.com/474x/d7/70/33/d7703333ad8ba85827b60fccf42f9c25.jpg"
          />
        </SideSeciton>
      </AnswerContentContainer>
    </AnswerContentSection>
  );
};

export default Answer;
