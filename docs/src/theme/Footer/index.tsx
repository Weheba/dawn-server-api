import {useEffect, useState, type ReactNode} from 'react';
import Link from '@docusaurus/Link';
import useBaseUrl from '@docusaurus/useBaseUrl';

import styles from './styles.module.css';

type FooterLink = {
  label: string;
  to: string;
  newTab?: boolean;
};

const columns: {title: string; links: FooterLink[]}[] = [
  {
    title: 'Docs',
    links: [
      {label: 'Getting Started', to: '/'},
      {label: 'Events', to: '/server-api/events'},
      {label: 'UI', to: '/server-api/ui/overview'},
    ],
  },
  {
    title: 'Dawn',
    links: [
      {label: 'dawn.gg', to: 'https://dawn.gg'},
      {label: 'Download', to: 'https://dawn.gg/#downloads'},
      {label: 'For servers', to: 'https://dawn.gg/servers'},
    ],
  },
  {
    title: 'Stay connected',
    links: [
      {label: 'Discord ↗', to: 'https://discord.gg/feather', newTab: true},
      {label: 'Support', to: 'https://dawn.gg/support'},
    ],
  },
];

export default function Footer(): ReactNode {
  const logo = useBaseUrl('/img/dawn-logo-white.svg');
  const [year, setYear] = useState(() => new Date().getFullYear());
  useEffect(() => setYear(new Date().getFullYear()), []);

  return (
    <footer className={styles.footer}>
      <div className={styles.top}>
        <a href="https://dawn.gg" aria-label="Dawn home">
          <img className={styles.logo} src={logo} alt="Dawn" width={125} height={45} />
        </a>
        {columns.map((column) => (
          <div key={column.title} className={styles.column}>
            <strong>{column.title}</strong>
            {column.links.map((link) => (
              <Link
                key={link.label}
                to={link.to}
                {...(link.newTab && {target: '_blank', rel: 'noopener noreferrer'})}>
                {link.label}
              </Link>
            ))}
          </div>
        ))}
      </div>
      <div className={styles.bottom}>
        <span>© {year} InPvP Inc. All rights reserved.</span>
        <span className={styles.disclaimer}>
          NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.
        </span>
        <div className={styles.legal}>
          <a href="https://dawn.gg/terms">Terms</a>
          <a href="https://dawn.gg/privacy">Privacy</a>
        </div>
      </div>
    </footer>
  );
}
