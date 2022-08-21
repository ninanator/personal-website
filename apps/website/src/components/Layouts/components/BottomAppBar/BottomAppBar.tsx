import * as React from 'react';
import Image from 'next/image';
import Link from '@mui/material/Link';
import Toolbar from '@mui/material/Toolbar';
import { useTheme } from '@mui/material/styles';
import GitHubLogo from '../../../../../public/global/github.png';
import GitHubWhiteLogo from '../../../../../public/global/github-light.png';
import LinkedInLogo from '../../../../../public/global/linkedin.png';
import LinkedInWhiteLogo from '../../../../../public/global/linkedin-light.png';
import { checkIsDarkMode } from '../../../../utils/theme';

function BottomAppBar() {
  const { palette } = useTheme();
  const isDarkMode = checkIsDarkMode(palette.mode);

  return (
    <Toolbar
      disableGutters
      sx={{
        bottom: 0,
        justifyContent: 'flex-end',
        mx: 2,
        mb: 1,
        top: 'auto',
      }}
    >
      <Link
        href="https://www.linkedin.com/pub/nina-blanson/93/565/692"
        rel="noopener"
        sx={{ mr: 1 }}
        target="_blank"
      >
        {isDarkMode && (
          <Image
            alt="GitHub"
            height="20px"
            src={GitHubWhiteLogo}
            width="20px"
          />
        )}
        {!isDarkMode && (
          <Image alt="GitHub" height="20px" src={GitHubLogo} width="20px" />
        )}
      </Link>
      <Link href="https://github.com/ninanator" rel="noopener" target="_blank">
        {isDarkMode && (
          <Image
            alt="LinkedIn"
            height="20px"
            src={LinkedInWhiteLogo}
            width="24px"
          />
        )}
        {!isDarkMode && (
          <Image alt="LinkedIn" height="20px" src={LinkedInLogo} width="24px" />
        )}
      </Link>
    </Toolbar>
  );
}

export default BottomAppBar;
