# Dawn Server API

Server-side API for integrating with the Dawn client. See `docs/server-api` for the documentation.

## Testing Server UI

Custom Server UI only renders on servers whitelisted by the Dawn team. To test against your own
server, enable the override in the Dawn client: **General settings -> Developer -> Enable developer
settings**, then **Allow Server UI on unwhitelisted servers**. Unwhitelisted servers receive a
`NOT_WHITELISTED` error through the page's `UILoadHandler`.

## Website

The docs site is built with [Docusaurus](https://docusaurus.io/).

```
npm ci
npm start
```

`npm start` serves the site at http://localhost:3000 and reloads on changes. `npm run build` writes
the static site to `build/` and fails on broken links.

Pushes to `main` that touch `docs/` are built and published to GitHub Pages by
`.github/workflows/pages.yml`.
