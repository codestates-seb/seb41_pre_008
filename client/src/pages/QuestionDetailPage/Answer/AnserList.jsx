import React from "react";
import Answer from "./Answer";

const AnswerList = ({ data }) => {
  return (
    <div>
      {data.map((el) => (
        <Answer answer={el} key={el.id} />
      ))}
    </div>
  );
};

export default AnswerList;
