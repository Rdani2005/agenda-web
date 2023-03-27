import { BrowserRouter, Route, Routes } from "react-router-dom";
import Nav from "./components/Nav";
import ContactHome from "./screens/Contact";
import AddContact from "./screens/Contact/AddContact";
import Home from "./screens/Home";
import ReportUsers from "./screens/users";
import AddUser from "./screens/users/AddUser";
import Login from './screens/auth/Login'
import { useContext } from "react";
import { globalContext } from "./global/ContextProvider";
import { Protected } from "./security/ProtectRoutes";

/**
 * Handling all routes for the App.
 * @returns Main React Application
 */
const App = () => {

    const { auth } = useContext(globalContext)

    return (
        <BrowserRouter>

            {
                !!auth && <Nav></Nav>
            }
            <Routes>

                <Route element={<Protected isAllowed={!(!!auth)} redirectTo="/" />}>
                    <Route path='/login' element={<Login />} />
                </Route>

                <Route element={<Protected isAllowed={(!!auth)} redirectTo="/login" />}>
                    <Route path="/" element={<Home />}></Route>
                    <Route path="/contacts" element={<ContactHome />}></Route>
                    <Route path="/users" element={<ReportUsers />}></Route>
                    <Route path="/contacts/add" element={<AddContact />}></Route>
                    <Route path="/contacts/:id" element={<AddContact />}></Route>
                    <Route path="/users/add" element={<AddUser />}></Route>
                    <Route path="/users/:id" element={<AddUser />}></Route>
                </Route>

            </Routes>
        </BrowserRouter>
    );

}

export default App