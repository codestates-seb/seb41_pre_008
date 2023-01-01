import axios from "axios";
import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import styled from "styled-components";
import TagsPageCard from "./TagsPageCard";
import search from "../../img/Login/search.png";

const Main = styled.main`
  display: flex;
  flex-direction: column;
  width: 750px;
  .tagsCard {
    display: flex;
    flex-wrap: wrap;
  }
  h1 {
    font-size: 25px;
    margin: 0 1rem;
  }
  .tag.content {
    @media screen and (max-width: 900px) {
      width: 600px;
    }
    font-size: 15px;
    line-height: 20px;
    margin: 1rem;
  }
  .aSection {
    display: flex;
    .tag.synonyms {
      font-size: 14px;
      color: #0074cc;
      margin: 0.5rem 1rem 1rem 1rem;
      &:hover {
        filter: brightness(120%);
      }
    }
  }
  .filterSection {
    display: flex;
    @media screen and (max-width: 900px) {
      width: 616px;
    }
    justify-content: space-between;
    align-items: center;
    margin: 0.5rem 0 1rem 1rem;
    .filterInput {
      position: relative;
      width: 188px;
      height: 37px;
      input {
        padding: 0.5rem 0 0.5rem 2rem;
        border: 1px solid rgb(169, 170, 178);
        border-radius: 3px;
        width: 188px;
        height: 37px;
        &::placeholder {
          color: rgb(202, 203, 211);
        }
        &:focus-within {
          outline: none !important;
          border-color: #1d80cb;
          box-shadow: 0 0 0 4px rgba(9, 128, 219, 0.2);
        }
      }
      img {
        position: absolute;
        width: 27px;
        left: 3px;
        top: 7px;
      }
    }
    .filterButton {
      button {
        padding: 10px;
        border: 1px solid rgb(159, 166, 173);
        border-left: 0;
        background-color: #fff;
        color: #6a737c;
        &.active {
          background-color: #e3e6e8;
          color: #3b4045;
        }
        &:first-child {
          border-left: 1px solid rgb(159, 166, 173);
          border-radius: 3px 0 0 3px;
        }
        &:last-child {
          border-radius: 0 3px 3px 0;
        }
      }
    }
  }
`;

const TagsPage = () => {
  useEffect(() => {
    axios
      .get("http://3.39.203.17:8080/tags?page=1&size=20")
      .then((res) => setTags(res.data.data.sort((a, b) => a.tagId - b.tagId)))
      .catch((err) => console.log(err));
  }, []);

  const [tags, setTags] = useState([]);
  const [isDefault, setIsDefault] = useState(false);
  const [isName, setIsName] = useState(false);
  const [isNew, setIsNew] = useState(false);

  // tag id 값 기준으로 기본 정렬
  const handleDefault = () => {
    axios
      .get("http://3.39.203.17:8080/tags?page=1&size=20")
      .then((res) => setTags(res.data.data.sort((a, b) => a.tagId - b.tagId)))
      .catch((err) => console.log(err));
    setIsDefault(!isDefault);
  };

  // tag 사전식 정렬
  const handleName = () => {
    const name = tags.sort((a, b) =>
      a.name.toLowerCase() < b.name.toLowerCase() ? -1 : 1
    );
    setTags(name);
    setIsName(!isName);
  };

  // 새로운 태그 기준 정렬
  const handleNew = () => {
    const newTags = tags.sort((a, b) => b.tagId - a.tagId);
    setTags(newTags);
    setIsNew(!isNew);
  };

  // 원하는 tag 입력 시 tagname을 기준으로 필터해주는 핸들러
  const handleFilterTags = (event) => {
    const taglist = tags.map((tag) => tag.name);
    if (taglist.includes(event.target.value)) {
      const filterTag = tags.filter((tag) => tag.name === event.target.value);
      setTags(filterTag);
      event.target.value = "";
    } else {
      event.target.value = "";
    }
  };

  return (
    <Main>
      <h1>Tags</h1>
      <p className="tag content">
        A tag is a keyword or label that categorizes your question with other,
        similar questions. Using the right tags makes it easier for others to
        find and answer your question.
      </p>
      <section className="aSection">
        <a
          className="tag synonyms"
          href="https://stackoverflow.com/tags/synonyms"
        >
          Show all tag synonyms
        </a>
      </section>
      <section className="filterSection">
        <div className="filterInput">
          <input
            type="text"
            placeholder="Filter by tag name"
            onKeyUp={(e) => (e.key === "Enter" ? handleFilterTags(e) : null)}
            autoFocus
          />
          <img src={search} alt="tag filter" />
        </div>
        <div className="filterButton">
          <button className={isDefault ? "active" : ""} onClick={handleDefault}>
            default
          </button>
          <button className={isName ? "active" : ""} onClick={handleName}>
            name
          </button>
          <button className={isNew ? "active" : ""} onClick={handleNew}>
            new
          </button>
        </div>
      </section>
      <section className="tagsCard">
        <TagsPageCard tags={tags} />
      </section>
    </Main>
  );
};

export default TagsPage;
