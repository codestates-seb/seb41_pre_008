import React from "react";
import Answer from "./Answer";
// import { Viewer } from "@toast-ui/react-editor";

const AnswerList = ({ answerData }) => {
  return (
    <div>
      {answerData.map((el) => (
        <Answer answer={el} key={el.answerId} />
      ))}
      {/* <Viewer initialValue={answerData[0].nickName} /> */}
    </div>
  );
};

export default AnswerList;
