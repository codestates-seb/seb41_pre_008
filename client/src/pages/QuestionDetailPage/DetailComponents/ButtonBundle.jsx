import styled from "styled-components";

export const MainButton = styled.button`
  width: 100px;
  height: 35px;
  border: none;
  color: white;
  background-color: #0a95ff;
  border-radius: 5px;
  margin: 0.4rem 0;
  &:hover {
    filter: brightness(120%);
  }
`;

export const SideButtonSection = styled.div`
  display: flex;
`;

export const SideButton = styled.button`
  border: none;
  color: rgb(116, 117, 122);
  background: white;
  &:hover {
    filter: brightness(110%);
  }
`;
