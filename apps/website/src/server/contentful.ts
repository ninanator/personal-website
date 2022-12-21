import {
  Asset,
  ContentfulClientApi,
  createClient,
  Entry,
  EntryCollection,
} from 'contentful';

type ContentfulBlogCategory = {
  icon: Asset;
  slug: string;
  title: string;
};

export type BlogCategoryType = {
  icon: string;
  slug: string;
  title: string;
};

type ContentfulBlogPost = {
  body: string;
  category: Entry<ContentfulBlogCategory>;
  createDate: string;
  references: string[];
  slug: string;
  summary: string;
  title: string;
};

export type BlogPostType = {
  body: string;
  category: BlogCategoryType;
  createDate: string;
  references: string[];
  slug: string;
  summary: string;
  title: string;
};

const client: ContentfulClientApi = createClient({
  space: process.env.CONTENTFUL_SPACE_ID as string,
  accessToken: process.env.CONTENTFUL_ACCESS_TOKEN as string,
});

const prependHttps = (partialUrl: string) => `https:${partialUrl}`;

export async function getBlogCategories(): Promise<BlogCategoryType[]> {
  const categories: EntryCollection<ContentfulBlogCategory> =
    await client.getEntries({
      content_type: process.env.CONTENTFUL_CATEGORY_TYPE_ID,
    });

  return categories.items.map(({ fields }) => ({
    ...fields,
    icon: prependHttps(fields.icon.fields.file.url),
  }));
}

export async function getBlogPosts(): Promise<BlogPostType[]> {
  const blogPosts: EntryCollection<ContentfulBlogPost> =
    await client.getEntries({
      content_type: process.env.CONTENTFUL_POST_TYPE_ID,
    });

  return blogPosts.items.map(({ fields }) => ({
    ...fields,
    category: {
      icon: prependHttps(fields.category.fields.icon.fields.file.url),
      slug: fields.category.fields.slug,
      title: fields.category.fields.title,
    },
  }));
}
