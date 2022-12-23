import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import {
  SideButton,
  SideButtonSection,
  UserProgileCard,
  SideSeciton,
} from "../QusetionDetail";
import { useNavigate } from "react-router-dom";

const AnswerContentSection = styled.section`
  display: flex;
  padding: 1rem;
  align-items: flex-start;
  border-bottom: 1px solid rgb(210, 212, 219);
`;

const Vote = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-size: x-large;
  color: rgb(116, 117, 122);
`;

const AnswerContentContainer = styled.section`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 1rem;
  width: 100%;
`;

const Answer = ({ answers }) => {
  const [isBookMark, setIsBookMark] = useState(false);
  const navigate = useNavigate();
  return (
    <AnswerContentSection>
      <Vote>
        <RiArrowUpSFill color="#b6b8bd" size={64} />
        <span>{answers.vote}</span>
        <RiArrowDownSFill color="#b6b8bd" size={64} />
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
        {answers.content}
        <SideSeciton>
          <SideButtonSection>
            <SideButton>Share</SideButton>
            <SideButton onClick={() => navigate("/answer/edit")}>
              Edit
            </SideButton>
            <SideButton>Delete</SideButton>
          </SideButtonSection>
          <UserProgileCard
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
