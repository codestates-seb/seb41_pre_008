import React from 'react';
import styled from 'styled-components';

const MenuHeader = styled.div`
    position: fixed;
    top: 0;
    width: 100%;
    height: 50px;
    background-color: #f48225;
`

const Header = () => {
    return (
        <MenuHeader></MenuHeader>
    )
}

export default Header;