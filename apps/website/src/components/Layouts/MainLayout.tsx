import * as React from 'react';
import Box from '@mui/material/Box';
import { ThemeProvider } from '@mui/material/styles';
import BottomAppBar from './components/BottomAppBar/BottomAppBar';
import TopAppBar from './components/TopAppBar/TopAppBar';
import theme from '../../utils/theme';

type MainLayoutProps = {
  children: React.ReactNode;
};

function MainLayout(props: MainLayoutProps) {
  const { children } = props;

  return (
    <ThemeProvider theme={theme}>
      <Box display="flex" flexDirection="column" minHeight="100vh">
        <Box flex={1}>
          <TopAppBar />
          <main>
            <Box m={2}>{children}</Box>
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
