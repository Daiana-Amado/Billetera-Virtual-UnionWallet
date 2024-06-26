import Box from '@mui/material/Box';
import { DataGrid, GridToolbar  } from '@mui/x-data-grid';
import { useMediaQuery } from '@mui/material';
import { useAccounts } from '../../context/AccountContext';
import {  useMemo } from 'react';


export default function DataGridDemo() {

  const { accounts } = useAccounts();
  const isMobile = useMediaQuery('(max-width:600px)');
  const width = !isMobile ? 1000 : 450;

  const transactions = useMemo(() => {

  let allTransactions = [];

  function formatDate(dateString) {
    const [year, month, day] = dateString.split('-');
    return `${day}/${month}/${year}`;
  }
  
  accounts.forEach(account => {
      if (account.transferenciasRealizadas && account.transferenciasRealizadas.length > 0) {
          allTransactions = allTransactions.concat(account.transferenciasRealizadas.map(trans => ({
              ...trans,
              tipo: 'Realizada',
              fechaEjecucion: formatDate(trans.fechaEjecucion.split("T")[0])
          })));
      }
      if (account.transferenciasRecibidas && account.transferenciasRecibidas.length > 0) {
          allTransactions = allTransactions.concat(account.transferenciasRecibidas.map(trans => ({
              ...trans,
              fechaEjecucion: formatDate(trans.fechaEjecucion.split("T")[0]),
              tipo: 'Recibida'
          })));
      }
  });
  return allTransactions;
}, [accounts]);


  const columns = [
    { field: 'numReferencia',  headerName: 'N° de REF', width: 100, align: 'center', renderHeader: () => (<strong> N° de REF</strong> )},
    { field: 'tipo',  headerName: 'Tipo de transacción', width: 150, align: 'center', renderHeader: () => (<strong>Tipo de Transacción</strong>) },
    { field: 'cuentaBancariaOrigen',  headerName: 'Cuenta origen',  width: 150, align: 'center', renderHeader: () => (<strong>Cuenta de origen</strong>) },
    { field: 'cuentaBancariaDestino',  headerName: 'Cuenta destino', width: 150, align: 'center', renderHeader: () => (<strong>Cuenta destino</strong>) },
    { field: 'monto', headerName: 'Valor',  width: 100, align: 'center', renderHeader: () => (<strong>Valor</strong>) },
    { field: 'fechaEjecucion', headerName: 'Fecha de transacción',  type: String, width: 170, align: 'center' , renderHeader: () => (<strong>Fecha de ejecucion</strong>) },
    { field: 'descripcion', headerName: 'Descripción', width: 150, align: 'center', renderHeader: () => (<strong>Descripción</strong>) },
  ];


  const getRowId = (row) => row.numReferencia;

  return (
    <Box sx={{ height: 400, width, maxWidth:'1', mb:15}}>
      <DataGrid      
        getRowId={getRowId}
        rows={transactions}
        columns={columns}
        slots={{ toolbar: GridToolbar }}
        initialState={{
          sorting: {
            sortModel: [{ field: 'fechaEjecucion', sort: 'desc' }],
          },
          pagination: {
            paginationModel: {
              pageSize: 5,
            },
          },
        }}
        pageSizeOptions={[5, 10, 25]}        
        disableRowSelectionOnClick
      />
    </Box>
  );
}