import React from "react";
import styled from "styled-components";
import { HiPencil } from "react-icons/hi";
import { SlSpeech } from "react-icons/sl";
import { BsStackOverflow } from "react-icons/bs";

const AsideContainer = styled.ul`
  width: 298px;
  border: 1px solid yellow;
  margin-left: 20px;

  .asideTitle {
    color: #525960;
    font-size: 12px;
    padding: 12px 15px;
    background-color: #fbf3d5;
    font-weight: bold;

    li,
    span {
      font-size: 13px;
      color: #3b4045;
      font-weight: normal;
    }
  }
`;

const Aside = () => {
  let location = window.location.pathname;
  if (location === "/login" || location === "/signup") return null;
  return (
    <AsideContainer>
      <li className="asideTitle">
        The Overflow Blog
        <ul>
          <li>
            <HiPencil />
            <span>
              I spent two years trying to do what Backstage does for free
            </span>
          </li>
          <li>
            <HiPencil />
            <span>
              The complete guide to protecting your APIs with OAuth2 (part 1)å
            </span>
          </li>
        </ul>
      </li>
      <li className="asideTitle">
        Featured on Meta
        <ul>
          <li>
            <SlSpeech />
            <span>Navigation and UI research starting soon</span>
          </li>
          <li>
            <BsStackOverflow />
            <span>
              2022 Community Moderator Election Results - now with two more
              mods!
            </span>
          </li>
          <li>
            <BsStackOverflow />
            <span>Temporary policy: ChatGPT is banned</span>
          </li>
          <li>
            <BsStackOverflow />
            <span>I'm standing down as a moderator</span>
          </li>
        </ul>
      </li>
      <li className="asideTitle">
        Hot Meta Posts
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
