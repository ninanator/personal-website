const { fontFamily } = require('tailwindcss/defaultTheme');

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: {
    relative: true,
    files: ['./app/**/*.{jsx,tsx,css}', './src/components/**/*.{jsx,tsx}'],
  },
  darkMode: 'media',
  theme: {
    extend: {
      fontFamily: {
        // Override default font family with custom font.
        sans: ['"Tahoma"', ...fontFamily.sans],
      },
    },
  },
  plugins: [],
};
