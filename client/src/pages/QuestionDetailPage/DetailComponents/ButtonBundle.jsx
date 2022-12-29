import styled from "styled-components";

export const MainButton = styled.a`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  background-color: #0a95ff;
  color: #fff;
  border: 0;
  border-radius: 3px;
  font-size: 13px;
  min-width: 100px;
  max-width: 130px;
  height: 35px;
  margin: 0.4rem 0;
  cursor: pointer;
  &:hover {
    filter: brightness(120%);
  }
`;

export const SideButtonSection = styled.div`
  display: flex;
`;

export const SideButton = styled.button`
  display: flex;
  padding-left: 0;
  padding-right: 0.5rem;
  border: none;
  color: rgb(116, 117, 122);
  background: white;
  cursor: pointer;
  &:hover {
    filter: brightness(110%);
  }
`;

export const CancelButton = styled.a`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  background-color: #fff;
  color: #0a95ff;
  border: 0;
  border-radius: 3px;
  font-size: 13px;
  min-width: 100px;
  max-width: 130px;
  height: 35px;
  margin: 0.4rem 0;
  cursor: pointer;
  &:hover {
    filter: brightness(120%);
  }
`;

export const ButtonContainer = styled.section`
  display: flex;
  align-items: center;
  margin: 0.5rem 2rem;
`;
