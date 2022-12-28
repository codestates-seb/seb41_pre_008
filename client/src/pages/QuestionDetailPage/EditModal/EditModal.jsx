import React from "react";
import styled from "styled-components";
import { RiCloseFill } from "react-icons/ri";

const EditIntroContainer = styled.section`
  display: flex;
  justify-content: space-between;
  border: 1px solid #dfc257;
  border-radius: 5px;
  background-color: #fff9e3;
  line-height: 15px;
  padding: 0.8rem;
  margin: 1rem 0 0 0;
  p,
  section {
    font-size: 13px;
    color: #3b4045;
    padding: 0.5rem;
    ul {
      margin-left: 2rem;
      list-style-type: disc;
    }
    a {
      color: #0a95ff;
      filter: brightness(80%);
      &:hover {
        filter: brightness(100%);
      }
    }
  }
  .closeButton {
    font-size: 30px;
    cursor: pointer;
  }
`;

const EditModalCard = ({ setIsEditModal }) => {
  const handleClick = () => {
    setIsEditModal();
  };

  return (
    <EditIntroContainer id="editmodal" className="editmodal hide">
      <section>
        <p>Thanks for contributing an answer to Stack Overflow!</p>
        <section>
          <ul>
            <li>
              Please be sure to answer the question. Provide details and share
              your research!
            </li>
          </ul>
        </section>
        <p>But avoid …</p>
        <section>
          <ul>
            <li>
              Asking for help, clarification, or responding to other answers.
            </li>
            <li>
              Making statements based on opinion; back them up with references
              or personal experience.
            </li>
          </ul>
        </section>
        <p>
          To learn more, see our{" "}
          <a href="https://stackoverflow.com/help/how-to-answer">
            tips on writing great answers
          </a>
          .
        </p>
      </section>
      <RiCloseFill className="closeButton" onClick={handleClick} />
    </EditIntroContainer>
  );
};

export default EditModalCard;
