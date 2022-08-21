import * as React from 'react';
import Box from '@mui/material/Box';
import { ThemeProvider } from '@mui/material/styles';
import { PaletteMode } from '@mui/material';
import useMediaQuery from '@mui/material/useMediaQuery';
import BottomAppBar from './components/BottomAppBar/BottomAppBar';
import TopAppBar from './components/TopAppBar/TopAppBar';
import updateTheme from '../../utils/theme';

type MainLayoutProps = {
  children: React.ReactNode;
};

function MainLayout(props: MainLayoutProps) {
  const { children } = props;
  const prefersDarkMode = useMediaQuery('(prefers-color-scheme: dark)');
  const [mode, setMode] = React.useState<PaletteMode>(
    prefersDarkMode ? 'dark' : 'light',
  );

  return (
    <ThemeProvider theme={updateTheme(mode)}>
      <style global jsx>
        {'body { margin: 0; }'}
      </style>
      <Box
        display="flex"
        flexDirection="column"
        minHeight="100vh"
        sx={{ backgroundColor: 'background.default' }}
      >
        <Box flex={1}>
          <TopAppBar onSetMode={setMode} />
          <main>
            <Box m={3}>{children}</Box>
          </main>
        </Box>
        <footer>
          <BottomAppBar />
        </footer>
      </Box>
    </ThemeProvider>
  );
}

export default MainLayout;
