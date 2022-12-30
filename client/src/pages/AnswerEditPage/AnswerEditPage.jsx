import React from "react";
import styled from "styled-components";
import { Editor, Viewer } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import { useRef, useState } from "react";
import { MainButton } from "../QuestionDetailPage/DetailComponents/ButtonBundle";
import { useNavigate, useParams } from "react-router-dom";
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
import { MdError } from "react-icons/md";
import axios from "axios";
import { useEffect } from "react";

const Main = styled.main`
  display: flex;
  flex-direction: column;
  min-width: 500px;
  max-width: 750px;
  .body {
    position: relative;
    .editor {
      /* min-width: 500px;
      max-width: 100%; */
      .toastui-editor-main-container {
        border: 1px solid #d0393e;
        &:focus-within {
          box-shadow: 0 0 0 4px rgba(208, 57, 62, 0.2);
        }
      }
    }
    .editor.hide {
      /* min-width: 500px;
      max-width: 100%; */
      .toastui-editor-main-container {
        border: none;
        &:focus-within {
          border: 1px solid #0a95ff;
          box-shadow: 0 0 0 4px rgba(10, 149, 255, 0.1);
        }
      }
    }

    .icon {
      position: absolute;
      color: #d0393e;
      font-size: 20px;
      right: 8px;
      top: 150px;
    }
  }
`;

const AnswerEditPage = () => {
  const editorRef = useRef();
  const navigate = useNavigate();
  const { answerId } = useParams();
  const [isViewer, setIsViewer] = useState(false);
  const [body, setBody] = useState("");
  const [bodyPost, setBodyPost] = useState(true);
  const [summary, setSummary] = useState("");
  const [summaryPost, setSummaryPost] = useState(true);
  console.log(answerId);

  // 본문, 요약의 내용을 각각의 상태에 저장하는 핸들러
  const handleBodyChange = () => {
    setBody(editorRef.current.getInstance().getMarkdown());
  };

  const handleSummaryChange = (e) => {
    setSummary(e.target.value);
  };

  // 버튼 클릭 시 수정된 답변 post 요청 보내는 핸들러
  const handlePost = () => {
    // 본문 길이 30, 요약 길이 10 이상일 경우에만 post 요청 가능
    if (body.length >= 30 && summary.length >= 10) {
      axios
        .patch(`http://3.39.203.17:8080/answers/${answerId}`, {
          answerId: answerId,
          answerContent: body,
          answerStatus: "ANSWER_NOTSELECT",
        })
        .catch((err) => console.log(err));
      navigate("/questions/:questionId");
      // window.location.reload();
      navigate(0);
    }
    // 나머지 경우 post 요청 대신 문구와 디자인으로 경고 표시
    if (body.length >= 30) {
      setBodyPost(true);
      document.getElementById("editor").classList.add("hide");
    }
    if (body.length < 30) {
      setBodyPost(false);
      document.getElementById("editor").classList.remove("hide");
    }
    if (summary.length >= 10) {
      setSummaryPost(true);
      document.getElementById("summary").classList.add("hide");
    }
    if (summary.length < 10) {
      setSummaryPost(false);
      document.getElementById("summary").classList.remove("hide");
    }
  };

  return (
    <Main>
      <EditIntroCard />
      <Container>
        <EditTitle>Answer</EditTitle>
        <div className="body">
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
              onChange={handleBodyChange}
              autofocus={false}
            />
          </div>
          {bodyPost ? "" : <MdError className="icon" />}
        </div>
        {bodyPost ? (
          ""
        ) : (
          <div className="warning">
            Body must be at least 30 characters; you entered {body.length}.
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
