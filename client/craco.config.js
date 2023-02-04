const { resolve } = require('path')

module.exports = {
  webpack: {
    alias: {
      '@': resolve('src')
    }
  },
  style: {
    postcssOptions: {
      plugins: [require('tailwindcss'), require('autoprefixer')]
    }
  }
}
