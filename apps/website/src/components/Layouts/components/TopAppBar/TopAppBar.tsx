import * as React from 'react';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import { MenuItem } from '@mui/material';
import TopAppBarLink from '../TopAppBarLink/TopAppBarLink';

const navigationItems = [
  { href: '/blog/entries', title: 'Electric Sheep' },
  { href: '/notelearn', title: 'NoteLearn' },
];

function TopAppBar() {
  const [anchorEl, setAnchorEl] = React.useState<HTMLElement | null>(null);
  const isOpen = Boolean(anchorEl);

  return (
    <Toolbar disableGutters sx={{ justifyContent: 'space-between', mx: 2 }}>
      <TopAppBarLink href="/" title="Nina Blanson" variant="top" />
      <Box sx={{ '&>*': { ml: 2 }, display: { xs: 'none', sm: 'flex' } }}>
        {navigationItems.map(({ href, title }) => (
          <TopAppBarLink href={href} key={title} title={title} variant="top" />
        ))}
      </Box>
      <Box sx={{ display: { xs: 'flex', sm: 'none' } }}>
        <IconButton
          aria-controls={isOpen ? 'main-menu' : undefined}
          aria-expanded={isOpen ? 'true' : undefined}
          aria-haspopup="true"
          aria-label="Open Menu"
          id="main-menu-button"
          onClick={(event) => setAnchorEl(event.currentTarget)}
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
            <MenuItem key={title}>
              <TopAppBarLink href={href} title={title} variant="menu" />
            </MenuItem>
          ))}
        </Menu>
      </Box>
    </Toolbar>
  );
}

export default TopAppBar;
