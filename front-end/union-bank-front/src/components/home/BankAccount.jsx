import { CardBankAccount } from "./CardAccount";
import {CircularProgress, Grid} from '@mui/material';
import Box from '@mui/material/Box';
import { useAccounts } from "../../context/AccountContext";
import { useUser } from "../../context/UserContext";
import { useEffect } from "react";

const BankAccount = () => {
    const { accounts, loading, loadAccount  } = useAccounts();
    const { userContext } = useUser();
    // const [page, setPage] = useState(1);

    useEffect(() => {
        if (userContext.user && userContext.user.cuentas) {
            const accountNumbers = userContext.user.cuentas.map(cuenta => cuenta.numCuenta);
            loadAccount(accountNumbers);
        }
    }, [userContext, loadAccount]);

    if (loading) {
        return <Box sx={{ display: 'flex' }}>
        <CircularProgress />
      </Box>
    }
    // const cuentasPerPage =2; 

    // const handleChangePage = (event, newPage) => {
    //     setPage(newPage);
    // };

    // const startIndex = (page - 1) * cuentasPerPage;
    // const endIndex = startIndex + cuentasPerPage;
    // const cuentasToShow = accounts.slice(startIndex, endIndex);
    return (
        <Box sx={{display:'flex', flexDirection:'column', alignItems:'center', }}  >
            <Grid container spacing={2} sx={{display:'flex', flexDirection:'row', flexWrap:'nowrap' , justifyContent:'center',  }} >            
            {accounts && accounts.map((cuenta) => (
                <Grid item key={cuenta.numCuenta}  xs={12} sm={12} md={12} lg={12}>
                    <CardBankAccount cuenta={cuenta} />
                </Grid>
            ))}
            </Grid>
            <Box mt={2} display="flex" justifyContent="center">
            {/* <Pagination count={Math.ceil(accounts.length / cuentasPerPage)} page={page} onChange={handleChangePage} /> */}
            </Box>
        </Box>
    );
};

export default BankAccount;