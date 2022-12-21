import * as React from 'react';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import Box from '@mui/material/Box';
import { BlogPostType } from '../../../server/contentful';
import BlogCategory from '../BlogCategory';
import Link from '../../Link';

type BlogPostSummariesProp = {
  blogPosts: BlogPostType[];
};

function BlogPostSummaries(props: BlogPostSummariesProp) {
  const { blogPosts } = props;
  const hasBlogPosts = blogPosts.length > 0;

  return (
    <Box sx={{ mr: { xs: 0, sm: 4 } }}>
      {hasBlogPosts &&
        blogPosts.map(({ category, createDate, slug, summary, title }) => (
          <Box my={2} sx={{ '& > *': { mb: 1 } }}>
            <Typography color="text.primary" variant="h4">
              {title}
            </Typography>
            <Box
              alignItems="center"
              display="flex"
              justifyContent="space-between"
              flexDirection="row"
            >
              <BlogCategory {...category} />
              <Typography color="text.secondary" variant="subtitle1">
                {Intl.DateTimeFormat('en-US', {
                  day: 'numeric',
                  month: 'short',
                  year: 'numeric',
                }).format(new Date(createDate))}
              </Typography>
            </Box>
            <Typography color="text.secondary" paragraph variant="body1">
              {summary}
            </Typography>
            <Link href={`/blog/entries/${slug}`} title="READ MORE..." />
            <Divider sx={{ my: 2 }} />
          </Box>
        ))}
      {!hasBlogPosts && <Box my={2}>Placeholder</Box>}
    </Box>
  );
}

export default BlogPostSummaries;
