/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    colors: {
      'text-color': '#ffffff',
      'light-color': '#A4D9D9',
      'main-color': '#8787D9',
      'secundary-color': '#5151C6',
      'bg-color': '#141225',
      "transparent": "transparent",
    },
    extend: {},
  },
  plugins: [],
}
