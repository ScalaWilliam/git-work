# Development

<!-- toc -->

- [Running locally](#running-locally)
- [Developing](#developing)
- [Editing the content](#editing-the-content)
- [Deployment](#deployment)
- [Technical choices](#technical-choices)

<!-- tocstop -->

First, [Clone the repository](https://help.github.com/articles/cloning-a-repository/).

## Running locally

1. <a href="https://www.scalawilliam.com/essential-sbt/">Install SBT</a>
2. Inside the Git clone of this project, run: `sbt run` or `make run`.
3. Go to http://localhost:9000

## Developing

1. <a href="https://www.jetbrains.com/idea/">Install IntelliJ IDEA</a> Community Edition or Ultimate Edition.
2. Make sure to install the Scala plugin during set up.
3. Install the <a href="http://scalafmt.org/">scalafmt</a> plugin.
4. <a href="https://www.jetbrains.com/help/idea/2017.1/getting-started-with-sbt.html#import_project">Import the SBT project</a>.

## Editing the content

It's all in `dist/www`. You can probably use something like <a href="http://brackets.io/">Brackets</a> for that.

## Deployment

We use [git.watch](https://git.watch) to deploy automatically to a bare metal server.
On push to GitHub we trigger the [push script](push). Bare gives us instant deployment and a filesystem.

This project is unlikely to need scaling except for the website which we can always put behind a CloudFront, Fastly 
or something similar.

In the current deployment, we have an [nginx](https://www.nginx.com/resources/wiki/) instance 
[reverse-proxying](https://www.nginx.com/resources/glossary/reverse-proxy-server/) to the Play application instance.

Provisioning is done with Ansible.

## Technical choices

I chose this stack because of my experience and familiarity with it.

- [Scala](http://www.scala-lang.org/news/) and 
    [Play framework](https://www.playframework.com/documentation/2.6.x/Migration26) 
    because I'm experienced in it. See [ActionFPS](https://github.com/ScalaWilliam/ActionFPS) 
    and [Git Watch](http://git.watch/) which also use Event Source.
- Build tool: [SBT](https://www.scalawilliam.com/essential-sbt/) default for Play and supports Docker.
- TOC generation: [markdown-toc](https://github.com/jonschlinkert/markdown-toc) (run `make toc`).
