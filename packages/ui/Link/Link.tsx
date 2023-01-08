import * as React from 'react';
import NextLink from 'next/link';
import { compress } from '../../../apps/website/src/utils/theme';

type LinkProps = {
  href: string;
  title: string;
};

function Link(props: LinkProps) {
  const { href, title } = props;

  return (
    <NextLink href={href}>
      <span
        className={compress(`
          hover:text-teal-200
          hover:underline
          hover:underline-offset-4
          text-black
          dark:text-white
        `)}
      >
        {title}
      </span>
    </NextLink>
  );
}

Link.defaultProps = {
  sx: {},
  variant: 'link',
};

export default Link;
