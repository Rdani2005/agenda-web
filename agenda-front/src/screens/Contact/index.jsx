import React, { useEffect, useState } from 'react'
import { getWithToken } from '../../api';
import Ball from '../../components/Ball';
import TableRow from '../../components/TableRow'


/**
 * Contact home page
 * @returns React Page of all user contacts
 */
const ContactHome = () => {


    const [contacts, setContacts] = useState([])

    const [countries, setCountries] = useState([])
    const [contact_type, setContactType] = useState([])


    // uSED TO GET ALL DATA FROM DB
    useEffect(() => {
        // getting countries
        getWithToken('/v1/country/all')
            .then(({ data }) => setCountries(data))
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });

            //getting contacts types
        getWithToken('/v1/contact-type/all')
            .then(({ data }) => setContactType(data))
            .catch((e) => {
                console.log("No se hizo peticion API");
                console.log(e)
            });
            // getting all contacts
        getWithToken('/v1/contacts/all')
            // successful
            .then(({ data }) => {
                setContacts(data)
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
                Contactos Registrados
            </h1>
            <div className="flex justify-between mt-24">
                <select
                    name="type-filter"
                    id=""
                    className='bg-bg-color bg-opacity-20 backdrop-blur-lg w-36 h-8 rounded'
                // onChange={(e) => filterContacts(e)}
                >
                    <option value="-1">Filtro por tipo de contacto</option>
                    {
                        contact_type && contact_type.map(c => (
                            <option key={c.id} value={c.id}>{c.name}</option>
                        ))
                    }
                </select>

                <select name="country-filter" id="" className='bg-bg-color  bg-opacity-20 backdrop-blur-lg w-36 h-8 rounded            '>
                    <option value="-1">Filtro por pais</option>
                    {
                        countries && countries.map(country => (
                            <option key={country.id} value={country.id}>{country.name}</option>
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
                                <th>Tipo:</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        {/* Renderizando contactos en BD */}
                        {
                            contacts && contacts.map((contact, i) => (
                                <TableRow key={i} name={contact.name} type={contact.type.name} link={`/contacts/${contact.id}`} />
                            ))
                        }
                        </tbody>
                        
                    </table>
                </div>
            </section>
        </>
    )
}

export default ContactHome