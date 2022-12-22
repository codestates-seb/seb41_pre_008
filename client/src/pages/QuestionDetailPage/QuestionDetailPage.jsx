import { useEffect } from "react";
import QuestionDetail from "./QusetionDetail";

const QuestionDetailPage = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return <QuestionDetail />;
};

export default QuestionDetailPage;
