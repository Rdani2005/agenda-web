import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router'
import { getWithToken, postWithToken } from '../../api'
import Ball from '../../components/Ball'

const AddUser = () => {
    const navigate = useNavigate()

    const { id } = useParams()

    const [user_role, setUserRole] = useState([])

    //  form data
    const [userId, setId] = useState("")
    const [name, setName] = useState("")

    const [email, setemail] = useState("")

    const [roleId, setRoleId] = useState(0)

    const [password, setPassword] = useState("")
    const [username, setUsername] = useState("")

    // Adding to BD
    const handleSubmit = (e) => {
        e.preventDefault();

        postWithToken("/v1/auth/register",
            {
                "identification": userId,
                "name": name,
                "email": email,
                "login": username,
                "password": password,
                "roleIds": [
                    roleId
                ]
            }

        ).then(({ data }) => {
            alert("Usuario Registrado con exito")
            navigate("/users")
        })
            .catch((e) => {
                alert("Ocurrio un error en el registro de usuario")
                console.log(e)
            })
    }


    // uSED TO GET ALL DATA FROM DB
    useEffect(() => {

        if (id != null) {
            // El usuario existe en bd
            getWithToken(`/v1/users/${id}`)
                .then(({ data }) => {
                    console.log(data);
                    setId(data.identification)
                    setName(data.name)
                    setemail(data.email)
                    setUsername(data.login)
                    setRoleId(data.roles[0].id)
                })
                .catch(e => console.log("No se hizo la peticion API"))
        }

        // obtener tipo de usuarios
        getWithToken('/v1/user-role/all')
            .then(({ data }) => setUserRole(data))
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });
    }, [])

    return (
        <>
            <Ball top={'top-[10%]'} xPosition={'right-20'} color={'bg-light-color'}></Ball>
            <Ball top={'top-[10%]'} xPosition={'left-[10%]'} color={'bg-main-color'}></Ball>
            <h1 className='text-6xl font-semibold'>
                {
                    id != null ? "COnsultar usuario" : "Agregar Nuevo Usuario"
                }
            </h1>
            <main className='flex items-center justify-center w-full h-screen'>


                <form
                    onSubmit={handleSubmit}
                    className='flex flex-col w-3/4  bg-text-color bg-opacity-20 backdrop-blur-lg mx-auto my-auto'
                >
                    <input
                        type="text"
                        placeholder='Identificación'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={userId}
                        onChange={(e) => setId(e.target.value)}
                    />

                    <input
                        type="text"
                        placeholder='Nombre'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />

                    <input
                        type="text"
                        placeholder='username'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />



                    <input
                        type="email"
                        placeholder='email'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={email}
                        onChange={(e) => setemail(e.target.value)}
                    />

                    {
                        id == null && <input
                            type="password"
                            placeholder='contraseña'
                            className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                            required
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    }


                    <select
                        name="type"
                        id=""
                        className='mt-10 w-3/4 h-[30px] mx-auto border-b border-b-text-color focus:outline-none bg-bg-color bg-opacity-20 backdrop-blur-lg'
                        onChange={(e) => setRoleId(e.target.value)}
                    >
                        <option value="-1">Role de usuario</option>
                        {
                            user_role && user_role.map(c => (
                                <option key={c.id} value={c.id}>{c.name}</option>
                            ))
                        }
                    </select>


                    <button
                        type='submit'
                        className='bg-main-color mt-10 w-3/4 h-[60px] mx-auto mb-10 text-text-color'

                    >
                        Registrar
                    </button>
                </form>
            </main>
        </>
    )
}

export default AddUser