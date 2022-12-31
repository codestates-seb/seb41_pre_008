import axios from "axios";
import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import styled from "styled-components";
import TagsPageCard from "./TagsPageCard";

const Main = styled.main`
  display: flex;
  /* flex-wrap: wrap; */
  flex-direction: column;
  min-width: 500px;
  width: 750px;
  .tagsCard {
    display: flex;
    flex-wrap: wrap;
  }
  h1 {
    font-size: 25px;
    /* font-weight: bold; */
    margin: 0 1rem;
  }
  .tag.content {
    font-size: 15px;
    line-height: 20px;
    margin: 1rem;
  }
  .tag.synonyms {
    font-size: 14px;
    color: #0074cc;
    margin: 0.5rem 1rem 1rem 1rem;
    &:hover {
      filter: brightness(120%);
    }
  }
`;
const TagsPage = () => {
  const [tags, setTags] = useState([]);
  useEffect(() => {
    axios
      .get("http://3.39.203.17:8080/tags?page=1&size=20")
      .then((res) => setTags(res.data.data.sort((a, b) => a.tagId - b.tagId)))
      .catch((err) => console.log(err));
  }, []);

  return (
    <Main>
      <h1>Tags</h1>
      <p className="tag content">
        A tag is a keyword or label that categorizes your question with other,
        similar questions. Using the right tags makes it easier for others to
        find and answer your question.
      </p>
      <a
        className="tag synonyms"
        href="https://stackoverflow.com/tags/synonyms"
      >
        Show all tag synonyms
      </a>
      <section className="tagsCard">
        <TagsPageCard tags={tags} />
      </section>
    </Main>
  );
};

export default TagsPage;
