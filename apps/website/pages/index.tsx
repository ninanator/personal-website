import * as React from 'react';
import Box from '@mui/material/Box';
import Head from 'next/head';
import Image from 'next/image';
import Typography from '@mui/material/Typography';
import { MainLayout } from '../src/components/Layouts';
import Cat from '../public/home/cat.png';

function Home() {
  return (
    <div>
      <Head>
        <title>Nina Blanson</title>
        <meta name="description" content="REPLACE ME" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <MainLayout>
        <Box
          alignItems="center"
          display="flex"
          justifyContent="center"
          sx={{ flexDirection: { xs: 'column', sm: 'row' } }}
        >
          <Box sx={{ '&>div': { mb: 3 } }}>
            <Box>
              <Typography color="text.primary" mb="4px" variant="h4">
                Hello! I&apos;m Nina.
              </Typography>
              <Typography color="text.secondary" variant="h5">
                안녕하세요! 니나 입니다.
              </Typography>
            </Box>
            <Box>
              <Typography color="text.primary" mb="4px" variant="h5">
                I&apos;m a software developer in Austin, Texas.
              </Typography>
              <Typography color="text.secondary" variant="subtitle1">
                오스틴, 텍사스에 사는 소프터웨어 개발자입니다.
              </Typography>
            </Box>
            <Box>
              <Typography color="text.primary" mb="4px" variant="h5">
                Stay a while and listen.
              </Typography>
              <Typography color="text.secondary" variant="subtitle1">
                &quot;잠시 내 이야기 좀 들어보게나.&quot;
              </Typography>
            </Box>
          </Box>
          <Box
            display="flex"
            sx={{ ml: { xs: 0, sm: 4, md: 8 }, mt: { xs: 4, sm: 0 } }}
          >
            <Image
              alt="Cat Sticker"
              layout="fixed"
              src={Cat}
              width="100%"
              height="100%"
            />
          </Box>
        </Box>
      </MainLayout>
    </div>
  );
}

export default Home;
