import React from "react";
import styled from "styled-components";
import test from "../../img/test/test.png";

const Main = styled.div`
  display: flex;
`;

const Section = styled.div`
  width: 750px;

  header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 28px;
    padding-left: 24px;

    h2 {
      font-size: 27px;
    }

    button {
      padding: 10px;
      background-color: #0a95ff;
      color: #fff;
      border: 0;
      border-radius: 3px;
    }
  }

  .alignFilter {
    display: flex;
    justify-content: flex-end;
    margin-bottom: 16px;

    button {
      padding: 10px;
      border: 1px solid rgb(159, 166, 173);
      border-left: 0;
      background-color: #fff;
      color: #6a737c;

      span {
        background-color: #0074cc;
        color: #fff;
        margin-right: 5px;
        padding: 2.2px 5.5px 2.7px;
        border-radius: 3px;
      }
    }

    button:first-child {
      border-left: 1px solid rgb(159, 166, 173);
      border-radius: 3px 0 0 3px;
    }

    button:last-child {
      border-radius: 0 3px 3px 0;
    }
  }

  article {
    display: flex;
    border-top: 1px solid #d6d9dc;
    border-bottom: 1px solid #d6d9dc;
    padding: 16px;

    .dataInfo {
      width: 108px;
      text-align: right;
      margin-right: 16px;

      ul {
        li:nth-child(n + 2) {
          color: #6a737c;
        }
      }

      li {
        font-size: 13px;
        margin-bottom: 8px;
      }

      span {
        margin-right: 3px;
      }
    }

    .questionInfo {
      flex-grow: 1;

      h3 {
        font-size: 17px;
        color: #0074cc;
        margin-bottom: 8px;
      }
    }

    .questionSub {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
      flex-grow: 1;

      .tags {
        flex-grow: 1;
        display: flex;
        justify-content: flex-start;

        a {
          font-size: 12px;
          background-color: #e1ecf4;
          color: #39749d;
          padding: 4.8px 6px;
          margin: 0 2px 2px 0;
          border-radius: 3px;
        }
      }

      .userInfo {
        flex-grow: 1;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        font-size: 12px;

        * {
          margin-right: 3px;
        }

        img {
          width: 16px;
          height: 16px;
          border-radius: 3px;
          position: relative;
          top: -1px;
        }

        a {
          color: #0074cc;
        }

        span {
          color: #6a737c;
        }

        span:nth-child(3) {
          font-weight: bold;
          color: #525960;
        }
      }
    }
  }
`;

const QuestionListPage = () => {
  return (
    <Main>
      <Section>
        <header>
          <h2>Top Questions</h2>
          <button>Ask Question</button>
        </header>
        <div className="alignFilter">
          <button type="button">Interesting</button>
          <button type="button">
            <span>242</span>Bountied
          </button>
          <button type="button">Hot</button>
          <button type="button">Week</button>
          <button type="button">Month</button>
        </div>
        <article>
          <div className="dataInfo">
            <ul>
              <li>
                <span>0</span>votes
              </li>
              <li>
                <span>0</span>answers
              </li>
              <li>
                <span>0</span>views
              </li>
            </ul>
          </div>
          <div className="questionInfo">
            <h3>Question Title</h3>
            <div className="questionSub">
              <div className="tags">
                <a href="/">tag</a>
              </div>
              <div className="userInfo">
                <img src={test} alt="profile img" />
                <a href="/mypage">Ron</a>
                <span>5,281</span>
                <span>modified</span>
                <span>1</span>
                <span>min ago</span>
              </div>
            </div>
          </div>
        </article>
      </Section>
    </Main>
  );
};

export default QuestionListPage;
