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
import { MdError } from "react-icons/md";

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
  p {
    font-size: smaller;
    color: #3b4045;
    padding: 0.5rem;
  }
`;

export const Container = styled.section`
  display: flex;
  flex-direction: column;
  margin: 1rem 2rem;

  .body {
    position: relative;
    .editor {
      width: 100%;
      .toastui-editor-main-container {
        border: 1px solid #d0393e;
        &:focus-within {
          box-shadow: 0 0 0 4px rgba(208, 57, 62, 0.2);
        }
      }
    }
    .editor.hide {
      width: 100%;
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
  .warning {
    margin: 0.5rem 0.1rem;
    font-size: 12px;
    color: #d0393e;
  }

  .test {
    position: relative;
    /* input {
      width: 100%;
    } */
    .editwarning {
      width: 100%;
      padding: 0.5rem;
      border: 1px solid #d0393e;
      border-radius: 3px;
      &:focus-within {
        outline: none !important;
        border-color: #d0393e;
        box-shadow: 0 0 0 4px rgba(208, 57, 62, 0.2);
      }
    }
    .editwarning.hide {
      width: 100%;
      padding: 0.5rem;
      border: 1px solid rgb(169, 170, 178);
      border-radius: 3px;
      &:focus-within {
        outline: none !important;
        border-color: #0a95ff;
        box-shadow: 0 0 0 4px rgba(10, 149, 255, 0.1);
      }
    }
    .icon {
      position: absolute;
      color: #d0393e;
      font-size: 20px;
      right: 8px;
      top: 7px;
    }
  }
`;

export const EditTitle = styled.h2`
  font-weight: bold;
  margin: 0.5rem 0;
`;

// 제목과 input 창이 묶여있는 카드
export const EditCard = ({
  id,
  editTitle,
  placeholder,
  handleChange,
  post,
  warningContent,
}) => {
  return (
    <Container>
      <EditTitle>{editTitle}</EditTitle>
      <div className="test">
        <input
          id={id}
          className="editwarning hide"
          placeholder={post ? placeholder : ""}
          onChange={handleChange}
        />
        {post ? "" : <MdError className="icon" />}
      </div>
      {post ? "" : <div className="warning">{warningContent}</div>}
    </Container>
  );
};

// 편집 페이지 소개 문구 카드
export const EditIntroCard = () => {
  return (
    <EditIntroContainer>
      <p>Your edit will be placed in a queue until it is peer reviewed.</p>
      <p>
        We welcome edits that make the post easier to understand and more
        valuable for readers. Because community members review edits, please try
        to make the post substantially better than how you found it, for
        example, by fixing grammar or adding additional resources and
        hyperlinks..
      </p>
    </EditIntroContainer>
  );
};

const QuestionEditPage = () => {
  const editorRef = useRef();
  const navigate = useNavigate();
  const [isViewer, setIsViewer] = useState(false);
  const [title, setTitle] = useState("");
  const [titlepost, setTitlePost] = useState(true);
  const [body, setBody] = useState("");
  const [bodyPost, setBodyPost] = useState(true);
  const [summary, setSummary] = useState("");
  const [summaryPost, setSummaryPost] = useState(true);

  // 제목, 본문, 요약의 내용을 각각의 상태에 저장하는 핸들러
  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  const handleBodyChange = () => {
    setBody(editorRef.current.getInstance().getMarkdown());
  };

  const handleSummaryChange = (e) => {
    setSummary(e.target.value);
  };

  // 버튼 클릭 시 수정된 질문 post 요청 보내는 핸들러
  const handlePost = () => {
    // 제목 길이 15, 본문 길이 30, 요약 길이 10 이상일 경우에만 post 요청 가능
    if (title.length >= 15 && body.length >= 30 && summary.length >= 10)
      navigate("/questions/1");
    // 나머지 경우 post 요청 대신 문구와 디자인으로 경고 표시
    if (title.length >= 15) {
      setTitlePost(true);
      document.getElementById("title").classList.add("hide");
    }
    if (title.length < 15) {
      setTitlePost(false);
      document.getElementById("title").classList.remove("hide");
    }
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
      <EditCard
        id="title"
        editTitle="Title"
        handleChange={handleTitleChange}
        post={titlepost}
        warningContent="Title must be at least 15 characters."
      />
      <Container>
        <EditTitle>Body</EditTitle>
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
      <Container>
        <EditTitle>Tags</EditTitle>
        <TagsInput />
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
        <CancelButton onClick={() => navigate(-1)}>Cancel</CancelButton>
      </ButtonContainer>
    </Main>
  );
};

export default QuestionEditPage;
