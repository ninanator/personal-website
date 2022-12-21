import * as React from 'react';
import Image from 'next/image';
import Cat from '../public/home/cat.svg';

function Home() {
  return (
    <div className="flex flex-row items-center justify-center">
      <div className="[&_div]:mb-6 sm:ml-6">
        <div>
          <h4 className="mb-1 text-2xl text-black dark:text-white">
            Hello! I&apos;m Nina.
          </h4>
          <h5 className="text-black dark:text-white">
            안녕하세요! 니나 입니다.
          </h5>
        </div>
        <div>
          <h4 className="mb-1 text-2xl text-black dark:text-white">
            I&apos;m a software developer in Austin, Texas.
          </h4>
          <h5 className="text-black dark:text-white">
            오스틴, 텍사스에 사는 소프터웨어 개발자입니다.
          </h5>
        </div>
        <div>
          <h4 className="mb-1 text-2xl text-black dark:text-white">
            Stay a while and listen.
          </h4>
          <h5 className="text-black dark:text-white">
            &quot;잠시 내 이야기 좀 들어보게나.&quot;
          </h5>
        </div>
      </div>
      <Image alt="Cat Sticker" height="400" width="400" src={Cat} priority />
    </div>
  );
}

export default Home;
