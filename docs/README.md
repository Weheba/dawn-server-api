# Dawn Server API

Server-side API for integrating with the Dawn client. See `docs/server-api` for the documentation.

## Testing Server UI

Custom Server UI only renders on servers whitelisted by the Dawn team. To test against your own
server, enable the override in the Dawn client: **General settings -> Developer -> Enable developer
settings**, then **Allow Server UI on unwhitelisted servers**. Unwhitelisted servers receive a
`NOT_WHITELISTED` error through the page's `UILoadHandler`.

# Website

This website is built using [Docusaurus](https://docusaurus.io/), a modern static website generator.

### Installation

```
$ yarn
```

### Local Development

```
$ yarn start
```

This command starts a local development server and opens up a browser window. Most changes are reflected live without having to restart the server.

### Build

```
$ yarn build
```

This command generates static content into the `build` directory and can be served using any static contents hosting service.

### Deployment

Using SSH:

```
$ USE_SSH=true yarn deploy
```

Not using SSH:

```
$ GIT_USER=<Your GitHub username> yarn deploy
```

If you are using GitHub pages for hosting, this command is a convenient way to build the website and push to the `gh-pages` branch.
