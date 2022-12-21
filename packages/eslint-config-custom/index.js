module.exports = {
  extends: ['plugin:unicorn/recommended', 'prettier', 'turbo', 'next'],
  rules: {
    'import/extensions': 'off',
    'react/jsx-filename-extension': [1, { 'extensions': ['.tsx'] }],
    'react/jsx-props-no-spreading': 'off',
    'unicorn/filename-case': 'off',
    'unicorn/prevent-abbreviations': 'off',
  },
}
