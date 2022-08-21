import {
  createTheme,
  ThemeOptions as ThemeOptionsType,
} from '@mui/material/styles';
import { PaletteMode } from '@mui/material';

declare module '@mui/material/styles' {
  // eslint-disable-next-line no-unused-vars
  interface TypeText {
    hover: string;
  }
}

export const checkIsDarkMode = (mode: PaletteMode): boolean => mode === 'dark';

const createThemeOverrides = (mode: PaletteMode): ThemeOptionsType => {
  const isDarkMode = checkIsDarkMode(mode);

  return {
    palette: {
      background: {
        default: isDarkMode ? '#0F1216' : '#FFF',
      },
      text: {
        hover: '#7FB02C',
      },
    },
  };
};

export default function updateTheme(mode: PaletteMode) {
  const { palette: paletteOverrides, ...otherThemeKeysOverrides } =
    createThemeOverrides(mode);

  return createTheme({
    palette: {
      mode,
      ...paletteOverrides,
    },
    ...otherThemeKeysOverrides,
  });
}
