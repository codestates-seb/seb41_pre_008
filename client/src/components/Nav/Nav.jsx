import styled from "styled-components";
import { MdStars } from "react-icons/md";
import { IoMdBriefcase } from "react-icons/io";
import { IoEarth } from "react-icons/io5";

const NavContainer = styled.div`
  width: 164px;
  height: auto;
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
    padding: 8px 6px 8px 0;
  }

  a {
    display: flex;
    font-size: 13px;
    color: #525960;
    padding: 8px 6px 8px 8px;

    &:hover {
      color: #000;
    }
  }

  .active {
    font-weight: bold;
    color: #000;
    background-color: #f1f2f3;
    border-right: 3px solid #f38225;
  }

  .homeActive {
    display: block;
    margin-bottom: 16px;
    padding: 8px 6px 8px 0;
    font-weight: bold;
    color: #000;
    background-color: #f1f2f3;
    border-right: 3px solid #f38225;
  }

  .publicMenu,
  .collectivesMenu,
  .teamsMenu {
    font-size: 11px;
    color: #6a737c;
    margin-bottom: 16px;

    ul {
      span {
        display: block;
      }

      span:first-child {
        width: 18px;
        height: 18px;
      }
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
        <a href="/" className={location === "/" ? "homeActive" : "homeMenu"}>
          Home
        </a>
        <ul>
          <li className="publicMenu">
            PUBLIC
            <ul>
              <li>
                <a
                  href="/questions"
                  className={location === "/questions" ? "active" : null}
                >
                  <span>
                    <IoEarth />
                  </span>
                  <span>Questions</span>
                </a>
              </li>
              <li>
                <a href="#none">
                  <span></span>
                  <span>Tags</span>
                </a>
              </li>
              <li>
                <a href="#none">
                  <span></span>
                  <span>Users</span>
                </a>
              </li>
              <li>
                <a href="#none">
                  <span></span>
                  <span>Companies</span>
                </a>
              </li>
            </ul>
          </li>
          <li className="collectivesMenu">
            COLLECTIVES
            <ul>
              <li>
                <a href="#none">
                  <span>
                    <MdStars color="#f48225" fontSize="13px" />
                  </span>
                  <span>Explore Collectives</span>
                </a>
              </li>
            </ul>
          </li>
          <li className="teamsMenu">
            TEAMS
            <ul>
              <li>
                <a href="#none">
                  <span>
                    <IoMdBriefcase color="#f48225" fontSize="13px" />
                  </span>
                  <span>Create free Team</span>
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </Navbar>
    </NavContainer>
  );
};

export default Nav;
