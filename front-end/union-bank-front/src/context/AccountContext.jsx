import { createContext, useContext, useState, useEffect, useCallback, useMemo } from 'react';
import PropTypes from 'prop-types';
import { useUser } from './UserContext';

const AccountContext = createContext();

export const useAccounts = () => useContext(AccountContext);


export const AccountProvider = ({ children }) => {

    const [accounts, setAccounts] = useState([]);
    const [loading, setLoading] = useState(true);
    const {userContext} = useUser();
    const apiUrl =import.meta.env.VITE_BACKEND_URL;

    const loadAccount = useCallback(async (accountNumbers) => {
    setLoading(true);
    const loadedAccounts = [];
    try {
        for (const numCuenta of accountNumbers) {
            const response = await fetch(`${apiUrl}/api/v1/cuenta/${numCuenta}`);
            if (!response.ok) {
                throw new Error(`Failed to fetch account ${numCuenta}`);
            }
            const accountData = await response.json();
            loadedAccounts.push(accountData);
        }
        setAccounts(loadedAccounts);
    } catch (error) {
        console.error('Error loading accounts:', error.message);
    } finally {
        setLoading(false);
    }
}, [apiUrl]);

    useEffect(() => {
        if (userContext && userContext?.user?.cuentas) {
            const accountNumbers = userContext.user.cuentas.map(cuenta => cuenta.numCuenta);
            loadAccount(accountNumbers);
        }
    }, [userContext, loadAccount]); 

    const value = useMemo(() => ({
        accounts,
        loading,
        setAccounts,
        loadAccount
    }), [accounts, loading, loadAccount]);

    return (
        <AccountContext.Provider value={value}>
            {children}
        </AccountContext.Provider>
    );
};

AccountProvider.propTypes = {
    children: PropTypes.node.isRequired,
};
