import type {PrismTheme} from 'prism-react-renderer';
import type {Config} from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

const prismTheme: PrismTheme = {
  plain: {
    color: '#e6e6e6',
    backgroundColor: '#141414',
  },
  styles: [
    {
      types: ['comment', 'prolog', 'doctype', 'cdata'],
      style: {color: '#7a7f87', fontStyle: 'italic'},
    },
    {
      types: ['string', 'char', 'attr-value', 'regex', 'inserted'],
      style: {color: '#9ece6a'},
    },
    {
      types: ['keyword', 'tag', 'selector', 'atrule', 'important'],
      style: {color: '#ff8a3d'},
    },
    {
      types: ['class-name', 'builtin', 'attr-name', 'property', 'namespace'],
      style: {color: '#7dcfff'},
    },
    {
      types: ['number', 'boolean', 'constant'],
      style: {color: '#e0af68'},
    },
    {
      types: ['annotation', 'decorator'],
      style: {color: '#d49bff'},
    },
    {
      types: ['punctuation', 'operator'],
      style: {color: '#8a8a8a'},
    },
    {
      types: ['deleted'],
      style: {color: '#f87171'},
    },
  ],
};

const baseUrl = process.env.DOCS_BASE_URL ?? '/';

const config: Config = {
  title: 'Dawn Server API',
  tagline: 'Integrate your Minecraft server with the Dawn client.',
  favicon: 'img/favicon.ico',

  url: process.env.DOCS_URL ?? 'http://localhost:3000',
  baseUrl,

  organizationName: 'Weheba',
  projectName: 'dawn-server-api',

  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',

  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  headTags: [
    {
      tagName: 'link',
      attributes: {rel: 'icon', type: 'image/png', sizes: '512x512', href: `${baseUrl}img/icon.png`},
    },
    {
      tagName: 'link',
      attributes: {rel: 'apple-touch-icon', href: `${baseUrl}img/apple-icon.png`},
    },
  ],

  presets: [
    [
      'classic',
      {
        docs: {
          routeBasePath: '/',
          sidebarPath: './sidebars.ts',
        },
        blog: false,
        theme: {
          customCss: ['./src/css/font.css', './src/css/custom.css'],
        },
      } satisfies Preset.Options,
    ],
  ],

  themeConfig: {
    image: 'img/social-card.png',
    metadata: [{name: 'theme-color', content: '#0a0a0a'}],
    navbar: {
      title: 'Server API',
      logo: {
        alt: 'Dawn',
        src: 'img/dawn-logo-white.svg',
      },
      items: [
        {
          label: 'dawn.gg',
          href: 'https://dawn.gg',
          position: 'right',
        },
        {
          label: 'Discord',
          href: 'https://discord.gg/feather',
          position: 'right',
        },
        {
          label: 'Get Dawn',
          href: 'https://dawn.gg/#downloads',
          position: 'right',
          className: 'navbar__link--cta',
        },
      ],
    },
    prism: {
      theme: prismTheme,
      darkTheme: prismTheme,
      additionalLanguages: ['java'],
    },
    colorMode: {
      defaultMode: 'dark',
      disableSwitch: true,
      respectPrefersColorScheme: false,
    },
  } satisfies Preset.ThemeConfig,

  plugins: [
    [
      '@docusaurus/plugin-ideal-image',
      {
        quality: 85,
        max: 1200,
        min: 640,
        steps: 4,
        disableInDev: false,
      },
    ],
  ],
};

export default config;
