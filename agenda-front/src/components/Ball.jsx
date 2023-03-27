import React from 'react'

/**
 * A component representing a ball with customizable position, size and color.
 *
 * @param {Object} props - The props object containing top, color, and xPosition.
 * @param {string} props.top - The distance from the top of the screen.
 * @param {string} props.color - The color of the ball.
 * @param {string} props.xPosition - The horizontal position of the ball.
 * @returns {JSX.Element} - The JSX for the ball.
 */
const Ball = ({ top, color, xPosition }) => {
  return (
    <div className={`absolute ${top} ${xPosition} w-[200px] md:w-[300px] h-[200px] md:h-[300px] ${color} z-[-10] rounded-full`}></div>
  )
}

export default Ball