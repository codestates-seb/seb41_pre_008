import React from "react";
import styled from "styled-components";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { useRef } from "react";

const EditIntroContainer = styled.section`
  display: flex;
  flex-direction: column;
  border: 1px solid #dfc257;
  border-radius: 5px;
  background-color: #fff9e3;
  line-height: 20px;
  margin: 1.5rem;
  padding: 1rem;
`;

const EditIntro = styled.p`
  font-size: smaller;
  color: #3b4045;
  padding: 0.5rem;
`;

const Container = styled.section`
  display: flex;
  flex-direction: column;
  margin: 1rem 2rem;
`;

const EditTitle = styled.h2`
  font-weight: bold;
  margin: 0.5rem 0;
`;

const EditInput = styled.input`
  display: flex;
  padding: 0.5rem;
  border: 1px solid rgb(169, 170, 178);
  border-radius: 5px;
`;

const EditCard = ({ editTitle, placeholder }) => {
  return (
    <Container>
      <EditTitle>{editTitle}</EditTitle>
      <EditInput placeholder={placeholder ? placeholder : ""} />
    </Container>
  );
};

const QuestionEditPage = () => {
  const editorRef = useRef();
  return (
    <main>
      <EditIntroContainer>
        <EditIntro>
          Your edit will be placed in a queue until it is peer reviewed.
        </EditIntro>
        <EditIntro>
          We welcome edits that make the post easier to understand and more
          valuable for readers. Because community members review edits, please
          try to make the post substantially better than how you found it, for
          example, by fixing grammar or adding additional resources and
          hyperlinks..
        </EditIntro>
      </EditIntroContainer>
      <EditCard editTitle="Title" />
      <Container>
        <EditTitle>Body</EditTitle>
        <Editor
          ref={editorRef}
          initialValue="Write"
          placeholder="Please enter your contents"
          height="300px"
          useCommandShortcut={false}
        />
      </Container>
      <EditCard editTitle="Tags" />
      <EditCard
        editTitle="Edit Summary"
        placeholder="briefly explain your changes (corrected spelling, fixed grammer, improved formatting)"
      />
    </main>
  );
};

export default QuestionEditPage;
