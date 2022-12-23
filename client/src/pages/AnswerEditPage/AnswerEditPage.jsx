import React from "react";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { useRef, useState } from "react";
import { MainButton } from "../QuestionDetailPage/QusetionDetail";
import { useNavigate } from "react-router-dom";
import {
  Container,
  EditTitle,
  ButtonContainer,
  EditIntroCard,
  EditCard,
  CancelButton,
} from "../QuestionEditPage/QuestionEditPage";
import { useEffect } from "react";

const AnswerEditPage = () => {
  const editorRef = useRef();
  const navigate = useNavigate();
  const [text, setText] = useState("");
  const onChange = () => {
    const data = editorRef.current.getInstance().getHTML();
    setText(data);
  };
  const handleClick = () => {
    console.log(text);
    navigate("/questions/1");
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <main>
      <EditIntroCard />
      <Container>
        <EditTitle>Answer</EditTitle>
        <Editor
          ref={editorRef}
          initialValue=" "
          initialEditType="wysiwyg"
          previewStyle="vertical"
          placeholder="Please enter your contents"
          height="300px"
          useCommandShortcut={false}
          onChange={onChange}
        />
        {/* <div dangerouslySetInnerHTML={{ __html: text }}></div> */}
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
    </main>
  );
};

export default AnswerEditPage;
