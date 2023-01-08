import * as React from 'react';
import Link from '../../../Link';

const navigationItems = [
  { href: '/blog/entries', title: 'Electric Sheep' },
  { href: '/genshin', title: 'Genshin Impact' },
  { href: '/notelearn', title: 'NoteLearn' },
];

function TopAppBar() {
  return (
    <nav>
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
    </nav>
  );
}

export default TopAppBar;
