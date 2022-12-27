import React from "react";
import styled from "styled-components";
import { Editor, Viewer } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { useRef, useState } from "react";
import { MainButton } from "../QuestionDetailPage/DetailComponents/ButtonBundle";
import { useNavigate } from "react-router-dom";
import {
  Container,
  EditTitle,
  EditIntroCard,
  EditCard,
} from "../QuestionEditPage/QuestionEditPage";
import {
  CancelButton,
  ButtonContainer,
} from "../QuestionDetailPage/DetailComponents/ButtonBundle";
import { useEffect } from "react";

const Main = styled.main`
  display: flex;
  flex-direction: column;
  min-width: 500px;
  width: 750px;
  .editor {
    &:focus-within {
      border-radius: 5px;
      border: 1px solid #0a95ff;
      box-shadow: 0 0 0 5px #d3ecff;
    }
  }
`;

const AnswerEditPage = () => {
  const editorRef = useRef();
  const navigate = useNavigate();
  const [text, setText] = useState("");
  const [isViewer, setIsViewer] = useState(false);

  const onChange = () => {
    setText(editorRef.current.getInstance().getMarkdown());
    console.log(text);
  };

  const handleClick = () => {
    console.log(text);
    navigate("/questions/1");
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <Main>
      <EditIntroCard />
      <Container>
        <EditTitle>Answer</EditTitle>
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
      <EditCard
        editTitle="Edit Summary"
        placeholder="briefly explain your changes (corrected spelling, fixed grammer, improved formatting)"
      />
      <ButtonContainer>
        <MainButton onClick={handleClick}>Save edits</MainButton>
        <CancelButton onClick={() => navigate("/questions/1")}>
          Cancel
        </CancelButton>
      </ButtonContainer>
    </Main>
  );
};

export default AnswerEditPage;
