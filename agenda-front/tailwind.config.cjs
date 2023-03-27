/** @type {import('tailwindcss').Config} */
module.exports = {
  // Were we will find the content?
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    // color scheme
    colors: {
      'active-color': '#a0a0a0',
      'light-color': '#A4D9D9',
      'main-color': '#8787D9',
      'secundary-color': '#5151C6',
      'bg-color': '#141225',
      'innactive-color': '#606060',
      "transparent": "transparent",
      "text-color":"#fff",
    },
    extend: {},
  },
  plugins: [],
}
// Tailwind config files
