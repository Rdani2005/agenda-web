import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import "./css/style.css"
import ContextProvider from './global/ContextProvider'
/**
 * Indicating the app exists!
 */
ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <ContextProvider>
            <App />
        </ContextProvider>
    </React.StrictMode>,
)
