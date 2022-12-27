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
    border: 1px solid #fe5353;
    border-radius: 5px;
    &:focus-within {
      box-shadow: 0 0 0 5px #ffecec;
    }
  }
  .editor.hide {
    border: none;
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
  const [isViewer, setIsViewer] = useState(false);
  const [body, setBody] = useState("");
  const [bodyPost, setBodyPost] = useState(true);
  const [summary, setSummary] = useState("");
  const [summaryPost, setSummaryPost] = useState(true);

  const onChange = () => {
    setBody(editorRef.current.getInstance().getMarkdown());
    console.log(body);
  };

  const handleSummaryChange = (e) => {
    setSummary(e.target.value);
  };

  const handlePost = () => {
    if (
      summary.length >= 10 &&
      editorRef.current.getInstance().getMarkdown().length >= 30
    )
      navigate("/questions/1");
    if (summary.length >= 10) {
      setSummaryPost(true);
      document.getElementById("summary").classList.add("hide");
    }
    if (summary.length < 10) {
      setSummaryPost(false);
      document.getElementById("summary").classList.remove("hide");
    }
    if (editorRef.current.getInstance().getMarkdown().length >= 30) {
      setBodyPost(true);
      document.getElementById("editor").classList.add("hide");
    }
    if (editorRef.current.getInstance().getMarkdown().length < 30) {
      setBodyPost(false);
      document.getElementById("editor").classList.remove("hide");
    }
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <Main>
      <EditIntroCard />
      <Container>
        <EditTitle>Answer</EditTitle>
        <div id="editor" className="editor hide">
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
            autofocus={false}
          />
        </div>
        {bodyPost ? (
          ""
        ) : (
          <div className="warning">
            Body must be at least 30 characters; you entered{" "}
            {editorRef.current.getInstance().getMarkdown().length}.
          </div>
        )}
        <MainButton onClick={() => setIsViewer(!isViewer)}>
          {isViewer ? "Close viewer" : "Open viewer"}
        </MainButton>
        {isViewer && <Viewer initialValue={body} />}
      </Container>
      <EditCard
        id="summary"
        editTitle="Edit Summary"
        placeholder="briefly explain your changes (corrected spelling, fixed grammer, improved formatting)"
        handleChange={handleSummaryChange}
        post={summaryPost}
        warningContent="Your edit summary must be at least 10 characters."
      />
      <ButtonContainer>
        <MainButton onClick={handlePost}>Save edits</MainButton>
        <CancelButton onClick={() => navigate("/questions/1")}>
          Cancel
        </CancelButton>
      </ButtonContainer>
    </Main>
  );
};

export default AnswerEditPage;
