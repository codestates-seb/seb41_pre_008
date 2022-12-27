import React from "react";
import Answer from "./Answer";

const AnswerList = ({ testData }) => {
  return (
    <div>
      {testData.map((el) => (
        <Answer answer={el} key={el.id} />
      ))}
    </div>
  );
};

export default AnswerList;
