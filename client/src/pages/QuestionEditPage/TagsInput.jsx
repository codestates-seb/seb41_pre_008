import { useState } from "react";
import styled from "styled-components";
import { RiCloseFill } from "react-icons/ri";

const TagsInputContainer = styled.div`
  display: flex;
  border: 1px solid rgb(169, 170, 178);
  border-radius: 3px;
  padding: 0 4px;
  &:focus-within {
    border-color: #0a95ff;
    box-shadow: 0 0 0 5px #d3ecff;
  }
  ul {
    display: flex;
    flex-wrap: wrap;
    margin: 4px 0 0 0;
    .tag {
      display: flex;
      align-items: center;
      justify-content: center;
      width: auto;
      height: 25px;
      background-color: #e1ecf4;
      color: #39749d;
      padding: 4.8px 6px;
      font-size: 12px;
      list-style: none;
      border-radius: 3px;
      margin: 0 4px 4px 0;
      .tag-close-icon {
        width: 16px;
        height: 16px;
        line-height: 16px;
        text-align: center;
        font-size: 16px;
        color: #39749d;
        border-radius: 3px;
        margin-left: 2px;
        background-color: transparent;
        &:hover {
          cursor: pointer;
          background-color: #39749d;
          color: #fff;
        }
      }
    }
  }
  input {
    flex: 1;
    display: flex;
    border: none;
    border-radius: 3px;
    min-width: 70px;
    &:focus {
      outline: transparent;
    }
  }
`;

const TagsInput = () => {
  const initialTags = [
    { id: 1, tag: "javascript" },
    { id: 2, tag: "python" },
    { id: 3, tag: "c#" },
  ];
  const [tags, setTags] = useState(initialTags);

  const removeTags = (idToRemove) => {
    setTags(tags.filter((el) => el.id !== idToRemove));
  };

  const addTags = (event) => {
    const filtered = tags.filter((el) => el.tag === event.target.value);
    if (event.target.value !== "" && filtered.length === 0) {
      setTags([...tags, { id: Math.random(), tag: event.target.value }]);
      event.target.value = "";
    }
  };

  return (
    <TagsInputContainer>
      <ul id="tags">
        {tags.map((el) => (
          <li key={el.id} className="tag">
            <span className="tag-title">{el.tag}</span>
            <span className="tag-close-icon" onClick={() => removeTags(el.id)}>
              <RiCloseFill className="icon" />
            </span>
          </li>
        ))}
      </ul>
      <input
        className="tag-input"
        type="text"
        onKeyUp={(e) => (e.key === "Enter" ? addTags(e) : null)}
      />
    </TagsInputContainer>
  );
};

export default TagsInput;
