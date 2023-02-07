import React from "react";
import Answer from "./Answer";

const AnswerList = ({ answerData }) => {
  return (
    <div>
      {answerData.map((el) => (
        <Answer answer={el} key={el.answerId} />
      ))}
    </div>
  );
};

export default AnswerList;
