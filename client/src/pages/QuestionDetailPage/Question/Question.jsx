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
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

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
  width: 100%;
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 0;
  justify-content: space-between;
  .modified {
    font-size: 13px;
    color: #0a5c9b;
  }
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
  const [isFollow, setIsFollow] = useState(false);
  const { questionId } = useParams();
  const createObjDate = new Date(questionData.createdAt);
  const createDate = createObjDate.toString();
  const time = `${createDate.slice(4, 7)} ${createDate.slice(
    8,
    10
  )}, ${createDate.slice(11, 15)} at ${createDate.slice(16, 21)}`;
  const modifiedObjDate = new Date(questionData.modifiedAt);
  const modifiedDate = modifiedObjDate.toString();
  const modifiedtime = `modified ${modifiedDate.slice(
    4,
    7
  )} ${modifiedDate.slice(8, 10)}, ${modifiedDate.slice(
    11,
    15
  )} at ${modifiedDate.slice(16, 21)}`;

  const handleShowShareModal = (e) => {
    e.stopPropagation();
    document.getElementById("modal").classList.remove("hide");
  };

  const handleDelete = () => {
    axios
      .delete(`http://3.39.203.17:8080/questions/${questionId}`)
      .then(() => {
        window.location.replace("/questions");
      })
      .catch((err) => console.log(err));
  };

  const handleVoteUp = () => {
    axios
      .post(`http://3.39.203.17:8080/questions/up/${questionId}`, {
        likes: 1,
      })
      .then(() => window.location.reload())
      .catch((err) => {
        console.log(err);
      });
  };
  const handleVoteDown = () => {
    axios
      .post(`http://3.39.203.17:8080/questions/down/${questionId}`, {
        likes: 1,
      })
      .then(() => window.location.reload())
      .catch((err) => {
        console.log(err);
      });
  };

  const handleFollow = () => {
    setIsFollow(!isFollow);
    alert("You’re following this question");
  };

  const handleUnFollow = () => {
    setIsFollow(!isFollow);
    alert("You’re no longer following this question");
  };

  return (
    <QuestionContentSection>
      <Vote>
        <RiArrowUpSFill className="icon" size={64} onClick={handleVoteUp} />
        <span>{questionData.likes}</span>
        <RiArrowDownSFill className="icon" size={64} onClick={handleVoteDown} />
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
        {questionData.problemContent}
        <div className="modified">{modifiedtime}</div>
        <TagCard tags={questionTag} />
        <SideSeciton>
          <SideButtonSection>
            <SideButton onClick={handleShowShareModal}>Share</SideButton>
            {window.localStorage.getItem("user") ? (
              <>
                <SideButton
                  onClick={() => navigate(`/questions/${questionId}/edit`)}
                >
                  Edit
                </SideButton>
                <SideButton onClick={handleDelete}>Delete</SideButton>
                {isFollow ? (
                  <SideButton onClick={handleUnFollow}>Follwing</SideButton>
                ) : (
                  <SideButton onClick={handleFollow}>Follow</SideButton>
                )}
              </>
            ) : (
              <>
                <SideButton onClick={() => window.location.replace("/login")}>
                  Edit
                </SideButton>
                <SideButton onClick={() => window.location.replace("/login")}>
                  Delete
                </SideButton>
                <SideButton onClick={() => window.location.replace("/signup")}>
                  Follow
                </SideButton>
              </>
            )}

            <LinkModal modalId="modal" isAnswer={false} />
          </SideButtonSection>
          <UserProfileCard
            type={true}
            time={time}
            name={questionData.nickName}
            userId={questionData.memberId}
            src="https://i.pinimg.com/474x/d7/70/33/d7703333ad8ba85827b60fccf42f9c25.jpg"
          />
        </SideSeciton>
      </QuestionContent>
    </QuestionContentSection>
  );
};

export default Question;
