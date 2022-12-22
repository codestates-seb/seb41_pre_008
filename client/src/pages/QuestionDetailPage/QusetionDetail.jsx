import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import AnswerList from "./Answer/AnserList";

const data = [
  {
    id: 1,
    content: "dfsfsdfsdfsdff",
    vote: 1,
    bookmark: true,
    tag: "gogo",
    user: "TaTa",
  },
  {
    id: 2,
    content: "wefweffgegewgwgeg",
    vote: 3,
    bookmark: false,
    tag: "gogo",
    user: "TaTa",
  },
  {
    id: 3,
    content: "asdggdffherhdfbfdbdf",
    vote: 2,
    bookmark: false,
    tag: "gogo",
    user: "TaTa",
  },
  {
    id: 4,
    content: "asdggdffherhdfbfdbdf",
    vote: 0,
    bookmark: true,
    tag: "gogo",
    user: "TaTa",
  },
];

const Main = styled.main``;
const Container = styled.section`
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

const QuestionTitleDetail = styled.p`
  color: rgb(116, 117, 122);
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

const QuestionContent = styled.div`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 1rem;
`;

export const SideButtonSection = styled.div`
  display: flex;
  margin-top: 1rem;
`;

export const SideButton = styled.button`
  border: none;
  color: rgb(116, 117, 122);
  background: white;
  /* border: 1px solid black; */
  &:hover {
    color: #3b4045;
  }
`;

const AnswerTitle = styled.h2`
  padding: 1rem;
  color: #3b4045;
  font-size: x-large;
`;

const QuestionDetail = () => {
  const navigate = useNavigate();
  const [isBookMark, setIsBookMark] = useState(false);
  const handlePostAnswer = () => {
    // 추가해야 할 내용: 서버에 post 요청 보내기
    console.log(editorRef.current.getInstance().getHTML());
    window.scrollTo(0, 0);
    navigate("/questions/1");
  };
  const editorRef = useRef();

  return (
    <Main>
      <Container>
        <QuestionTitleSection>
          <div>
            <QuestionTitle>
              How to setup twilio device in Angular app for the incoming call
              (i.e. API is in .NET 6)?
            </QuestionTitle>
            <QuestionTitleDetail>Asked: Modified: Viewed:</QuestionTitleDetail>
          </div>
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
            <SideButtonSection>
              <SideButton>Share</SideButton>
              <SideButton>Edit</SideButton>
              <SideButton>Delete</SideButton>
            </SideButtonSection>
          </QuestionContent>
        </QuestionContentSection>
      </Container>
      <Container>
        <AnswerTitle>{data.length} Answer</AnswerTitle>
        <AnswerList data={data} />
      </Container>
      <Container>
        <AnswerTitle>Your Answer</AnswerTitle>
        <Editor
          ref={editorRef}
          initialValue="Write"
          placeholder="Please enter your contents"
          height="300px"
          useCommandShortcut={false}
        />
        <AskButton type="submit" onClick={handlePostAnswer}>
          Post Your Answer
        </AskButton>
      </Container>
    </Main>
  );
};

export default QuestionDetail;
