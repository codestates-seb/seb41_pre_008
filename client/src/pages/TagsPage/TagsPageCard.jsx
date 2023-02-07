import React from "react";
import styled from "styled-components";

const TagsDetailCard = styled.section`
  display: flex;
  flex-direction: column;
  @media screen and (max-width: 900px) {
    width: 300px;
  }
  @media screen and (min-width: 900px) {
    width: 234px;
  }
  height: 175px;
  border: 1px solid rgb(169, 170, 178);
  border-radius: 3px;
  margin: 0.5rem 0 1rem 1rem;
  a {
    width: fit-content;
    font-size: 13px;
    height: 23px;
    background-color: #e1ecf4;
    color: #39749d;
    padding: 4.8px 6px;
    margin: 1rem;
    border-radius: 3px;
  }
  .tags.content {
    overflow-y: auto;
    height: 200px;
    font-size: 13px;
    margin: 0 1rem;
    line-height: 15px;
  }
  span {
    color: rgb(153, 154, 162);
    font-size: 12px;
    margin: 0 1rem;
    padding: 1rem 0;
  }
  .tags.view {
    display: flex;
    justify-content: space-between;
  }
`;

const TagsPageCard = ({ tags }) => {
  return (
    <>
      {tags.map((tag) => (
        <TagsDetailCard key={tag.tagId}>
          <a href="/">{tag.name}</a>
          <p className="tags content">{tag.content}</p>
          <p className="tags view">
            <span>
              {Math.floor(Math.random() * (600000 - 100000) + 100000)} questions
            </span>
            <span>
              {Math.floor(Math.random() * (1000 - 100) + 100)} asked today,{" "}
              {Math.floor(Math.random() * (10000 - 1000) + 1000)} this week
            </span>
          </p>
        </TagsDetailCard>
      ))}
    </>
  );
};

export default TagsPageCard;
