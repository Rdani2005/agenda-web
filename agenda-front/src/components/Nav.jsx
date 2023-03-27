import React, { useContext } from 'react'
import { NavLink } from 'react-router-dom'
import { globalContext } from '../global/ContextProvider'

/**
 * IMPORTANT: This is a jsx component handling routes with React Router DOM
 * 
 * As we are not using NextJs, we implements a React Library, and theres a lot of
 * conditional working in here. This is just because of animation porpuses, and active.
 * 
 * Let's consider we need to handle if a route is available or not in that specific moment.
 */



/**
 * A component representing a navigation bar with links.
 *
 * @returns {JSX.Element} - The JSX for the navigation bar.
 */
const Nav = () => {
    // handling auth
    const { auth, setAuth } = useContext(globalContext)
    // login out
    const logOut = () => {
        localStorage.removeItem("token")
        setAuth(null)
    }

    // HERE WE FOUND A NAV ITEM. It will have all methods available and log out
    return (
        <nav className='flex justify-between py-9'>
            <h1 className='font-sans font-bold text-trihead'>
                Personal Agenda
            </h1>
            <div className="md:flex font-sans text-trihead hidden">
                <NavLink
                    to="/"
                    className={({ isActive }) => isActive ?
                        'text-active-color mr-5'
                        : 'text-innactive-color mr-5 transition-all hover:text-active-color'}
                >
                    Home
                </NavLink>
                <NavLink
                    to="/contacts"
                    className={({ isActive }) => isActive ?
                        'text-active-color mr-5'
                        : 'text-innactive-color mr-5 transition-all hover:text-active-color'}
                >

                    Reporte de Contactos
                </NavLink>
                <NavLink
                    to="/contacts/add"
                    className={({ isActive }) => isActive ?
                        'text-active-color mr-5'
                        : 'text-innactive-color mr-5 transition-all hover:text-active-color'}
                >
                    Agregar Contactos
                </NavLink>
                <NavLink
                    to="/users"
                    className={({ isActive }) => isActive ?
                        'text-active-color mr-5'
                        : 'text-innactive-color mr-5 transition-all hover:text-active-color'}
                >

                    Reporte de Usuarios
                </NavLink>
                <NavLink
                    to="/users/add"
                    className={({ isActive }) => isActive ?
                        'text-active-color mr-5'
                        : 'text-innactive-color mr-5 transition-all hover:text-text-color'}
                >
                    Agregar Usuarios
                </NavLink>
                <p
                    onClick={() => logOut()}
                    className='text-innactive-color mr-5 transition-all hover:text-text-color cursor-pointer'
                >
                    Salir
            </p>
        </div>
        </nav >
    )
}

export default Nav