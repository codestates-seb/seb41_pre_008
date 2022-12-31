import React from "react";
import styled from "styled-components";
import { useState, useRef } from "react";
import { Editor, Viewer } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import AnswerList from "./Answer/AnserList";
import { MainButton, CancelButton } from "./DetailComponents/ButtonBundle";
import EditModalCard from "./EditModal/EditModal";
import Question from "./Question/Question";
import { MdError } from "react-icons/md";
import { useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

const Main = styled.main`
  display: flex;
  flex-direction: column;
  min-width: 500px;
  width: 750px;
  .question {
    border-bottom: 1px solid rgb(210, 212, 219);
  }
  .answer {
    margin: 0.5rem 1.5rem;
    .answerPostTitle {
      padding: 1rem 0.5rem;
    }
  }
  .answerTitle {
    padding: 2rem 2rem 0 2rem;
  }
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
`;

const Container = styled.section`
  display: flex;
  flex-direction: column;
  .modal.hide {
    display: none;
  }
`;

const QuestionTitleSection = styled.section`
  display: flex;
  width: 100%;
  padding: 1rem 0 1rem 2rem;
  border-bottom: 1px solid rgb(210, 212, 219);
  justify-content: space-between;
`;

const QuestionTitle = styled.h1`
  display: flex;
  color: #3b4045;
  font-size: xx-large;
  line-height: 40px;
`;

const QuestionTitleDetail = styled.p`
  font-size: 14px;
  color: rgb(116, 117, 122);
  span {
    color: black;
    padding-left: 0.5rem;
    padding-right: 1rem;
  }
`;

const AnswerTitle = styled.h2`
  display: flex;
  color: #3b4045;
  font-size: x-large;
  justify-content: space-between;
  align-items: center;
  div {
    font-size: 13px;
    select {
      width: 260px;
      height: 35px;
      padding: 0 0.3rem;
      margin-left: 0.3rem;
      border-color: rgb(169, 170, 178);
      border-radius: 3px;
      &:focus-within {
        outline: none !important;
        border: 1px solid #0a95ff;
        box-shadow: 0 0 0 4px rgba(10, 149, 255, 0.1);
      }
    }
  }
`;

const BottomButtonSection = styled.div`
  display: flex;
`;

const QuestionDetailPage = () => {
  const editorRef = useRef();
  const { questionId } = useParams();
  const [questionData, setQuestionData] = useState([]);
  const [answerData, setAnswerData] = useState([]);
  const [isViewer, setIsViewer] = useState(false);
  const [isEditModal, setIsEditModal] = useState(false);
  const [body, setBody] = useState("");
  const [bodyPost, setBodyPost] = useState(true);
  const [questionTag, setQuestionTag] = useState([]);

  // 답변 정렬 기능 구현
  useEffect(() => {
    axios
      .get(`http://3.39.203.17:8080/questions/${questionId}`)
      .then((res) => {
        setQuestionData(res.data);
        setAnswerData(res.data.answers);
        setQuestionTag(res.data.questionTags);
        if (localStorage.getItem("state") === "vote") {
          setAnswerData(res.data.answers.sort((a, b) => b.likes - a.likes));
        }
        if (localStorage.getItem("state") === "newest") {
          setAnswerData(
            res.data.answers.sort(
              (a, b) =>
                new Date(b.modifiedAt).getTime() -
                new Date(a.modifiedAt).getTime()
            )
          );
        }
        if (localStorage.getItem("state") === "oldest") {
          setAnswerData(
            res.data.answers.sort(
              (a, b) =>
                new Date(a.createdAt).getTime() -
                new Date(b.createdAt).getTime()
            )
          );
        }
      })
      .catch((err) => console.log(err));
  }, [questionId]);

  // 답변 작성 후 버튼 클릭 시 post 요청 보내는 핸들러
  const handlePost = () => {
    if (body.length >= 30) {
      setBodyPost(true);
      document.getElementById("editor").classList.add("hide");
      // 추가해야 할 내용: 서버에 post 요청 보내기
      axios
        .post("http://3.39.203.17:8080/answers", {
          memberId: JSON.parse(localStorage.getItem("user")).memberId,
          questionId: questionId,
          answerContent: body,
        })
        .then((res) => console.log(res.data))
        .catch((err) => console.log(err));
      window.location.reload();
    } else {
      setBodyPost(false);
      document.getElementById("editor").classList.remove("hide");
    }
  };

  // 답변 본문의 내용을 상태에 저장하는 핸들러
  const handleBodyChange = () => {
    setBody(editorRef.current.getInstance().getMarkdown());
  };

  // 링크 공유 모달 핸들러
  const handleHideShareModal = (e) => {
    e.stopPropagation();
    document.getElementById("modal").classList.add("hide");
  };

  // 답변 입력 시 보이는 하단 안내 문구 모달 핸들러
  const handleEditModal = () => {
    setIsEditModal(!isEditModal);
  };

  const changeValue = (e) => {
    if (e.target.value === "vote") {
      localStorage.setItem("state", "vote");
    }
    if (e.target.value === "newest") {
      localStorage.setItem("state", "newest");
    }
    if (e.target.value === "oldest") {
      localStorage.setItem("state", "oldest");
    }
    window.location.reload();
  };

  // 현재 시간과 질문한 시간, 현재 시간과 수정된 시간을 계산한 값
  const askedGap =
    new Date().getTime() - new Date(questionData.createdAt).getTime();
  const askedDate = Math.floor(Math.abs(askedGap / (1000 * 60 * 60 * 24)));
  const modifiedGap =
    new Date().getTime() - new Date(questionData.modifiedAt).getTime();
  const modifiedDate = Math.floor(
    Math.abs(modifiedGap / (1000 * 60 * 60 * 24))
  );

  return (
    <Main onClick={handleHideShareModal}>
      <Container className="question">
        <QuestionTitleSection>
          <div>
            <QuestionTitle>{questionData.title}</QuestionTitle>
            <QuestionTitleDetail>
              Asked
              <span>
                {askedDate === 0 && "today"}
                {askedDate === 1 && "yesterday"}
                {askedDate !== 0 && askedDate !== 1 && `${askedDate} days ago`}
              </span>
              Modified
              <span>
                {modifiedDate === 0 && "today"}
                {modifiedDate === 1 && "yesterday"}
                {modifiedDate !== 0 &&
                  modifiedDate !== 1 &&
                  `${modifiedDate} days ago`}
              </span>
              Viewed
              <span>0 times</span>
            </QuestionTitleDetail>
          </div>
          {window.localStorage.getItem("user") ? (
            <MainButton href="/questions/ask">Ask Question</MainButton>
          ) : (
            <MainButton href="/login">Ask Question</MainButton>
          )}
        </QuestionTitleSection>
        <Question questionData={questionData} questionTag={questionTag} />
      </Container>
      <Container>
        <AnswerTitle className="answerTitle">
          {answerData.length} Answer
          <div>
            Sorted by:
            <select
              name="answersort"
              id="body"
              onChange={changeValue}
              value={localStorage.getItem("state")}
            >
              <option value="vote">Highest score (default)</option>
              <option value="newest">Date modified (newest first)</option>
              <option value="oldest">Date created (oldest first)</option>
            </select>
          </div>
        </AnswerTitle>
        <AnswerList answerData={answerData} />
      </Container>
      <Container className="answer">
        <AnswerTitle className="answerPostTitle">Your Answer</AnswerTitle>
        <div className="body">
          <div
            id="editor"
            className="editor hide"
            onFocus={() => setIsEditModal(true)}
          >
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

        {isEditModal && <EditModalCard setIsEditModal={handleEditModal} />}
        {isViewer && <Viewer className="viewer" initialValue={body} />}
        <BottomButtonSection>
          {window.localStorage.getItem("user") ? (
            <MainButton type="submit" onClick={handlePost}>
              Post Your Answer
            </MainButton>
          ) : (
            <MainButton
              type="submit"
              onClick={() => window.location.replace("/login")}
            >
              Post Your Answer
            </MainButton>
          )}
          <CancelButton onClick={() => setIsViewer(!isViewer)}>
            {isViewer ? "Close viewer" : "Open viewer"}
          </CancelButton>
        </BottomButtonSection>
      </Container>
    </Main>
  );
};

export default QuestionDetailPage;
