import React from "react";
import Answer from "./Answer";

const AnswerList = ({ data }) => {
  return (
    <div>
      {data.map((el) => (
        <Answer answers={el} key={el.id} />
      ))}
    </div>
  );
};

export default AnswerList;
