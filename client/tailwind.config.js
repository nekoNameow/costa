const colors = require('tailwindcss/colors')

module.exports = {
  important: true,
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: false,
  theme: {
    extend: {},
    colors: {
      primary: '#1976d2',
      ...colors
    }
  },
  variants: {
    extend: {}
  },
  plugins: []
}
