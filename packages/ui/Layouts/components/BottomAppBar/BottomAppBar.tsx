import * as React from 'react';
import Image from 'next/image';
import GitHubLogo from '../../../../../apps/website/public/global/github.png';
import GitHubWhiteLogo from '../../../../../apps/website/public/global/github-light.png';
import LinkedInLogo from '../../../../../apps/website/public/global/linkedin.png';
import LinkedInWhiteLogo from '../../../../../apps/website/public/global/linkedin-light.png';

function BottomAppBar() {
  return (
    <div className="bottom-0 flex justify-end mb-4 mx-6 top-auto">
      <a className="mr-2" href="https://github.com/ninanator" rel="noopener">
        <Image
          alt="GitHub"
          height="20"
          // TODO - Swap with light theme?
          src={GitHubWhiteLogo}
          width="20"
        />
      </a>
      <a
        href="https://www.linkedin.com/pub/nina-blanson/93/565/692"
        rel="noopener"
      >
        <Image
          alt="GitHub"
          height="20"
          // TODO - Swap with light theme?
          src={LinkedInWhiteLogo}
          width="24"
        />
      </a>
    </div>
  );
}

export default BottomAppBar;
