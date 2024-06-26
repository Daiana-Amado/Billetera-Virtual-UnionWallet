import { createContext, useContext } from "react";
import PropTypes from 'prop-types';

const TransferNotificationContext = createContext();

export function useTransferNotification() {
    return useContext(TransferNotificationContext);
}

export function TransferNotificationProvider({ children }) {
    const sendTransferNotification = async (email, referenceNumber) => {
        try {
            const apiUrl = import.meta.env.VITE_BACKEND_URL;
            const response = await fetch(`${apiUrl}/api/v1/mensajes/transferencia/${email}/${referenceNumber}`, {
                method: 'POST',
            });
            const data = await response.json();
            if (!response.ok) {
                throw new Error(data.message || "Error al enviar la notificación de transferencia.");
            }
            console.log("Notificación enviada correctamente:", data);
        } catch (error) {
            console.error("Error al enviar la notificación de transferencia:", error);
        }
    };

    return (
        <TransferNotificationContext.Provider value={{ sendTransferNotification }}>
            {children}
        </TransferNotificationContext.Provider>
    );
}
TransferNotificationProvider.propTypes = {
    children: PropTypes.node.isRequired,
};
