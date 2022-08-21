import * as React from 'react';
import Image from 'next/image';
import Typography from '@mui/material/Typography';
import { MainLayout } from '../../src/components/Layouts';
import Sheep from '../../public/blog/sheep.png';

function BlogEntries() {
  return (
    <MainLayout>
      <Image alt="Blog Banner" layout="fixed" src={Sheep} />
      <Typography color="text.primary" variant="h3">
        Dreaming of Electric Sheep
      </Typography>
      <Typography color="text.secondary" variant="h5">
        &gt; a software engineering blog
      </Typography>
    </MainLayout>
  );
}

export default BlogEntries;
