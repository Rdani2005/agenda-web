import React, { useEffect, useState } from 'react'
import { getWithToken } from '../../api'
import Ball from '../../components/Ball'
import TableRow from '../../components/TableRow'

/**
 * Report all users on BD
 * @returns JSX Element
 */
const ReportUsers = () => {
    const [users, setUsers] = useState([])

    const [user_role, setUserRole] = useState([])


    // uSED TO GET ALL DATA FROM DB
    useEffect(() => {

        // obtener tipo de usuarios
        getWithToken('/v1/user-role/all')
            .then(({ data }) => setUserRole(data))
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });
        // obtener todos los usuarios
        getWithToken('/v1/users/all')
            // successful
            .then(({ data }) => {
                setUsers(data)
            })
            // error
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });

    }, [])


    // View
    return (
        <>
            <Ball top={'top-[10%]'} xPosition={'right-20'} color={'bg-light-color'}></Ball>
            <Ball top={'top-[10%]'} xPosition={'left-[10%]'} color={'bg-main-color'}></Ball>
            <h1 className='text-6xl font-semibold'>
                Usuarios Registrados
            </h1>
            <div className="flex justify-between mt-24">
                <select
                    name="type-filter"
                    id=""
                    className='bg-bg-color bg-opacity-20 backdrop-blur-lg w-36 h-8 rounded'
                // onChange={(e) => filterContacts(e)}
                >
                    <option value="-1">Filtro por rol</option>
                    {
                        user_role && user_role.map(c => (
                            <option key={c.id} value={c.id}>{c.name}</option>
                        ))
                    }
                </select>


            </div>

            <section className=''>
                <div className='w-full'>

                    <table className='w-full text-left my-40'>
                        <thead>
                            <tr>
                                <th className='pl-5'>Nombre</th>
                                <th>Rol:</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {/* Renderizando contactos en BD */}
                            {
                                users && users.map((user, i) => (
                                    <TableRow key={i} name={user.name} type={user.roles[0].name} link={`/users/${user.id}`} />
                                ))
                            }
                        </tbody>

                    </table>
                </div>
            </section>
        </>
    )
}

export default ReportUsers