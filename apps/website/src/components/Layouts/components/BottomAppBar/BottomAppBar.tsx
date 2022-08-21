import * as React from 'react';
import Image from 'next/image';
import Link from '@mui/material/Link';
import Toolbar from '@mui/material/Toolbar';
import GitHubLogo from '../../../../../public/global/github.png';
import LinkedInLogo from '../../../../../public/global/linkedin.png';

function BottomAppBar() {
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
        <Image
          alt="GitHub"
          height="20px"
          src={GitHubLogo}
          width="20px"
        />
      </Link>
      <Link href="https://github.com/ninanator" rel="noopener" target="_blank">
        <Image
          alt="LinkedIn"
          height="20px"
          src={LinkedInLogo}
          width="24px"
        />
      </Link>
    </Toolbar>
  );
}

export default BottomAppBar;
