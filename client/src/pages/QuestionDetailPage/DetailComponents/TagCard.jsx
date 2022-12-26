import React from "react";
import styled from "styled-components";

const TagContainer = styled.div`
  display: flex;
  flex: 1;
  justify-content: flex-start;
  align-items: center;
  margin-top: 0.5rem;
`;

const Tag = styled.a`
  display: flex;
  font-size: 12px;
  background-color: #e1ecf4;
  color: #39749d;
  padding: 4.8px 6px;
  margin-right: 0.4rem;
  border-radius: 3px;
  height: 25px;
  align-items: center;
  &:hover {
    filter: brightness(97%);
  }
`;

const TagCard = ({ tags }) => {
  return (
    <TagContainer>
      {tags.map((tag) => (
        <Tag href="/" key={tag.id}>
          {tag.tag}
        </Tag>
      ))}
    </TagContainer>
  );
};

export default TagCard;
