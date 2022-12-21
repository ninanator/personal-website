import * as React from 'react';
import Image from 'next/image';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import { GetStaticProps } from 'next';
import { useRouter } from 'next/router';
import { MainLayout } from '../../../../../packages/ui/Layouts';
import Sheep from '../../../public/blog/sheep.png';
import { BlogCategories } from '../../../src/components/Blog';
import {
  BlogCategoryType,
  BlogPostType,
  getBlogCategories,
  getBlogPosts,
} from '../../../src/server/contentful';
import BlogPostSummaries from '../../../src/components/Blog/BlogPostSummaries/BlogPostSummaries';

type BlogEntriesPageProps = {
  blogPosts: BlogPostType[];
  categories: BlogCategoryType[];
};

function filterBlogPosts(
  posts: BlogPostType[],
  selectedCategory?: string | string[],
): BlogPostType[] {
  return !selectedCategory || Array.isArray(selectedCategory)
    ? posts
    : posts.filter(({ category }) => category.slug === selectedCategory);
}

function BlogEntriesPage(props: BlogEntriesPageProps) {
  const { blogPosts: blogPostsProp, categories } = props;
  const { query } = useRouter();
  const { category: selectedCategory } = query;

  const [blogPosts, setBlogPosts] = React.useState(
    filterBlogPosts(blogPostsProp, selectedCategory),
  );

  React.useEffect(() => {
    setBlogPosts(filterBlogPosts(blogPostsProp, selectedCategory));
  }, [blogPostsProp, selectedCategory]);

  return (
    <MainLayout>
      <Container disableGutters maxWidth="xl">
        <Box
          alignItems="center"
          display="flex"
          justifyContent="flex-start"
          mb={3}
          sx={{ flexDirection: { xs: 'column', sm: 'row' } }}
        >
          <Box sx={{ mb: { xs: 3, sm: 0 } }}>
            <Image
              alt="Blog Banner"
              height="84px"
              layout="fixed"
              src={Sheep}
              width="145px"
            />
          </Box>
          <Box sx={{ ml: { xs: 0, sm: 4 } }}>
            <Typography color="text.primary" mb={1} variant="h3">
              Dreaming of Electric Sheep
            </Typography>
            <Typography color="text.secondary" variant="h5">
              &gt; a software engineering blog
            </Typography>
          </Box>
        </Box>
        <Box
          alignItems="center"
          display="flex"
          minWidth="300px"
          sx={{ flexDirection: { xs: 'column-reverse', sm: 'row' } }}
        >
          <BlogPostSummaries blogPosts={blogPosts} />
          <BlogCategories categories={categories} />
        </Box>
      </Container>
    </MainLayout>
  );
}

export const getStaticProps: GetStaticProps<
  BlogEntriesPageProps
> = async () => {
  // TODO: Add pagination once there are enough posts...
  const [blogPosts, categories] = await Promise.all([
    getBlogPosts(),
    getBlogCategories(),
  ]);

  return {
    props: {
      blogPosts,
      categories,
    },
  };
};

export default BlogEntriesPage;
