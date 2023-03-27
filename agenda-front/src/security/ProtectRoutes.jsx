import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'
/**
 * Protected Routes for navigation. Checking if user is logged in, or has access to this.
 * @param isAllowed used for checking if user can access to the route.
 * @param children Returning children in case user can access
 * @param redirectTo By default, /login. Used for redirecting in case user is not logged in
 * @returns Depending of the case of use, it can return an aoutlet from React Router Dom, or 
 * a little component
 */
export const Protected = ({ isAllowed, children, redirectTo = '/login' }) => {
    return isAllowed ?  (children ? children : <Outlet />) : <Navigate to={redirectTo} />
}