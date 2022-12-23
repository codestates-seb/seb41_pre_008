import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import AnswerList from "./Answer/AnserList";

const data = [
  {
    id: 1,
    content:
      "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
    vote: 1,
    bookmark: true,
    tag: "gogo",
    user: "TaTa",
  },
  {
    id: 2,
    content:
      "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
    vote: 3,
    bookmark: false,
    tag: "gogo",
    user: "TaTa",
  },
  {
    id: 3,
    content:
      "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
    vote: 2,
    bookmark: false,
    tag: "gogo",
    user: "TaTa",
  },
  {
    id: 4,
    content: "asdggdffherhdfbfdbdf",
    vote: 0,
    bookmark: true,
    tag: "gogo",
    user: "TaTa",
  },
];

// const tags = [];

const Container = styled.section`
  display: flex;
  flex-direction: column;
  margin: 1rem;
  border-bottom: 1px solid rgb(210, 212, 219);
  .icon {
    color: #b6b8bd;
    &:active {
      color: #f48225;
    }
  }
`;
const QuestionTitleSection = styled.section`
  display: flex;
  padding: 1rem;
  border-bottom: 1px solid rgb(210, 212, 219);
`;
export const MainButton = styled.button`
  width: 10vw;
  height: 5vh;
  border: none;
  color: white;
  background-color: #0a95ff;
  border-radius: 5px;
  margin: 1rem 0;
  &:hover {
    filter: brightness(120%);
  }
`;
const QuestionTitle = styled.h1`
  display: flex;
  color: #3b4045;
  font-size: xx-large;
  line-height: 40px;
  margin-right: 1rem;
`;

const QuestionTitleDetail = styled.p`
  color: rgb(116, 117, 122);
`;

const QuestionContentSection = styled.section`
  display: flex;
  padding: 1rem;
  align-items: center;
`;

const Vote = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  font-size: x-large;
  color: rgb(116, 117, 122);
`;

const QuestionContent = styled.div`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 1rem;
  width: 100%;
`;

export const SideSeciton = styled.section`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-top: 1rem;
`;

export const SideButtonSection = styled.div`
  display: flex;
`;

export const SideButton = styled.button`
  border: none;
  color: rgb(116, 117, 122);
  background: white;
  &:hover {
    color: #3b4045;
  }
`;

const AnswerTitle = styled.h2`
  padding: 1rem;
  color: #3b4045;
  font-size: x-large;
`;

const UserProfileContainer = styled.section`
  display: flex;
  flex-direction: column;
  background-color: #d8eaf7;
  border-radius: 3px;
  width: 200px;
  height: 67px;
  padding: 0.2rem 0.5rem;
  .recent {
    color: #676c72;
    font-size: small;
  }
  .userProfile {
    display: flex;
    align-items: center;
  }
  img {
    width: 32px;
    height: 32px;
    border-radius: 3px;
  }
  .userInfo {
    display: flex;
    flex-direction: column;
    margin-left: 0.5rem;
    line-height: 15px;
    font-size: small;
  }
  .userName {
    color: #0e6eb3;
    font-weight: bold;
  }
  .userReputation {
    font-weight: bold;
  }
`;

const TagContainer = styled.div`
  display: flex;
  flex: 1;
  justify-content: flex-start;
  align-items: center;
  margin-top: 0.5rem;
`;

const Tag = styled.a`
  display: flex;
  font-size: 12px;
  background-color: #e1ecf4;
  color: #39749d;
  padding: 4.8px 6px;
  margin-right: 0.4rem;
  border-radius: 3px;
  height: 25px;
  align-items: center;
`;

export const UserProgileCard = ({
  type = false,
  time,
  name,
  reputation,
  src,
}) => {
  return (
    <UserProfileContainer>
      <div className="recent">
        {type === true ? "asked" : "answered"} {time} mins ago
      </div>
      <div className="userProfile">
        <img src={src} alt="profileimg" />
        <div className="userInfo">
          <span className="userName">{name}</span>
          <span className="userReputation">{reputation}</span>
        </div>
      </div>
    </UserProfileContainer>
  );
};

// const

const QuestionDetailPage = () => {
  const navigate = useNavigate();
  const [isBookMark, setIsBookMark] = useState(false);
  const handlePostAnswer = () => {
    // 추가해야 할 내용: 서버에 post 요청 보내기
    console.log(editorRef.current.getInstance().getHTML());
    window.scrollTo(0, 0);
    navigate("/questions/1");
  };
  const editorRef = useRef();

  return (
    <main>
      <Container>
        <QuestionTitleSection>
          <div>
            <QuestionTitle>
              How to setup twilio device in Angular app for the incoming call
              (i.e. API is in .NET 6)?
            </QuestionTitle>
            <QuestionTitleDetail>Asked: Modified: Viewed:</QuestionTitleDetail>
          </div>
          <MainButton onClick={() => navigate("/questions/ask")}>
            Ask Question
          </MainButton>
        </QuestionTitleSection>
        <QuestionContentSection>
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
            I am facing one issue related to incoming calls, so the situation is
            API is in .NET 6 and it's frontend is in Angular app and also it's
            multi-tenant application.
            <TagContainer>
              {" "}
              <Tag>python</Tag>
              <Tag>javascript</Tag>
              <Tag>c#</Tag>
            </TagContainer>
            <SideSeciton>
              <SideButtonSection>
                <SideButton>Share</SideButton>
                <SideButton onClick={() => navigate("/questions/edit")}>
                  Edit
                </SideButton>
                <SideButton>Delete</SideButton>
              </SideButtonSection>
              <UserProgileCard
                type={true}
                time="2"
                name="Z.G"
                reputation="9"
                src="https://images.unsplash.com/photo-1544967082-d9d25d867d66?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1160&q=80"
              />
            </SideSeciton>
          </QuestionContent>
        </QuestionContentSection>
      </Container>
      <Container>
        <AnswerTitle>{data.length} Answer</AnswerTitle>
        <AnswerList data={data} />
      </Container>
      <Container>
        <AnswerTitle>Your Answer</AnswerTitle>
        <Editor
          ref={editorRef}
          initialValue=" "
          placeholder="Please enter your contents"
          height="300px"
          useCommandShortcut={false}
        />
        <MainButton type="submit" onClick={handlePostAnswer}>
          Post Your Answer
        </MainButton>
      </Container>
    </main>
  );
};

export default QuestionDetailPage;
