import { BrowserRouter , Routes, Route, Navigate } from 'react-router-dom';
import {Register, Login, MyProfile, Transfer, Home, AccountDetail} from './routes';
import FooterLayout from './components/layouts/footer/FooterLayout';
import { ThemeProvider } from '@emotion/react';
import theme from './theme/Theme';
import { UserProvider } from './context/UserContext';
import { AccountProvider } from './context/AccountContext';
import { TransferNotificationProvider } from './context/TransferNotificationContext';

function App() {

  return (    
      <ThemeProvider theme={theme}>
        <UserProvider>
          <AccountProvider >
            <TransferNotificationProvider>              
              <BrowserRouter>
                <Routes>
                <Route path="/" element={<Navigate replace to="/login" />} />
                  <Route path="/home" element={<Home/>}/>
                  <Route path="/register" element={<Register/>}/> 
                  <Route path="/login" element={<Login/>}/>
                  <Route path="/myProfile" element={<MyProfile/> }/>
                  <Route path="/accountDetail/:id" element={<AccountDetail/>}/>      
                  <Route path="/transfer" element={<Transfer/>}/>        
                </Routes>
              </BrowserRouter>     
            </TransferNotificationProvider>           
          </AccountProvider>                
        </UserProvider> 
        <FooterLayout />  
      </ThemeProvider>      
  );
}

export default App;