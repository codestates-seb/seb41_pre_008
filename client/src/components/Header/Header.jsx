import React from "react";
import styled from "styled-components";

const HeaderWrap = styled.div`
  position: fixed;
  width: 100%;
  height: 53px;
`;

const OrangeBg = styled.div`
  width: 100%;
  height: 3px;
  background-color: #f38225;
`;

const MenuHeader = styled.div`
  top: 0;
  width: 100%;
  height: 50px;
  background-color: #f8f9f9;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2);
`;

const Header = () => {
  return (
    <HeaderWrap>
      <OrangeBg />
      <MenuHeader></MenuHeader>
    </HeaderWrap>
  );
};

export default Header;
