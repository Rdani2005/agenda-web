import React from 'react'
import Ball from '../components/Ball'

/**
 * Experimental home, where we will have a dashboard
 * @returns Home view
 */
const Home = () => {
    return (

        <>
            <Ball top={'top-[30%]'} xPosition={'right-20'} color={'bg-light-color'}></Ball>
            <Ball top={'top-[50%]'} xPosition={'left-[10%]'} color={'bg-main-color'}></Ball>
            <div className="w-[50%]">
                <h1 className="text-6xl">Bienvenido a la Agenda personal</h1>
                <h2
                    className="text-xl mt-10">
                    Proyecto cotidiano hecho por:
                    <a href="http://rdani2005.works"
                        target={'_blank'}
                        className='underline'
                    >
                        Danny Sequeira
                    </a>
                </h2>
            </div>
            <section className='flex justify-around mt-16'>
                <div className='bg-text-color bg-opacity-20 backdrop-blur-lg w-[35%] py-20 text-center'>
                    <h3 className='text-2xl'>Contactos Registrados</h3>
                    <h1 className='text-6xl'>156</h1>
                </div>


                <div className='bg-text-color bg-opacity-20 backdrop-blur-lg w-[35%] py-20 text-center'>
                    <h3 className='text-2xl'>Contactos Registrados</h3>
                    <h1 className='text-6xl'>156</h1>
                </div>

            </section>
        </>
    )
}

export default Home