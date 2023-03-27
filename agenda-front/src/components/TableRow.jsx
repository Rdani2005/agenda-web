import React from 'react'
import { Link } from 'react-router-dom'

/**
 * A component representing a single row of a table
 *
 * @param {Object} props - The props object containing name, type, and link
 * @param {string} props.name - The name of the item
 * @param {string} props.type - The type of the item
 * @param {string} props.link - The link to the item
 * @returns {JSX.Element} - The JSX for the table row
 */
const TableRow = ({ name, type, link }) => {
    return (
        <>
            <tr className='h-10'></tr>
            <tr className='bg-text-color bg-opacity-20 backdrop-blur-lg w-full h-20'>
                <td className='pl-5'>
                    {name}
                </td>
                <td>
                    {type}
                </td>
                <td>
                    <Link to={link}>ver mas</Link>
                </td>
            </tr>1
        </>
    )
}

export default TableRow