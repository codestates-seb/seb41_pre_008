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
  .warning {
    margin: 0.5rem 0.1rem;
    font-size: 12px;
    color: #fe5353;
  }
  .editwarning {
    display: flex;
    padding: 0.5rem;
    border: 1px solid #fe5353;
    border-radius: 3px;
    &:focus-within {
      outline: none !important;
      border-color: #fe5353;
      box-shadow: 0 0 0 5px #ffecec;
    }
  }
  .editwarning.hide {
    display: flex;
    padding: 0.5rem;
    border: 1px solid rgb(169, 170, 178);
    border-radius: 3px;
    &:focus-within {
      outline: none !important;
      border-color: #0a95ff;
      box-shadow: 0 0 0 5px #d3ecff;
    }
  }
`;

export const EditTitle = styled.h2`
  font-weight: bold;
  margin: 0.5rem 0;
`;

// const EditInput = styled.input`
//   display: flex;
//   padding: 0.5rem;
//   border: 1px solid rgb(169, 170, 178);
//   border-radius: 3px;
//   &:focus {
//     outline: none !important;
//     border-color: #0a95ff;
//     box-shadow: 0 0 0 5px #d3ecff;
//   }
// `;

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
      <input
        id={id}
        className="editwarning hide"
        placeholder={placeholder ? placeholder : ""}
        onChange={handleChange}
      />
      {post ? "" : <div className="warning">{warningContent}</div>}
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
  const [isViewer, setIsViewer] = useState(false);
  const [title, setTitle] = useState("");
  const [titlepost, setTitlePost] = useState(true);
  const [body, setBody] = useState("");
  const [bodyPost, setBodyPost] = useState(true);
  const [summary, setSummary] = useState("");
  const [summaryPost, setSummaryPost] = useState(true);

  const onChange = () => {
    setBody(editorRef.current.getInstance().getMarkdown());
  };

  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  const handleSummaryChange = (e) => {
    setSummary(e.target.value);
  };

  const handlePost = () => {
    if (
      title.length >= 15 &&
      summary.length >= 10 &&
      editorRef.current.getInstance().getMarkdown().length >= 30
    )
      navigate("/questions/1");
    if (title.length >= 15) {
      setTitlePost(true);
      document.getElementById("title").classList.add("hide");
    }
    if (title.length < 15) {
      setTitlePost(false);
      document.getElementById("title").classList.remove("hide");
    }
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
