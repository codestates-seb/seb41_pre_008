import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Main = styled.main``;
const QuestionSection = styled.section`
  display: flex;
  flex-direction: column;
  margin: 1rem;
  border-bottom: 1px solid rgb(210, 212, 219);
  .icon {
    color: #b6b8bd;
    &:active {
      color: #f48225;
    }
  }
`;
const QuestionTitleSection = styled.section`
  display: flex;
  padding: 1rem;
  border-bottom: 1px solid rgb(210, 212, 219);
`;
const AskButton = styled.button`
  width: 10vw;
  height: 5vh;
  border: none;
  color: white;
  background-color: #0a95ff;
  border-radius: 5px;
  margin: 1rem;
  &:hover {
    filter: brightness(120%);
  }
`;
const QuestionTitle = styled.h1`
  display: flex;
  color: #3b4045;
  font-size: xx-large;
  line-height: 40px;
  margin-right: 1rem;
`;

const QuestionContentSection = styled.section`
  display: flex;
  padding: 1rem;
  align-items: center;
`;

const Vote = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-size: x-large;
  color: rgb(116, 117, 122);
`;

const QuestionContent = styled.p`
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 1rem;
`;

const AnswerInput = styled.input`
  display: flex;
  width: 30rem;
  height: 15rem;
  margin-left: 1rem;
`;

const AnswerTitle = styled.h2`
  padding: 1rem;
  color: #3b4045;
  font-size: x-large;
`;

const QuestionDetail = () => {
  const navigate = useNavigate();
  const [isBookMark, setIsBookMark] = useState(false);
  return (
    <Main>
      <QuestionSection>
        <QuestionTitleSection>
          <QuestionTitle>
            How to setup twilio device in Angular app for the incoming call
            (i.e. API is in .NET 6)?
          </QuestionTitle>
          <AskButton onClick={() => navigate("/questions/ask")}>
            Ask Question
          </AskButton>
        </QuestionTitleSection>
        <QuestionContentSection>
          <Vote>
            <RiArrowUpSFill className="icon" size={64} />
            <span>0</span>
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
          <QuestionContent>
            I am facing one issue related to incoming calls, so the situation is
            API is in .NET 6 and it's frontend is in Angular app and also it's
            multi-tenant application.
          </QuestionContent>
        </QuestionContentSection>
      </QuestionSection>
      <QuestionSection>
        <AnswerTitle>1 Answer</AnswerTitle>
        <QuestionContentSection>
          <Vote>
            <RiArrowUpSFill className="icon" size={64} />
            <span>1</span>
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
          <QuestionContent>test</QuestionContent>
        </QuestionContentSection>
      </QuestionSection>
      <QuestionSection>
        <AnswerTitle>YourAnswer</AnswerTitle>
        <AnswerInput />
        <AskButton type="submit" onClick={() => navigate("/questions/1")}>
          Post Your Answer
        </AskButton>
      </QuestionSection>
    </Main>
  );
};

export default QuestionDetail;
