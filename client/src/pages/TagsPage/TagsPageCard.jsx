import React from "react";
import styled from "styled-components";

const TagsDetailCard = styled.section`
  display: flex;
  flex-direction: column;
  width: 232px;
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
  p {
    overflow-y: auto;
    height: 200px;
    font-size: 13px;
    margin: 0 1rem;
    /* padding: 0 0 1rem 0; */
    line-height: 15px;
  }
  span {
    color: rgb(169, 170, 178);
    font-size: 12px;
    margin: 0 1rem;
    padding: 1rem 0;
  }
`;

const TagsPageCard = ({ tags }) => {
  console.log(tags);
  return (
    <>
      {tags.map((tag) => (
        <TagsDetailCard>
          <a key={tag.tagId} href="/">
            {tag.name}
          </a>
          <p>{tag.content}</p>
          <span>2462027 questions</span>
        </TagsDetailCard>
      ))}
    </>
  );
};

export default TagsPageCard;
