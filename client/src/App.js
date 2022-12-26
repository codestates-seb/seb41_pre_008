import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';
import SignupPage from './pages/SignupPage/SignupPage';
import QuestionDetailPage from './pages/QuestionDetailPage/QuestionDetailPage';
import AskPage from './pages/AskPage/AskPage';
import MyPage from './pages/MyPage/MyPage';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import QuestionEditPage from './pages/QuestionEditPage/QuestionEditPage';
import QuestionListPage from './pages/QuestionListPage/QuestionListPage';
import AnswerEditPage from './pages/AnswerEditPage/AnswerEditPage';
import Aside from './components/Aside/Aside';
import styled from 'styled-components';

const FullContainer = styled.div`
  max-width: 1400px;
  margin: 0 auto;
`;

const Container = styled.div`
  display: flex;
  padding: 74px 0 0 214px;
`;

const router = createBrowserRouter([
  {
    path: '/',
    element: <QuestionListPage />,
  },
  {
    path: '/login',
    element: <LoginPage />,
    errorElement: <div>Not found</div>,
  },
  {
    path: '/signup',
    element: <SignupPage />,
  },
  {
    path: '/questions',
    element: <QuestionListPage />,
  },
  {
    path: '/questions/ask',
    element: <AskPage />,
  },
  {
    path: '/questions/:questionId',
    element: <QuestionDetailPage />,
  },
  {
    path: '/questions/:questionId/edit',
    element: <QuestionEditPage />,
  },
  {
    path: '/questions/:questionId/answer/edit/:answerId',
    element: <AnswerEditPage />,
  },
  {
    path: '/mypage',
    element: <MyPage />,
  },
]);

const Main = ({ children }) => {
  let location = window.location.pathname;
  if (
    location === '/login' ||
    location === '/signup' ||
    location === '/questions/ask'
  ) {
    return <div>{children}</div>;
  } else {
    return <Container>{children}</Container>;
  }
};

function App() {
  return (
    <FullContainer>
      <Header />
      <Main>
        <RouterProvider router={router} />
        <Aside />
      </Main>
      <Footer />
    </FullContainer>
  );
}

export default App;
