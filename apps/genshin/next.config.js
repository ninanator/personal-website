const nextConfig = {
  experimental: {
    appDir: true,
    transpilePackages: ['@nmb/ui'],
  },
  images: {
    domains: ['images.ctfassets.net'],
  },
  reactStrictMode: true,
  swcMinify: true,
};

module.exports = nextConfig;
