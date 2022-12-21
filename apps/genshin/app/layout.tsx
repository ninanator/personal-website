import './globals.css';
import * as React from 'react';

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
      placeholder
    </div>
    </body>
    </html>
  );
}
