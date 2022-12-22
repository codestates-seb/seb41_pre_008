import "./App.css";
import LoginPage from "./pages/LoginPage/LoginPage";
import SignupPage from "./pages/SignupPage/SignupPage";
import QuestionListPage from "./pages/QuestionListPage/QuestionListPage";
import QuestionDetailPage from "./pages/QuestionDetailPage/QuestionDetailPage";
import AskPage from "./pages/AskPage/AskPage";
import MyPage from "./pages/MyPage/MyPage";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";
import Nav from "./components/Nav/Nav";
import QuestionEditPage from "./pages/QuestionEditPage/QuestionEditPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <QuestionListPage />,
  },
  {
    path: "/login",
    element: <LoginPage />,
    errorElement: <div>Not found</div>,
  },
  {
    path: "/signup",
    element: <SignupPage />,
  },
  {
    path: "/questions",
    element: <QuestionListPage />,
  },
  {
    path: "/questions/ask",
    element: <AskPage />,
  },
  {
    path: "/questions/:id",
    element: <QuestionDetailPage />,
  },
  {
    path: "/questions/edit",
    element: <QuestionEditPage />,
  },
  {
    path: "/mypage",
    element: <MyPage />,
  },
]);

function App() {
  return (
    <>
      <Header />
      <RouterProvider router={router} />
      <Footer />
    </>
  );
}

export default App;
