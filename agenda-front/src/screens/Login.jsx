import React from 'react'
import Ball from '../components/Ball'

/**
 * A React functional component representing a login form.
 * 
 * @returns {JSX.Element} A React component that displays a login form with two input fields for username and password, and a submit button.
 */
const Login = () => {

    // Declare state variables for the username and password inputs
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    // Handle form submission
    const handleSubmit = (event) => {
        event.preventDefault();
        // Make API call to authenticate user
        post("/v1/auth/login", {
            "login": username,
            "password": password
        })
            .then(({ data }) => {
                console.log(data)
                // Store token in local storage
                localStorage.setItem("token", data.token)
            })
            .catch((e) => console.log(e))
    }


    // Render login form
    return (
        <main className='flex items-center justify-center w-full h-screen'>
            <Ball top={'top-[60%]'} xPosition={'right-20'} color={'bg-light-color'}></Ball>
            <Ball top={'top-[10%]'} xPosition={'left-[10%]'} color={'bg-main-color'}></Ball>

            <form
                onSubmit={handleSubmit}
                className='flex flex-col w-3/4  bg-text-color bg-opacity-20 backdrop-blur-lg mx-auto my-auto'
            >
                <h2 className="text-6xl text-center mt-10">Agenda Personal</h2>
                <h3 className="text-2x text-center mt-2">Hecho por: Danny Sequeira</h3>

                <h2 className="text-4xl text-center mt-10">Ingresar</h2>
                <input
                    type="text"
                    placeholder='Username'
                    className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                    required
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    type="password"
                    placeholder='Contraseña'
                    className='mt-20 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                    required
                    onChange={(e) => setPassword(e.target.value)}
                />
                <button
                    type='submit'
                    className='bg-main-color mt-10 w-3/4 h-[60px] mx-auto mb-10 text-bg-color'

                >
                    Ingresar
                </button>
            </form>
        </main>
    )
}

export default Login