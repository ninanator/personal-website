import { createTheme, PaletteOptions } from '@mui/material/styles';

// declare module '@mui/material/styles' {
//   interface Theme {
//     palette: PaletteOptions & {
//       text: {
//         hover: string;
//       };
//     };
//   }
//   // Allow configuration using `createTheme`
//   interface ThemeOptions {
//     palette?: PaletteOptions & {
//       text?: {
//         hover?: string;
//       };
//     };
//   }
// }

const theme = createTheme({
  palette: {
    text: {
      hover: '#7FB02C',
    },
  },
  components: {
    MuiLink: {
      styleOverrides: {
        root: {

        },
      },
    },
  },
});

export default theme;
