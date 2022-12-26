import React from "react";
import styled from "styled-components";

const ModalContainer = styled.section`
  .modal {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    position: relative;
    margin: 1.7rem -8rem;
    padding: 10px 12px 10px 12px;
    background: #ffffff;
    border-radius: 5px;
    box-shadow: 0px 0px 5px #b2b2b2;
    position: absolute;
    font-size: 14px;
    text-align: left;
    width: 345px;
    height: 117px;
    &::after {
      content: "";
      position: absolute;
      border-style: solid;
      border-width: 0 8px 10px 9px;
      border-color: #ffffff transparent;
      display: block;
      width: 0;
      z-index: 1;
      top: -8.5px;
      left: 10px;
    }
    &::before {
      content: "";
      position: absolute;
      border-style: solid;
      border-width: 0 8px 10px 9px;
      border-color: #dbdbdb transparent;
      display: block;
      width: 0;
      z-index: 0;
      top: -10px;
      left: 10px;
    }
    header {
      display: flex;
      margin-bottom: 0.1rem;
      align-items: center;
    }
    h1 {
      display: inline;
      font-weight: bold;
    }
    input {
      display: flex;
      width: 100%;
      height: 35px;
      padding: 0.5rem 0.7rem;
      border: none;
      border: 1px solid #b2b2b2;
      border-radius: 3px;
    }

    .share {
      display: flex;
      justify-content: space-between;
    }

    button {
      display: flex;
      align-items: center;
      border: none;
      background-color: white;
      color: #0e6eb3;
      font-family: "Noto Sans KR", sans-serif;
      font-size: 14px;
      font-weight: 500;
      &:hover {
        filter: brightness(120%);
      }
    }

    a {
      display: flex;
      text-decoration: none;
      color: #0e6eb3;
      font-weight: 500;
      &:hover {
        filter: brightness(120%);
      }
    }
    .social {
      display: flex;
    }
    .socialLogo {
      display: flex;
      &:hover {
        background-color: rgba(113, 197, 231, 0.3);
        border-radius: 2px;
      }
    }
    img {
      width: 15px;
      height: 15px;
      border-radius: 2px;
      margin: 0.2rem;
    }
  }
`;

const LinkModal = ({ modalId, isAnswer }) => {
  const handleCopyLink = async (text) => {
    try {
      await navigator.clipboard.writeText(text);
      alert("Link copied to clipboard");
    } catch (e) {
      alert("Copy to clipboard failed");
    }
  };
  return (
    <ModalContainer>
      <div id={modalId} className="modal hide">
        <div>
          <h1>Share a link to this {isAnswer ? "answer" : "question"}</h1>{" "}
          (Includes your user id)
        </div>
        <input type="text" defaultValue={window.location.href} />
        <div className="share">
          <button onClick={() => handleCopyLink(window.location.href)}>
            Copy Link
          </button>
          <a
            href="https://creativecommons.org/licenses/by-sa/4.0/"
            target="_blank"
            rel="noreferrer"
          >
            CC BY-SA 4.0
          </a>
          <div className="social">
            <a
              href="https://www.facebook.com/login.php?skip_api_login=1&api_key=966242223397117&signed_next=1&next=https%3A%2F%2Fwww.facebook.com%2Fsharer.php%3Fu%3Dhttps%253A%252F%252Fstackoverflow.com%252Fq%252F74907263%252F20811815%253Fsfb%253D2%26ref%3Dfbshare%26t%3DSelect%2Bfunction%2Bload%2Bmultiple%2Bcreating%2Bproblem&cancel_url=https%3A%2F%2Fwww.facebook.com%2Fdialog%2Fclose_window%2F%3Fapp_id%3D966242223397117%26connect%3D0%23_%3D_&display=popup&locale=ko_KR"
              className="socialLogo"
              target="_blank"
              rel="noreferrer"
            >
              <img
                src="https://cdn-icons-png.flaticon.com/512/124/124010.png"
                alt="facebook"
              />
            </a>
            <a
              href="https://twitter.com/intent/tweet?url=https%3A%2F%2Fstackoverflow.com%2Fq%2F74907263%2F20811815%3Fstw%3D2&ref=twitbtn&text=Select%20function%20load%20multiple%20creating%20problem"
              target="_blank"
              className="socialLogo"
              rel="noreferrer"
            >
              <img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Twitter-logo.svg/2491px-Twitter-logo.svg.png"
                target="_blank"
                alt="twitter"
              />
            </a>
            <a
              href="https://dev.to/new?prefill=---%0Atitle%3A%20Select%20function%20load%20multiple%20creating%20problem%0Apublished%3A%20true%0A---%0A%0A%7B%25%20stackoverflow%2074907263%20%25%7D"
              target="_blank"
              className="socialLogo"
              rel="noreferrer"
            >
              <img
                src="https://d2fltix0v2e0sb.cloudfront.net/dev-black.png"
                alt="twitter"
              />
            </a>
          </div>
        </div>
      </div>
    </ModalContainer>
  );
};

export default LinkModal;
