import { useState } from "react";
import styled from "styled-components";

export const TagsInput = styled.div`
  display: flex;
  /* flex-direction: column; */
  margin: 1rem 2rem;
  border: 1px solid rgb(169, 170, 178);
  border-radius: 3px;
  padding: 0 0.5rem;
  &:focus {
    outline: none !important;
    border-color: #0a95ff;
    box-shadow: 0 0 0 5px #d3ecff;
  }
  > ul {
    display: flex;
    flex-wrap: wrap;
    padding: 0;
    margin: 8px 0 0 0;
    > .tag {
      width: auto;
      height: 32px;
      display: flex;
      align-items: center;
      justify-content: center;
      color: #fff;
      padding: 0 8px;
      font-size: 14px;
      list-style: none;
      border-radius: 6px;
      margin: 0 8px 8px 0;
      background: #4000c7;
      > .tag-close-icon {
        display: block;
        width: 16px;
        height: 16px;
        line-height: 16px;
        text-align: center;
        font-size: 14px;
        margin-left: 8px;
        color: #4000c7;
        border-radius: 50%;
        background: #fff;
        cursor: pointer;
      }
    }
  }
  > input {
    flex: 1;
    /* border: none;
    height: 46px;
    font-size: 14px;
    padding: 4px 0 0 0; */
    display: flex;
    padding: 0.5rem;
    border: none;
    /* border: 1px solid rgb(169, 170, 178); */
    border-radius: 3px;
    &:focus {
      outline: transparent;
    }
  }
`;

export const Tag = () => {
  // const selectedTags = (tags) => console.log(tags);
  const initialTags = ["CodeStates", "kimcoding"];

  const [tags, setTags] = useState(initialTags);
  const removeTags = (indexToRemove) => {
    setTags(tags.filter((_, index) => index !== indexToRemove));
  };

  const addTags = (event) => {
    const filtered = tags.filter((el) => el === event.target.value);
    if (event.target.value !== "" && filtered.length === 0) {
      setTags([...tags, event.target.value]);
      // selectedTags([...tags, event.target.value]);
      event.target.value = "";
    }
  };

  return (
    <>
      <TagsInput>
        <ul id="tags">
          {tags.map((tag, index) => (
            <li key={index} className="tag">
              <span className="tag-title">{tag}</span>
              <span
                className="tag-close-icon"
                onClick={() => removeTags(index)}
              >
                &times;
              </span>
            </li>
          ))}
        </ul>
        <input
          className="tag-input"
          type="text"
          onKeyUp={(event) => (event.key === "Enter" ? addTags(event) : null)}
          placeholder="Press enter to add tags"
        />
      </TagsInput>
    </>
  );
};