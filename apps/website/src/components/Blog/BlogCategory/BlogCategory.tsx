import * as React from 'react';
import Box from '@mui/material/Box';
import Image from 'next/image';
import { BlogCategoryType } from '../../../server/contentful';
import Link from '../../Link';

function BlogCategory(props: BlogCategoryType) {
  const { icon, slug, title } = props;

  return (
    <Box alignItems="center" display="flex" p={1}>
      <Image height="8px" width="8px" src={icon} />
      <Link
        href={`/blog/entries?category=${slug}`}
        sx={{ ml: 1 }}
        title={title}
      />
    </Box>
  );
}

export default BlogCategory;
