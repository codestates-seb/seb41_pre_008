import React from "react";
import styled from "styled-components";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { useRef } from "react";
import { MainButton } from "../QuestionDetailPage/DetailComponents/ButtonBundle";
import { useNavigate } from "react-router-dom";
import { Tag } from "./TestTags";

const Main = styled.main`
  display: flex;
  flex-direction: column;
  width: 750px;
`;

const EditIntroContainer = styled.section`
  display: flex;
  flex-direction: column;
  border: 1px solid #dfc257;
  border-radius: 5px;
  background-color: #fff9e3;
  line-height: 20px;
  margin: 0 2rem;
  padding: 0.8rem;
`;

const EditIntro = styled.p`
  font-size: smaller;
  color: #3b4045;
  padding: 0.5rem;
`;

export const Container = styled.section`
  display: flex;
  flex-direction: column;
  margin: 1rem 2rem;
`;

export const EditTitle = styled.h2`
  font-weight: bold;
  margin: 0.5rem 0;
`;

const EditInput = styled.input`
  display: flex;
  padding: 0.5rem;
  border: 1px solid rgb(169, 170, 178);
  border-radius: 3px;
  &:focus {
    outline: none !important;
    border-color: #0a95ff;
    box-shadow: 0 0 0 5px #d3ecff;
  }
`;

export const ButtonContainer = styled.section`
  display: flex;
  align-items: center;
  margin: 1rem 2rem;
`;

export const CancelButton = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 35px;
  border: none;
  color: #0a95ff;
  background-color: white;
  border-radius: 5px;
  margin: 1rem 0;
  &:hover {
    filter: brightness(120%);
  }
`;

export const EditCard = ({ editTitle, placeholder }) => {
  return (
    <Container>
      <EditTitle>{editTitle}</EditTitle>
      <EditInput placeholder={placeholder ? placeholder : ""} />
    </Container>
  );
};

export const EditIntroCard = () => {
  return (
    <EditIntroContainer>
      <EditIntro>
        Your edit will be placed in a queue until it is peer reviewed.
      </EditIntro>
      <EditIntro>
        We welcome edits that make the post easier to understand and more
        valuable for readers. Because community members review edits, please try
        to make the post substantially better than how you found it, for
        example, by fixing grammar or adding additional resources and
        hyperlinks..
      </EditIntro>
    </EditIntroContainer>
  );
};

const QuestionEditPage = () => {
  const editorRef = useRef();
  const navigate = useNavigate();
  return (
    <Main>
      <EditIntroCard />
      <EditCard editTitle="Title" />
      <Container>
        <EditTitle>Body</EditTitle>
        <Editor
          className="editor"
          ref={editorRef}
          initialValue=" "
          placeholder="Please enter your contents"
          initialEditType="wysiwyg"
          previewStyle="vertical"
          height="300px"
          useCommandShortcut={false}
        />
      </Container>
      <Container>
        <EditTitle>Tags</EditTitle>
        <EditInput></EditInput>
      </Container>
      <Tag />
      <EditCard
        editTitle="Edit Summary"
        placeholder="briefly explain your changes (corrected spelling, fixed grammer, improved formatting)"
      />
      <ButtonContainer>
        <MainButton onClick={() => navigate("/questions/1")}>
          Save edits
        </MainButton>
        <CancelButton onClick={() => navigate("/questions/1")}>
          Cancel
        </CancelButton>
      </ButtonContainer>
    </Main>
  );
};

export default QuestionEditPage;
