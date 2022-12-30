import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import {
  SideButton,
  SideButtonSection,
} from "../DetailComponents/ButtonBundle";
import LinkModal from "../LinkModal/LinkModal";
import UserProfileCard from "../DetailComponents/UserProfileCard";
import TagCard from "../DetailComponents/TagCard";
import { Viewer } from "@toast-ui/react-editor";
// import QuestionViewer from "./QuestionViewer";
import { useNavigate } from "react-router-dom";
// import { useEffect } from "react";
// import { useEffect } from "react";

const QuestionContentSection = styled.section`
  display: flex;
  padding: 1rem 0 1rem 1rem;
  align-items: flex-start;
`;

const Vote = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: x-large;
  color: rgb(116, 117, 122);
  .icon {
    color: #b6b8bd;
    &:active {
      color: #f48225;
    }
  }
`;

const QuestionContent = styled.div`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 0;
  width: 100%;
`;

export const SideSeciton = styled.section`
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-top: 1rem;
`;

const Question = ({ questionData, questionTag }) => {
  const navigate = useNavigate();
  const [isBookMark, setIsBookMark] = useState(false);
  // const [test, setTest] = useState(true);

  // useEffect(() => {
  //   setTest(!test);
  // }, []);
  // console.log(test);
  // const [content, setContent] = useState("");

  // console.log(questionData);
  // console.log(questionData.problemContent);

  // useEffect(() => {
  //   setContent(questionData.problemContent);
  // }, []);
  // console.log(content);

  const handleShowShareModal = (e) => {
    e.stopPropagation();
    document.getElementById("modal").classList.remove("hide");
  };
  return (
    <QuestionContentSection>
      <Vote>
        <RiArrowUpSFill className="icon" size={64} />
        <span>{questionData.likes}</span>
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
        <div>
          {/* dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsgv
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsgv
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsgv
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsgv
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg
          dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg dsgagkdskfkkdfalgsg */}
          <Viewer initialValue={questionData.problemContent} />
        </div>

        {/* <QuestionViewer data={questionData.problemContent} /> */}
        {/* <Question questionData={questionData} /> */}

        {/* {questionData.problemContent} */}
        <TagCard tags={questionTag} />
        <SideSeciton>
          <SideButtonSection>
            <SideButton onClick={handleShowShareModal}>Share</SideButton>
            {window.localStorage.getItem("user") ? (
              <SideButton
                onClick={() => navigate("/questions/:questionId/edit")}
              >
                Edit
              </SideButton>
            ) : (
              <SideButton onClick={() => window.location.replace("/login")}>
                Edit
              </SideButton>
            )}
            <SideButton>Delete</SideButton>
            <LinkModal modalId="modal" isAnswer={false} />
          </SideButtonSection>
          <UserProfileCard
            type={true}
            time="2"
            name={questionData.nickName}
            reputation="9"
            src="https://images.unsplash.com/photo-1544967082-d9d25d867d66?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1160&q=80"
          />
        </SideSeciton>
      </QuestionContent>
    </QuestionContentSection>
  );
};

export default Question;
