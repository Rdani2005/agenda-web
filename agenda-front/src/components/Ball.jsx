import React from 'react'

const Ball = ({top, color, xPosition}) => {
  return (
    <div className={`absolute ${top} ${xPosition} w-[200px] md:w-[300px] h-[200px] md:h-[300px] ${color} z-[-10] rounded-full`}></div>
  )
}

export default Ball