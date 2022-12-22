import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import { SideButton, SideButtonSection } from "../QusetionDetail";

const AnswerContentSection = styled.section`
  display: flex;
  padding: 1rem;
  align-items: center;
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

const AnswerContent = styled.div`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 1rem;
`;

const Answer = ({ answers }) => {
  const [isBookMark, setIsBookMark] = useState(false);
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
      <AnswerContent>
        {answers.content}
        <SideButtonSection>
          <SideButton>Share</SideButton>
          <SideButton>Edit</SideButton>
          <SideButton>Delete</SideButton>
        </SideButtonSection>
      </AnswerContent>
    </AnswerContentSection>
  );
};

export default Answer;
