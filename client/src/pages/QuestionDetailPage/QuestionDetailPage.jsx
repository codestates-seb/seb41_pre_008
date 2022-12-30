import React from "react";
import styled from "styled-components";
// import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
// import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState, useRef } from "react";
// import { useNavigate } from "react-router-dom";
import { Editor, Viewer } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import AnswerList from "./Answer/AnserList";
// import LinkModal from "./LinkModal/LinkModal";
// import UserProfileCard from "./DetailComponents/UserProfileCard";
// import TagCard from "./DetailComponents/TagCard";
import {
  MainButton,
  // SideButtonSection,
  // SideButton,
  CancelButton,
} from "./DetailComponents/ButtonBundle";
import EditModalCard from "./EditModal/EditModal";
import Question from "./Question/Question";
import { MdError } from "react-icons/md";

import { useEffect } from "react";

import axios from "axios";

// const data = [
//   {
//     id: 1,
//     content:
//       "Lorem Ipsum is simply dummy body of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy body ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
//     vote: 1,
//     tag: "gogo",
//     user: "TaTa",
//   },
//   {
//     id: 2,
//     content:
//       "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model body, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
//     vote: 3,
//     tag: "gogo",
//     user: "TaTa",
//   },
//   {
//     id: 3,
//     content:
//       "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of body. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
//     vote: 2,
//     tag: "gogo",
//     user: "TaTa",
//   },
//   {
//     id: 4,
//     content: "asdggdffherhdfbfdbdf",
//     vote: 0,
//     tag: "gogo",
//     user: "TaTa",
//   },
// ];

// const dummytags = [
//   { tagId: 1, name: "javascript" },
//   { tagId: 2, name: "python" },
//   { tagId: 3, name: "c#" },
// ];

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
`;

// const QuestionContentSection = styled.section`
//   display: flex;
//   padding: 1rem 0 1rem 1rem;
//   align-items: flex-start;
// `;

// const Vote = styled.section`
//   display: flex;
//   flex-direction: column;
//   align-items: center;
//   font-size: x-large;
//   color: rgb(116, 117, 122);
//   .icon {
//     color: #b6b8bd;
//     &:active {
//       color: #f48225;
//     }
//   }
// `;

// const QuestionContent = styled.div`
//   display: flex;
//   flex-direction: column;
//   color: #232629;
//   line-height: 25px;
//   margin: 0.5rem 1rem;
//   width: 100%;
// `;

// export const SideSeciton = styled.section`
//   display: flex;
//   justify-content: space-between;
//   align-items: flex-start;
//   margin-top: 1rem;
// `;

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
  // const navigate = useNavigate();
  const editorRef = useRef();
  const [questionData, setQuestionData] = useState([]);
  const [answerData, setAnswerData] = useState([]);
  // const [testData, setTestData] = useState([]);
  // const [isBookMark, setIsBookMark] = useState(false);
  const [isViewer, setIsViewer] = useState(false);
  const [isEditModal, setIsEditModal] = useState(false);
  const [body, setBody] = useState("");
  const [bodyPost, setBodyPost] = useState(true);
  const [questionTag, setQuestionTag] = useState([]);
  // const [content, setContent] = useState("");

  // 답변 정렬 기능 구현
  useEffect(() => {
    // setTestData(data.sort((a, b) => a.vote - b.vote).reverse());
    // if (localStorage.getItem("state") === "vote") {
    //   // console.log("vote");
    //   setTestData(data.sort((a, b) => a.vote - b.vote).reverse());
    // }
    // if (localStorage.getItem("state") === "id") {
    //   // console.log("id");
    //   setTestData(data.sort((a, b) => a.id - b.id));
    // }
    axios
      .get("http://3.39.203.17:8080/questions/1")
      .then((res) => {
        // console.log(res.data);
        setQuestionData(res.data);
        setAnswerData(res.data.answers);
        setQuestionTag(res.data.questionTags);
        // setContent(res.data.problemContent);
        if (localStorage.getItem("state") === "vote") {
          // console.log("vote");
          setAnswerData(
            res.data.answers.sort((a, b) => a.answerId - b.answerId)
          );
        }
        if (localStorage.getItem("state") === "id") {
          // console.log("id");
          setAnswerData(
            res.data.answers.sort((a, b) => a.memberId - b.memberId)
          );
        }
      })
      .catch((err) => console.log(err));
  }, []);

  console.log(questionData);
  // console.log(answerData);
  // console.log(questionTag);
  // useEffect(() => {

  // }, []);

  // console.log(mod);

  // const [mod, setMod] = useState("");

  // console.log(mod);

  // 답변 작성 후 버튼 클릭 시 post 요청 보내는 핸들러
  const handlePost = () => {
    if (body.length >= 30) {
      setBodyPost(true);
      document.getElementById("editor").classList.add("hide");
      // 추가해야 할 내용: 서버에 post 요청 보내기
      axios
        .post("http://3.39.203.17:8080/answers", {
          memberId: 2,
          questionId: 1,
          answerContent: body,
        })
        .catch((err) => console.log(err));

      window.location.reload();
    } else {
      setBodyPost(false);
      document.getElementById("editor").classList.remove("hide");
      // console.log(typeof questionData.problemContent);
    }
  };

  const onChange = () => {
    setBody(editorRef.current.getInstance().getMarkdown());
  };

  // 링크 공유 모달 핸들러
  const handleHideShareModal = (e) => {
    e.stopPropagation();
    document.getElementById("modal").classList.add("hide");
  };

  // const handleShowShareModal = (e) => {
  //   e.stopPropagation();
  //   document.getElementById("modal").classList.remove("hide");
  // };

  // 답변 입력 시 보이는 하단 안내 문구 모달 핸들러
  const handleEditModal = () => {
    setIsEditModal(!isEditModal);
  };

  const changeValue = (e) => {
    if (e.target.value === "vote") {
      localStorage.setItem("state", "vote");
    }
    if (e.target.value === "id") {
      localStorage.setItem("state", "id");
    }
    window.location.reload();
  };

  return (
    <Main onClick={handleHideShareModal}>
      <Container className="question">
        <QuestionTitleSection>
          <div>
            <QuestionTitle>{questionData.title}</QuestionTitle>
            <QuestionTitleDetail>Asked: Modified: Viewed:</QuestionTitleDetail>
          </div>
          {window.localStorage.getItem("user") ? (
            <MainButton href="/questions/ask">Ask Question</MainButton>
          ) : (
            <MainButton href="/login">Ask Question</MainButton>
          )}
        </QuestionTitleSection>
        <Question
          questionData={questionData}
          questionTag={questionTag}
          // content={content}
        />
        {/* <QuestionContentSection>
          <Vote>
            <RiArrowUpSFill className="icon" size={64} />
            <span>0</span>
            <RiArrowDownSFill className="icon" size={64} />
            {isBookMark ? (
              <FaBookmark
                size={16}
                color="#F48225"
                onClick={() => setIsBookMark(!isBookMark)}
              />
            ) : (
              <FaRegBookmark
                size={16}
                color="#b6b8bd"
                onClick={() => setIsBookMark(!isBookMark)}
              />
            )}
          </Vote>
          <QuestionContent>
            <Question questionData={questionData} />
            <Viewer initialValue={questionData.problemContent} />
            {questionData.problemContent}
            <TagCard tags={dummytags} />
            <SideSeciton>
              <SideButtonSection>
                <SideButton onClick={handleShowShareModal}>Share</SideButton>
                <SideButton
                  onClick={() => navigate("/questions/:questionId/edit")}
                >
                  Edit
                </SideButton>
                <SideButton>Delete</SideButton>
                <LinkModal modalId="modal" isAnswer={false} />
              </SideButtonSection>
              <UserProfileCard
                type={true}
                time="2"
                name="Z.G"
                reputation="9"
                src="https://images.unsplash.com/photo-1544967082-d9d25d867d66?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1160&q=80"
              />
            </SideSeciton>
          </QuestionContent>
        </QuestionContentSection> */}
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
              <option value="id">Trending (recent votes count more)</option>
              <option value="3">Date modified (newest first)</option>
              <option value="4">Date created (oldest first)</option>
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
              onChange={onChange}
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
          <MainButton type="submit" onClick={handlePost}>
            Post Your Answer
          </MainButton>
          <CancelButton onClick={() => setIsViewer(!isViewer)}>
            {isViewer ? "Close viewer" : "Open viewer"}
          </CancelButton>
        </BottomButtonSection>
      </Container>
    </Main>
  );
};

export default QuestionDetailPage;
