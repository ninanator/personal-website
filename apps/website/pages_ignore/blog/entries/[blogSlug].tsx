import { GetStaticPaths, GetStaticProps } from 'next';
import { BlogPostType } from '../../../src/server/contentful';

type BlogEntryPageProp = {
  blogPost: BlogPostType;
};

function BlogEntryPage(props: BlogEntryPageProp) {}

export const getStaticPaths: GetStaticPaths = async () => {};

export const getStaticProps: GetStaticProps<BlogEntryPageProp> = async () => {};

export default BlogEntryPage;
