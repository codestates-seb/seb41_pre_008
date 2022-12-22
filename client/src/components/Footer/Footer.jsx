import React from "react";
import styled from "styled-components";

const FooterContainer = styled.footer`
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 200px;
  background-color: #282c30;
  padding: 1rem;
`;

const FooterSection = styled.section`
  display: flex;
  flex-direction: column;
  padding: 1rem;
`;

const FooterTitle = styled.h2`
  color: #b7c8da;
  padding: 0.7rem 0.3rem 0.8rem 0.3rem;
  font-size: smaller;
  font-weight: bold;
`;

const FooterContent = styled.a`
  color: #a9b8c8;
  font-size: smaller;
  text-decoration: none;
  padding: 0.3rem;
  &:hover {
    filter: brightness(110%);
  }
`;

const Logo = styled.img`
  display: flex;
  width: 50px;
  height: 50px;
`;

const FooterSideSection = styled.section`
  display: flex;
  flex-direction: column;
  padding: 1rem;
  justify-content: space-between;
`;

const FooterSocialContainer = styled.div`
  display: flex;
  justify-content: space-between;
  max-width: 300px;
  margin: 0.3rem;
`;

const FooterSocialContent = styled.a`
  color: #a9b8c8;
  font-size: 0.75em;
  text-decoration: none;
  padding: 0.3rem;
  &:hover {
    filter: brightness(110%);
  }
`;

const FooterLicense = styled.p`
  color: #a9b8c8;
  font-size: 0.75em;
  padding: 0.3rem;
`;

const FooterSectionCard = ({ title, content1, href1, content2, href2 }) => {
  return (
    <FooterSection>
      <FooterTitle>{title}</FooterTitle>
      <FooterContent href={href1}>{content1}</FooterContent>
      <FooterContent href={href2}>{content2}</FooterContent>
    </FooterSection>
  );
};

const Footer = () => {
  return (
    <FooterContainer>
      <Logo src="https://upload.wikimedia.org/wikipedia/commons/e/ef/Stack_Overflow_icon.svg"></Logo>
      <FooterSectionCard
        title="STACK OVERFLOW"
        content1="Questions"
        href1="https://stackoverflow.com/questions"
        content2="Help"
        href2="https://stackoverflow.com/help"
      />
      <FooterSectionCard
        title="PRODUCTS"
        content1="Teams"
        href1="https://stackoverflow.co/teams/"
        content2="Advertising"
        href2="https://stackoverflow.co/advertising/"
      />
      <FooterSectionCard
        title="COMPANY"
        content1="About"
        href1="https://stackoverflow.co"
        content2="Press"
        href2="https://stackoverflow.co/company/press"
      />
      <FooterSectionCard
        title="STACK EXCHANGE NETWORK"
        content1="Technology"
        href1="https://stackexchange.com/sites#technology"
        content2="Culture & recreation"
        href2="https://stackexchange.com/sites#culturerecreation"
      />
      <FooterSideSection>
        <FooterSocialContainer>
          <FooterSocialContent href="https://stackoverflow.blog/?blb=1&_ga=2.5040435.1040116346.1671407631-25791809.1661736247">
            Blog
          </FooterSocialContent>
          <FooterSocialContent href="https://www.facebook.com/officialstackoverflow/">
            Facebook
          </FooterSocialContent>
          <FooterSocialContent href="https://twitter.com/stackoverflow">
            Twitter
          </FooterSocialContent>
          <FooterSocialContent href="https://www.linkedin.com/company/stack-overflow">
            LinkedIn
          </FooterSocialContent>
          <FooterSocialContent href="https://www.instagram.com/thestackoverflow/">
            Instagram
          </FooterSocialContent>
        </FooterSocialContainer>
        <FooterLicense>
          Site design / logo © 2022 Stack Exchange Inc; user contributions
          licensed under CC BY-SA. rev 2022.12.21.43127
        </FooterLicense>
      </FooterSideSection>
    </FooterContainer>
  );
};

export default Footer;
