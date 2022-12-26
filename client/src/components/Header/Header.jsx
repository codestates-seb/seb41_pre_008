import React, { useState } from "react";
import styled from "styled-components";
import Nav from "../Nav/Nav";
import stacklogo from "../../img/Login/stacklogo.png";
import menu from "../../img/Login/menu.png";
import search from "../../img/Login/search.png";

const HeaderWrap = styled.div`
  top: 0;
  position: fixed;
  width: 100%;
  height: 53px;
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2);
  background-color: #f8f9f9;
`;

const OrangeBg = styled.div`
  width: 100%;
  height: 3px;
  background-color: #f38225;
`;

const MenuHeader = styled.div`
  width: 1264px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-left: auto;
  margin-right: auto;
`;

const LogoImg = styled.img`
  width: 150px;
`;

const MenuImg = styled.img`
  width: 24px;
`;

const Menu = styled.div`
  color: #525960;
  font-size: 14px;
  margin-top: 4px;
`;

const SearchWrap = styled.div`
  position: relative;
  width: 656px;
  height: 32.5px;
`;

const SearchBox = styled.input`
  width: 656px;
  height: 32.5px;
  padding-left: 30px;
`;

const SearchImg = styled.img`
  position: absolute;
  width: 24px;
  left: 5px;
  top: 6px;
`;

const LoginButton = styled.a`
  width: 59.5px;
  height: 33px;
  color: #39739d;
  background-color: #e1ecf4;
  font-size: 13px;
  border: 1px solid rgb(122, 167, 199);
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const SignUpButton = styled.a`
  width: 68.4px;
  height: 33px;
  color: white;
  background-color: rgb(10, 149, 255);
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 3px;
  border: 1px solid rgb(255, 255, 255, 0);
  font-size: 13px;
  position: relative;
  right: 14px;
`;

const Header = () => {
  const [isLogin, setIsLogin] = useState(
    JSON.parse(window.localStorage.getItem("user"))
  );

  return (
    <>
      <HeaderWrap>
        <OrangeBg />
        <MenuHeader>
          <MenuImg src={menu} />
          <a href="/">
            <LogoImg src={stacklogo} />
          </a>
          <Menu>About</Menu>
          <Menu>Products</Menu>
          <Menu>For Teams</Menu>
          <SearchWrap>
            <SearchBox placeholder="Search..." />
            <SearchImg src={search} />
          </SearchWrap>
          <LoginButton href="/login">Log in</LoginButton>
          <SignUpButton href="/signup">Sign up</SignUpButton>
        </MenuHeader>
      </HeaderWrap>
      <Nav />
    </>
  );
};

export default Header;
