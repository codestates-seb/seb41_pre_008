import React from "react";
import styled from "styled-components";
import { HiPencil } from "react-icons/hi";
import { SlSpeech } from "react-icons/sl";
import { BsStackOverflow } from "react-icons/bs";

const AsideContainer = styled.ul`
  width: 298px;
  height: 400px;
  margin-left: 20px;
  background-color: hsl(47,87%,94%);
  border-left: 1px solid hsl(47,65%,84%);
  border-right: 1px solid hsl(47,65%,84%);
  border-bottom: 1px solid hsl(47,65%,84%);
  box-shadow: 1px 1px 10px 1px rgba(0, 0, 0, 0.1);

  .asideTitle {
    color: #525960;
    font-size: 12px;
    font-weight: bold;

    h3 {
      padding: 12px 15px;
      background-color: #fbf3d5;
      border-top: 1px solid hsl(47,65%,84%);
      border-bottom: 1px solid hsl(47,65%,84%);
    }

    ul{
      li{
        display: flex;
        font-size: 13px;
        color: #3b4045;
        font-weight: normal;
        padding: 0 16px;
        margin: 12px 0;

        span:first-child {
          width: 16px;
          height: 16px;
          flex-basis: 8.4%;
          flex-shrink: 0;
        }
      }
      
    }
  }
`;

const Aside = () => {
  let location = window.location.pathname;
  if (location === "/login" || location === "/signup" || location === '/questions/ask') return null;
  return (
    <AsideContainer>
      <li className="asideTitle">
        <h3>The Overflow Blog</h3>
        <ul>
          <li>
            <span><HiPencil /></span>
            <span>
              I spent two years trying to do what Backstage does for free
            </span>
          </li>
          <li>
            <span><HiPencil /></span>
            <span>
              The complete guide to protecting your APIs with OAuth2 (part 1)
            </span>
          </li>
        </ul>
      </li>
      <li className="asideTitle">
        <h3>Featured on Meta</h3>
        <ul>
          <li>
            <span><SlSpeech /></span>
            <span>Navigation and UI research starting soon</span>
          </li>
          <li>
            <span><BsStackOverflow /></span>
            <span>
              2022 Community Moderator Election Results - now with two more
              mods!
            </span>
          </li>
          <li>
            <span><BsStackOverflow /></span>
            <span>Temporary policy: ChatGPT is banned</span>
          </li>
          <li>
            <span><BsStackOverflow /></span>
            <span>I'm standing down as a moderator</span>
          </li>
        </ul>
      </li>
      <li className="asideTitle">
        <h3>Hot Meta Posts</h3>
        <ul>
          <li>
            <span>7</span>
            <span>
              Some Very unClear tag: drop [svc] for ambiguity since 2013
            </span>
          </li>
        </ul>
      </li>
    </AsideContainer>
  );
};

export default Aside;
