import * as React from 'react';
import Link from '../../../../../apps/website/src/components/Link';

const navigationItems = [
  { href: '/blog/entries', title: 'Electric Sheep' },
  { href: '/notelearn', title: 'NoteLearn' },
];

function TopAppBar() {
  return (
    <div className="flex justify-between m-4">
      <Link href="/apps/website/public" title="Nina Blanson" />
      <div className="flex align-center">
        {navigationItems.map(({ href, title }) => (
          <div className="ml-4" key={title}>
            <Link href={href} title={title} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default TopAppBar;
