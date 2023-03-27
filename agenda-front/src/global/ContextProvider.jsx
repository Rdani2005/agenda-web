import React, { createContext, useEffect, useState } from 'react'
import { getWithToken, postWithToken } from '../api'

export const globalContext = createContext()

/**
 * Global variables used in this web app
 */
const ContextProvider = ({ children }) => {



    // Authentication from user
    const [auth, setAuth] = useState(null)


    // Setting up the profile if there is any available token
    useEffect(() => {
        let mounted = true;

        const token = localStorage.getItem("token")
        if (token) {
            getWithToken("/v1/auth/userinfo")
            .then(({ data }) => {
                setAuth(data)
            })
            .catch(e => {
                localStorage.removeItem("token")
            })
        }

        return () => mounted = false
    }, [setAuth])


    return (
        <globalContext.Provider
            value={{
                auth, setAuth
            }}
        >
            {children}
        </globalContext.Provider>
    )

}

export default ContextProvider