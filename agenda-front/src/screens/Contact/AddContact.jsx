import React, { useEffect, useRef, useState } from 'react'
import { useNavigate, useParams } from 'react-router'
import { deleteWithToken, getWithToken, postWithToken, putWithToken } from '../../api'
import Ball from '../../components/Ball'

const AddContact = () => {

    // navigating
    const navigate = useNavigate()
    //case of consulting
    const { id } = useParams()
    // General data
    const [countries, setCountries] = useState([])
    const [contact_type, setContactType] = useState([])

    //  form data
    const [userId, setId] = useState()
    const [name, setName] = useState()
    const [birth_day, setbirth_day] = useState()
    const [phone, setphone] = useState()
    const [mobile, setmobile] = useState()
    const [fax, setfax] = useState()
    const [email, setemail] = useState()
    const [type, settype] = useState()
    const [country, setcountry] = useState()

    // delete items
    const handleDelete = (e) => {
        e.preventDefault();

        deleteWithToken(`/v1/contacts/${id}`)
            .then(({ data }) => {
                alert("Usuario borrado con exito!")
                navigate('/contacts')
                console.log(data);
            })
            .catch((e) => {
                alert("Hubo un error eliminando el contacto!")
                console.log(e);
            })
    }

    // Adding to BD
    const handleSubmit = (e) => {
        e.preventDefault();

        if (id == null) {
            //add
            postWithToken("/v1/contacts/add", {
                "identification": userId,
                "name": name,
                "birth_day": birth_day,
                "phone": phone,
                "mobile": mobile,
                "fax": fax,
                "email": email,
                "type_id": type,
                "country_id": country
            }).then(({ data }) => {
                alert("Contacto Registrado con exito")
                navigate("/contacts")
            })
                .catch((e) => {
                    alert("Ocurrio un error en el registro de usuario")
                    console.log(e)
                })
        } else {
            // update
            putWithToken("/v1/contacts/${id}", {
                "identification": userId,
                "name": name,
                "birth_day": birth_day,
                "phone": phone,
                "mobile": mobile,
                "fax": fax,
                "email": email,
                "type_id": type,
                "country_id": country
            }).then(({ data }) => {
                alert("Contacto modificado con exito")
                navigate("/contacts")
            })
                .catch((e) => {
                    alert("Ocurrio un error en el registro de usuario")
                    console.log(e)
                })
        }
    }


    // uSED TO GET ALL DATA FROM DB
    useEffect(() => {
        // se hace una modificacion de usuario, no un agregar
        if (id != null) {

            // El usuario existe en bd
            getWithToken(`/v1/contacts/${id}`)
                .then(({ data }) => {
                    setId(data.identification)
                    setName(data.name)
                    setbirth_day(Date.parse(data.birth_day))
                    setphone(data.phone)
                    setemail(data.email)
                    setmobile(data.mobile)
                    setfax(data.fax)
                })
                .catch(e => console.log("No se hizo la peticion API"))
        }
        // countries from Server
        getWithToken('/v1/country/all')
            .then(({ data }) => setCountries(data))
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });

            // Get all types
        getWithToken('/v1/contact-type/all')
            .then(({ data }) => setContactType(data))
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });

    }, [])

    // Rendering obj
    return (
        <>
            <Ball top={'top-[10%]'} xPosition={'right-20'} color={'bg-light-color'}></Ball>
            <Ball top={'top-[10%]'} xPosition={'left-[10%]'} color={'bg-main-color'}></Ball>
            <h1 className='text-6xl font-semibold'>
                {id != null ? "Consulta de usuario" : "Agregar Nuevo Contacto"}
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
                        type="date"
                        placeholder='nacimiento'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={birth_day}
                        onChange={(e) => setbirth_day(e.target.value)}
                    />

                    <input
                        type="tel"
                        placeholder='telefono'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={phone}
                        onChange={(e) => setphone(e.target.value)}
                    />

                    <input
                        type="tel"
                        placeholder='Celular'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={mobile}
                        onChange={(e) => setmobile(e.target.value)}
                    />

                    <input
                        type="fax"
                        placeholder='Fax'
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        value={fax}
                        onChange={(e) => setfax(e.target.value)}
                    />

                    <input
                        type="email"
                        placeholder='email'
                        value={email}
                        className='mt-10 w-3/4 h-[30px] bg-transparent mx-auto border-b border-b-text-color focus:outline-none'
                        required
                        onChange={(e) => setemail(e.target.value)}
                    />


                    <select
                        name="type"
                        id=""
                        className='mt-10 w-3/4 h-[30px] mx-auto border-b border-b-text-color focus:outline-none bg-bg-color bg-opacity-20 backdrop-blur-lg'
                        onChange={(e) => settype(e.target.value)}
                    >
                        <option value="-1">tipo de contacto</option>
                        {
                            contact_type && contact_type.map(c => (
                                <option key={c.id} value={c.id}>{c.name}</option>
                            ))
                        }
                    </select>


                    <select
                        name="country"
                        id=""
                        className='mt-10 w-3/4 h-[30px] mx-auto border-b border-b-text-color focus:outline-none bg-bg-color bg-opacity-20 backdrop-blur-lg'
                        onChange={(e) => setcountry(e.target.value)}
                    >
                        <option value="-1">pais</option>
                        {
                            countries && countries.map(country => (
                                <option key={country.id} value={country.id}>{country.name}</option>
                            ))
                        }
                    </select>

                    {
                        id == null &&
                        <button
                            type='submit'
                            className='bg-main-color mt-10 w-3/4 h-[60px] mx-auto mb-10 text-text-color'

                        >
                            Registrar
                        </button>
                    }

                    {
                        id != null && (
                            <div className='flex w-full'>
                                <button
                                    type='submit'
                                    className='bg-main-color mt-10 w-[45%] h-[60px] mx-auto mb-10 text-text-color'

                                >
                                    Modificar
                                </button>
                                <button
                                    type='submit'
                                    className='bg-[#8f1010] mt-10 w-[45%] h-[60px] mx-auto mb-10 text-text-color'
                                    onClick={handleDelete}

                                >
                                    Borrar
                                </button>
                            </div>
                        )
                    }
                </form>
            </main>
        </>
    )
}

export default AddContact