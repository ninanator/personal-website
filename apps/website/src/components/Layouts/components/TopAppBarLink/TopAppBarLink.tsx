import * as React from 'react';
import Link from '@mui/material/Link';
import NextLink from 'next/link';
import Typography from '@mui/material/Typography';

type TopAppBarLinkProps = {
  href: string;
  title: string;
  variant: 'top' | 'menu';
};

function TopAppBarLink(props: TopAppBarLinkProps) {
  const { href, title, variant } = props;

  if (variant === 'menu') {
    return (
      <NextLink href={href}>
        <Typography variant="body1">{title}</Typography>
      </NextLink>
    );
  }

  return (
    <NextLink href={href} passHref>
      {/* eslint-disable-next-line jsx-a11y/anchor-is-valid */}
      <Link
        sx={{
          '&:hover, .MuiLink-underlineNone': { color: 'text.hover' },
        }}
        color="black"
        underline="none"
        variant="subtitle1"
      >
        {title}
      </Link>
    </NextLink>
  );
}

export default TopAppBarLink;
