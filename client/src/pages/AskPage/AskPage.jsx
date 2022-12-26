import React, { useState } from 'react';
import styled from "styled-components";

const AskContainer = styled.div`
  padding: 60px;
  width: 100%;

  .askTitle {
    display: flex;
    align-items: center;
    height: 130px;
    font-size: 27px;
    font-weight: bold;
    color: #232629;
    margin-bottom: 16px;

    .askTitleBg {
      flex-grow: 1;
      height: 100%;
      background-size: contain;
      background-position: right;
      background-repeat: no-repeat;
      background-image: url('https://cdn.sstatic.net/Img/ask/background.svg?v=2e9a8205b368');
    }
  }

  .writingInfo {
    width: 852px;
    background-color: #ebf4fb;
    padding: 24px;
    border: 1px solid hsl(205,57%,81%);
    border-radius: 3px;
    color: #3b4045;
    margin-bottom: 16px;

    h3 {
      font-size: 21px;
      font-weight: 400;
      margin: 0 0 8px;
    }

    p {
      font-size: 15px;
      margin: 0 0 15px;
    }

    ul {
      list-style: disc;
      font-size: 13px;

      h4 {
        font-weight: bold;
        margin: 0 0 8px;
      }

      li {
        font-weight: normal;
        margin-left: 32px;
      }
    }
  }

  .writingTitle, .writingProblem, .writingExpecting, .writingTags {
    display: flex;
    flex-direction: column;
    width: 852px;
    background-color: #fff;
    padding: 24px;
    border: 1px solid #e3e6e8;
    border-radius: 3px;
    margin-bottom: 16px;

    .labelTitle {
      font-size: 15px;
      font-weight: bold;
      padding: 0 2px;
    }

    label {
      font-size: 12px;
      margin-bottom: 6px;
    }

    input, textarea {
      padding: 7.8px 9.1px;
      border: 1px solid #e3e6e8;
      border-radius: 3px;
    }
  }

  .buttons {
    .submitButton {
      padding: 10px;
      background-color: #0a95ff;
      color: #fff;
      border: 0;
      border-radius: 3px;
      &:hover {
        cursor: pointer;
      }
    }

    .deleteButton {
      padding: 10px;
      background-color: #fff;
      color: #c2223e;
      border: 0;
      border-radius: 3px;
      margin-left: 16px;
      &:hover {
        cursor: pointer;
      }
    }
  }
`


const AskPage = () => {
  const [inputValue, setInputValue] = useState({
    title: '',
    problemContent: '',
    expectContent: '',
    tags: '',
  })

  // inputValue에서 값 추출 -> input value 속성에 추출한 값 할당
  const { title, problemContent, expectContent, tags } = inputValue;

  // e.target name/value를 inputValue 객체에 복사
  const onChangeValue = (e) => {
    setInputValue({
      ...inputValue,
      [e.target.name]: e.target.value,
    });
  };

  // 입력한 값 초기화 버튼 
  const deleteValue = (e) => {
    setInputValue({
      title: '',
      problemContent: '',
      expectContent: '',
      tags: '',
    });
  }

  return (
    <AskContainer>
      <div className='askTitle'>
        <h2>Ask a public question</h2>
        <div className="askTitleBg"></div>
      </div>
      <div className='writingInfo'>
        <h3>Writing a good question</h3>
        <p>You’re ready to ask a programming-related question and this form will help guide you through the process. <br />
        Looking to ask a non-programming question? See the topics here to find a relevant site.</p>
        <ul>
          <h4>Steps</h4>
          <li>Summarize your problem in a one-line title.</li>
          <li>Describe your problem in more detail.</li>
          <li>Describe what you tried and what you expected to happen.</li>
          <li>Add “tags” which help surface your question to members of the community.</li>
          <li>Review your question and post it to the site.</li>
        </ul>
      </div>
      <form>
        <div className='writingTitle'>
          <label for='title' className='labelTitle'>Title</label>
          <label for='title'>Be specific and imagine you’re asking a question to another person.</label>
          <input name='title' type='text' id='title' placeholder='e.g. Is there an R function for finding the index of an element in a vector?' value={title} onChange={onChangeValue}></input>
        </div>
        <div className='writingProblem'>
          <label for='problem' className='labelTitle'>What are the details of your problem?</label>
          <label for='problem'>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</label>
          <textarea id='problem' name='problemContent' minlength='20' value={problemContent} onChange={onChangeValue}></textarea>
        </div>
        <div className='writingExpecting'>
          <label for='expecting' className='labelTitle'>What did you try and what were you expecting?</label>
          <label for='expecting'>Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.</label>
          <textarea id='expecting' name='expectContent' minlength='20' value={expectContent} onChange={onChangeValue}></textarea>
        </div>
        <div className='writingTags'>
          <label for='tags' className='labelTitle'>Tags</label>
          <label for='tags'>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</label>
          <input name='tags' type='text' id='tags' placeholder='e.g. (c# laravel typescript)' value={tags} onChange={onChangeValue}></input>
        </div>
        <div className='buttons'>
          <button type='submit' className='submitButton'>Review your question</button>
          <button type='button' className='deleteButton' onClick={deleteValue}>Discard draft</button>
        </div>
      </form>
    </AskContainer>
  )
};

export default AskPage;
