import React from "react";
import styled from "styled-components";
import { RiArrowUpSFill, RiArrowDownSFill } from "react-icons/ri";
import { FaBookmark, FaRegBookmark } from "react-icons/fa";
import { useState } from "react";
import { SideSeciton } from "../Question/Question";
import { useNavigate, useParams } from "react-router-dom";
import LinkModal from "../LinkModal/LinkModal";
import UserProfileCard from "../DetailComponents/UserProfileCard";
import {
  SideButtonSection,
  SideButton,
} from "../DetailComponents/ButtonBundle";
import { Viewer } from "@toast-ui/react-editor";
import axios from "axios";

const AnswerContentSection = styled.section`
  display: flex;
  padding: 1rem 0 1rem 1rem;
  align-items: flex-start;
  border-bottom: 1px solid rgb(210, 212, 219);
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

const AnswerContentContainer = styled.section`
  display: flex;
  flex-direction: column;
  color: #232629;
  line-height: 25px;
  margin: 0.5rem 0;
  width: 100%;
  justify-content: space-between;
  .modified {
    font-size: 13px;
    color: #0a5c9b;
  }
`;

const Answer = ({ answer }) => {
  const [isBookMark, setIsBookMark] = useState(false);
  const navigate = useNavigate();
  const { questionId } = useParams();
  const [isFollow, setIsFollow] = useState(false);
  const createObjDate = new Date(answer.createdAt);
  const createDate = createObjDate.toString();
  const time = `${createDate.slice(4, 7)} ${createDate.slice(
    8,
    10
  )}, ${createDate.slice(11, 15)} at ${createDate.slice(16, 21)}`;
  const modifiedObjDate = new Date(answer.modifiedAt);
  const modifiedDate = modifiedObjDate.toString();
  const modifiedtime = `edited ${modifiedDate.slice(4, 7)} ${modifiedDate.slice(
    8,
    10
  )}, ${modifiedDate.slice(11, 15)} at ${modifiedDate.slice(16, 21)}`;

  // 링크 공유 모달 핸들러
  const handleHideShareModal = (e) => {
    e.stopPropagation();
    document.getElementById(`${answer.answerId}`).classList.add("hide");
  };

  const handleShowShareModal = (e) => {
    e.stopPropagation();
    document.getElementById(`${answer.answerId}`).classList.remove("hide");
  };

  const handleDelete = () => {
    axios
      .delete(`http://3.39.203.17:8080/answers/${answer.answerId}`)
      .then(() => {
        window.location.reload();
      })
      .catch((err) => console.log(err));
  };

  const handleVoteUp = () => {
    axios
      .post(`http://3.39.203.17:8080/answers/up/${answer.answerId}`, {
        likes: 1,
      })
      .then(() => window.location.reload())
      .catch((err) => {
        console.log(err);
      });
  };
  const handleVoteDown = () => {
    axios
      .post(`http://3.39.203.17:8080/answers/down/${answer.answerId}`, {
        likes: 1,
      })
      .then(() => window.location.reload())
      .catch((err) => {
        console.log(err);
      });
  };

  const handleFollow = () => {
    setIsFollow(!isFollow);
    alert("You’re following this answer");
  };

  const handleUnFollow = () => {
    setIsFollow(!isFollow);
    alert("You’re no longer following this answer");
  };

  return (
    <AnswerContentSection onClick={handleHideShareModal}>
      <Vote>
        <RiArrowUpSFill className="icon" size={64} onClick={handleVoteUp} />
        <span>{answer.likes}</span>
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
      <AnswerContentContainer>
        <Viewer initialValue={answer.answerContent} />
        <div className="modified">{modifiedtime}</div>
        <SideSeciton>
          <SideButtonSection>
            <SideButton onClick={handleShowShareModal}>Share</SideButton>
            {window.localStorage.getItem("user") ? (
              <>
                <SideButton
                  onClick={() =>
                    navigate(
                      `/questions/${questionId}/answer/edit/${answer.answerId}`
                    )
                  }
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
            <LinkModal modalId={answer.answerId} isAnswer={true} />
          </SideButtonSection>
          <UserProfileCard
            time={time}
            name={answer.nickName}
            userId={answer.memberId}
            src="https://i.pinimg.com/474x/d7/70/33/d7703333ad8ba85827b60fccf42f9c25.jpg"
          />
        </SideSeciton>
      </AnswerContentContainer>
    </AnswerContentSection>
  );
};

export default Answer;
