import  { createContext, useState, useContext } from 'react';
import PropTypes from 'prop-types';

const UserContext = createContext();

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {

    const [userContext, setUserContext] = useState(() => {
        const savedUserData = sessionStorage.getItem('user');
        return savedUserData ? JSON.parse(savedUserData) : null;
    });
    
    return (
        <UserContext.Provider value={{userContext, setUserContext }}>
        {children}
        </UserContext.Provider>
    );
};
UserProvider.propTypes = {
    children: PropTypes.node.isRequired,
};
