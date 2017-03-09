# git-work
> The marketplace for [GitHub Issues](https://help.github.com/articles/github-glossary/#issue)
## Deployment
We use [git.watch](https://git.watch) to deploy automatically to a bare metal server.
On push to GitHub we trigger the [push script](push). Bare gives us instant deployment and a filesystem.
If the project reaches large enough scale we can consider moving to the cloud but for now
this is good enough.
