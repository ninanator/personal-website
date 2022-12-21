import './globals.css';
import * as React from 'react';
import { BottomAppBar, TopAppBar } from '@nmb/ui/Layouts';

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <head>
        <title>Nina Blanson</title>
        <meta name="description" content="REPLACE ME" />
        <link rel="icon" href="/favicon.ico" />
      </head>
      <body className="m-0">
        <div className="bg-white dark:bg-slate-900 flex flex-col min-h-screen">
          <div className="flex-auto">
            <TopAppBar />
            <main>
              <div className="m-3">{children}</div>
            </main>
          </div>
          <footer>
            <BottomAppBar />
          </footer>
        </div>
      </body>
    </html>
  );
}
