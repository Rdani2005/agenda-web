import React, { createContext, useState } from 'react'
import { postWithToken } from '../api'

export const globalContext = createContext()

const ContextProvider = () => {

    const [auth, setAuth] = useState({
        id: "",
        identification: "",
        name: "",
        register_day: "",
        email: "",
        login: ""
    })


    const [globalUsers, setGlobalUsers] = useState([])

    const [contacts, setContacts] = useState([])

    useEffect(() => {
        let mounted = true;
        
        const token = localStorage.getItem(token)
        if (token) {
            
        }

        return () => mounted = false
    }, [])



}

export default ContextProvider