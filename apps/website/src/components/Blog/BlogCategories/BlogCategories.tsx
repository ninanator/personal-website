import * as React from 'react';
import Divider from '@mui/material/Divider';
import Box from '@mui/material/Box';
import BlogCategory from '../BlogCategory';
import { BlogCategoryType } from '../../../server/contentful';

type BlogCategoriesProp = {
  categories: BlogCategoryType[];
};

function BlogCategories(props: BlogCategoriesProp) {
  const { categories } = props;
  const numCategories = categories.length;

  return (
    <Box>
      {categories.map((category, idx) => (
        <>
          <Divider />
          <BlogCategory {...category} />
          {numCategories === idx + 1 && <Divider />}
        </>
      ))}
    </Box>
  );
}

export default BlogCategories;
