import * as React from 'react';
import Box from '@mui/material/Box';
import DarkModeOutlined from '@mui/icons-material/DarkModeOutlined';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import LightModeOutlined from '@mui/icons-material/LightModeOutlined';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Toolbar from '@mui/material/Toolbar';
import { PaletteMode } from '@mui/material';
import Tooltip from '@mui/material/Tooltip';
import Typography from '@mui/material/Typography';
import { useTheme } from '@mui/material/styles';
import TopAppBarLink from '../TopAppBarLink/TopAppBarLink';
import { checkIsDarkMode } from '../../../../utils/theme';

type TopAppBarProps = {
  onSetMode: (mode: PaletteMode) => void;
};

const navigationItems = [
  { href: '/blog/entries', title: 'Electric Sheep' },
  { href: '/notelearn', title: 'NoteLearn' },
];

function TopAppBar(props: TopAppBarProps) {
  const { onSetMode } = props;
  const { palette } = useTheme();
  const [anchorEl, setAnchorEl] = React.useState<
    HTMLElement | null | undefined
  >(null);

  const isDarkMode = checkIsDarkMode(palette.mode);
  const isOpen = Boolean(anchorEl);

  return (
    <Toolbar disableGutters sx={{ justifyContent: 'space-between', mx: 3 }}>
      <TopAppBarLink href="/" title="Nina Blanson" variant="top" />
      <Box sx={{ alignItems: 'center', display: { xs: 'none', sm: 'flex' } }}>
        {navigationItems.map(({ href, title }) => (
          <TopAppBarLink
            href={href}
            key={title}
            title={title}
            sx={{ ml: 2 }}
            variant="top"
          />
        ))}
        <Tooltip title={isDarkMode ? 'Toggle Light Mode' : 'Toggle Dark Mode'}>
          <IconButton
            onClick={() => onSetMode(isDarkMode ? 'light' : 'dark')}
            sx={{ '&:hover': { color: 'text.hover' }, ml: 1 }}
          >
            {isDarkMode ? (
              <DarkModeOutlined fontSize="small" />
            ) : (
              <LightModeOutlined fontSize="small" />
            )}
          </IconButton>
        </Tooltip>
      </Box>
      <Box sx={{ display: { xs: 'flex', sm: 'none' } }}>
        <IconButton
          aria-controls={isOpen ? 'main-menu' : undefined}
          aria-expanded={isOpen ? 'true' : undefined}
          aria-haspopup="true"
          aria-label="Open Menu"
          id="main-menu-button"
          onClick={(event) => setAnchorEl(event.currentTarget)}
          sx={{ '&:hover': { color: 'text.hover' } }}
        >
          <MenuIcon />
        </IconButton>
        <Menu
          anchorEl={anchorEl}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'right',
          }}
          id="main-menu"
          MenuListProps={{
            'aria-labelledby': 'main-menu-button',
          }}
          open={isOpen}
          onClose={() => setAnchorEl(null)}
          transformOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
        >
          {navigationItems.map(({ href, title }) => (
            <MenuItem key={title} sx={{ '&:hover': { color: 'text.hover' } }}>
              <TopAppBarLink href={href} title={title} variant="menu" />
            </MenuItem>
          ))}
          <Divider />
          <MenuItem
            onClick={() => onSetMode(isDarkMode ? 'light' : 'dark')}
            sx={{ '&:hover': { color: 'text.hover' } }}
          >
            {isDarkMode ? (
              <DarkModeOutlined fontSize="small" />
            ) : (
              <LightModeOutlined fontSize="small" />
            )}
            <Typography ml={1}>
              {isDarkMode ? 'Dark Mode' : 'Light Mode'}
            </Typography>
          </MenuItem>
        </Menu>
      </Box>
    </Toolbar>
  );
}

export default TopAppBar;
