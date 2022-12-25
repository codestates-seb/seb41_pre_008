import styled from "styled-components";
import { MdStars } from "react-icons/md";
import { IoMdBriefcase } from "react-icons/io";
import { IoEarth } from "react-icons/io5";

const NavContainer = styled.div`
  position: fixed;
  top: 50px;
  width: 164px;
  height: 100vh;
  margin-left: 50px;
  padding-top: 24px;
  border-right: 1px solid #d6d9dc;
  background-color: transparent;
`;

const Navbar = styled.nav`
  width: 100%;
  height: 360px;
  background-color: #fff;

  .homeMenu {
    display: block;
    margin-bottom: 16px;
    font-size: 13px;
  }

  .publicMenu,
  .collectivesMenu,
  .teamsMenu {
    display: flex;
    flex-direction: column;
    font-size: 11px;
    color: #6a737c;
    margin-bottom: 16px;

    a {
      font-size: 13px;
      color: #000;
    }

    span,
    li {
      font-size: 13px;
      color: #000;
    }
  }
`;

const Nav = () => {
  let location = window.location.pathname;
  if (
    location === "/login" ||
    location === "/signup" ||
    location === "/questions/ask"
  )
    return null;

  return (
    <NavContainer>
      <Navbar>
        <a href="/" className="homeMenu">
          Home
        </a>
        <ol>
          <li className="publicMenu">
            PUBLIC
            <li>
              <IoEarth />
              <a href="/questions">Questions</a>
            </li>
            <li>Tags</li>
            <li>Users</li>
            <li>Companies</li>
          </li>
          <li className="collectivesMenu">
            COLLECTIVES
            <li>
              <MdStars color="#f48225" fontSize="13px" />
              <span>Explore Collectives</span>
            </li>
          </li>
          <li className="teamsMenu">
            TEAMS
            <li>
              <IoMdBriefcase color="#f48225" fontSize="13px" />
              <span>Create free Team</span>
            </li>
          </li>
        </ol>
      </Navbar>
    </NavContainer>
  );
};

export default Nav;
