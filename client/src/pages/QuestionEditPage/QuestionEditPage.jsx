import React from "react";
import styled from "styled-components";
import { Editor, Viewer } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { useState, useRef } from "react";
import { MainButton } from "../QuestionDetailPage/DetailComponents/ButtonBundle";
import { useNavigate } from "react-router-dom";
import { CancelButton } from "../QuestionDetailPage/DetailComponents/ButtonBundle";
import TagsInput from "./TagsInput";
import { ButtonContainer } from "../QuestionDetailPage/DetailComponents/ButtonBundle";

const Main = styled.main`
  display: flex;
  flex-direction: column;
  min-width: 500px;
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
  .editor {
    &:focus-within {
      border-radius: 5px;
      border: 1px solid #0a95ff;
      box-shadow: 0 0 0 5px #d3ecff;
    }
  }
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
  const [text, setText] = useState("");
  const [isViewer, setIsViewer] = useState(false);
  const onChange = () => {
    setText(editorRef.current.getInstance().getMarkdown());
    console.log(text);
  };

  return (
    <Main>
      <EditIntroCard />
      <EditCard editTitle="Title" />
      <Container>
        <EditTitle>Body</EditTitle>
        <div className="editor">
          <Editor
            ref={editorRef}
            initialValue=" "
            initialEditType="wysiwyg"
            previewStyle="vertical"
            placeholder="Please enter your contents"
            height="300px"
            toolbarItems={[
              ["heading", "bold", "italic", "strike"],
              ["code", "codeblock"],
              ["hr", "quote"],
              ["ul", "ol", "task"],
              ["table", "image", "link"],
            ]}
            useCommandShortcut={false}
            onChange={onChange}
          />
        </div>
        <MainButton onClick={() => setIsViewer(!isViewer)}>
          {isViewer ? "Close viewer" : "Open viewer"}
        </MainButton>
        {isViewer && <Viewer initialValue={text} />}
      </Container>
      <Container>
        <EditTitle>Tags</EditTitle>
        <TagsInput />
      </Container>
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
