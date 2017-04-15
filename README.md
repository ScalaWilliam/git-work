# git-work
> The marketplace for [GitHub Issues](https://help.github.com/articles/github-glossary/#issue)

See: [Git Work on Google Slides](https://docs.google.com/presentation/d/1o5J6twJ9vyvXOYP_qyf5fXrTT5rfl9VULBgo7Pq-gz4/edit#slide=id.p).

# User Stories

> As a **Client** who has a project running in [Git](https://www.quora.com/How-can-I-explain-what-Git-is-does-to-someone-who-is-not-a-programmer/answer/Jake-Boxer), I want help with my project on a task, without hiring. I want to have a predictable project velocity. I only want to **pay for the good work done**, not time, nor bad work. I want my workers to communicate project problems to me instead of being heroes. I don’t want to be responsible for the personal development of my workers. I am willing to split work appropriately and in incremental fashion.

> As a **Worker** who is familiar with [Git](https://www.quora.com/How-can-I-explain-what-Git-is-does-to-someone-who-is-not-a-programmer/answer/Jake-Boxer), I want to get paid for the amount of work I do and **not for the hours I work**. I want to have a clear specification to work against. I do not want to be **controlled** by an employer and go through tiring interview processes. I want to avoid politics, meetings and other things that distract me from my profession. I want to get honest and clear feedback and guide my own career. I am willing to communicate, not be a hero and work incrementally.

# Explanation
GitHub Issues are like e-mail threads, and comments are like individual e-mails. But they are focused around code,
and live together with the code repository and version control. With version control changes from multiple people
can be controlled and integrated. A typical e-mail thread would be around one subject and that is the same with an
Issue.

Git Work targets a technical manager or architect who builds software incrementally,
where the individual pieces of work would last less than half a working day. Where the piece is too large,
 it must be broken down.
 
There are no obligations from either side, so no contracts aside those of rights and licensing.
By taking on larger or unclear pieces of work, the **Worker** risks not being paid by the **Client** for the work done,
and the **Client** risks wasting his own time and not getting what he wants.

We are not interested in taking on Escrow services. There are other vendors like Upwork for that, but you'll spend 
far more time wrestling with people rather than simply getting on with the work.
 

We'll put in extra effort to make sure a good full system workflow is set up as Git Work is only a **piece of the puzzle**
and not a full fledged system for managing work. For example, it can be used with Gitchiu which lets you
create new Issues from commits very quickly. This lets developers work incrementally in a bug-driven development fashion
and communicates important information to the manager behind the delivery.

Feedback is very important, and **Workers** should always communicate to their best ability as should the **Clients**.
Communication outside of Issues is strongly discouraged because it leads to poorer quality of work.
 We will have metrics that help determine good working practices. Well documented Issues should be easy to get **Workers**
 for, whereas badly documented ones will have difficulty.

Initially the system will not be immediately open to new **Clients** to ensure the right workflow is built up.
 We're open to very good incremental technical managers/architects who could become beta-testers and potentially
  full fledged partners in this system. Once the essential bits are complete, Git Work should be finished
  and only small improvements necessary. It should be possible to build new systems on top of Git Work but we 
  cannot see a clear path for it yet. Definitely we see potential for customising user validation 
  and payment methods for example, as currently only PayPal would be supported for simplicity.

The system is open to **Workers**.

See the <a href="https://github.com/ScalaWilliam/git-work/wiki/USP">USP Wiki page</a> to have a look at the competition and some philosophy.

# User Command Interface

Git Work is used via the [GitHub Issues](https://help.github.com/articles/github-glossary/#issue) **comments**.

The **User** is the person making the **Comment**.

A **Command** is a **Comment** that includes the **Listener User Login**, such as `@git-work do this`.

A **Listener User** is a GitHub user account that periodically checks for new **Commands** using
the [GitHub Notifications API](https://developer.github.com/v3/activity/notifications/).

When a **Command** is received, the **Listener** will respond with a **Comment** that includes the **User**'s **Login** in response.

In case of **GitHub**, **User GitHub Login** is not the same as the **User GitHub ID**.
Latter is permanent, former can be changed. [Example API Endpoint for User by ID](https://api.github.com/user/5359646).

All stored references shall be absolute and permanent identifiers as we must be able to target multiple Issue systems.

# Issue-Level Comment Command examples

1. Open work: `@git-work $10.00 for this` or `@git-work $10.00 for this. 24h $10.00 bonus`
2. Give work: `@git-work give to @Worker`
3. Take work: `@git-work take`
4. Decline work: `@git-work decline`
5. Pay for work: `@git-work pay @Worker` or `@git-work pay with bonus @Worker`
6. Take away work: `@git-work take away`.

# User Validation

Commands are not executed until the **User** is **Validated**.

When the **User** is not **Validated** the bot will respond with a
link to `https://git.work/validate/` to achieve **User Validation** using the **Validation Flow**.

Example message: `@ScalaWilliam please [validate yourself](https://git.work/validate/) first.`.

**User Validation** requests authorization with the **User**'s GitHub
account and a **Validated** PayPal account for security reasons. It may also request Acceptance
of Contribution Licence Agreement or the like. Detail is TBD.

When successful, the **Validation Flow** executes the command that the **User** requested for,
and returns the **User** to the page once that **Command** has been executed.

# Commands and Flow

## Open work

Includes the **Issue** in the system as an **Open Work Item** with a **Price** and a reference to the **Issue** and
the **Work Item Owner** ("**Client**") being the **User** of this **Command**.

This **Open Work Item** will be visible in the index of **Open Work Items** and *may* be shown in the homepage.

**Time Bonus** is optional, and is executed in the **Pay for work** phase.
 
Example response: `@ScalaWilliam work is [now open](https://git.work/work/?id=abcd).`.

## Give work

For an **Open Work Item**, send an **Take Request** to the **Worker User** (target user of the command).
This turns this item into a **Given Work Item**.

For a **Given Work Item**, another **Take Request** will cancel an existing one.

Otherwise the command is ignored.

Example response: `@ScalaWilliam work is offered to @Worker`.

A **Worker User** who is not a **Validated User** will be asked to go through **User Validation** as well.

Example message: `@Worker, please [validate yourself](https://git.work/validate) to take work from @ScalaWilliam`.

## Take Work 

A **Given Work Item** can be turned into **Taken Work Item** only by the **Worker User**.

**Worker User** can request this work directly in the Issue rather than through **Git Work**.
We shall not include the automated taking functionality to ensure that the **Owner** is in control.
We also want to avoid having to take work away from a user when we know in advance they
are potentially a nuisance for instance or the **Client** already had a bad experience with them previously.

Example response: `@ScalaWilliam, @Worker took this work`.

## Decline Work

A **Given Work Item** can be turned into an **Open Work Item** by the **Worker User**.

A **Taken Work Item** can be turned into an **Open Work Item** by the **Worker User** if he realises he cannot
complete the task.

Example response: `@ScalaWilliam, @Worker declined this work`.

## Pay for work

Only works for **Taken Work Item**. The **Target User** *must* match the **Worker User**.

When this is initiated, it becomes a **Paying Work Item**.

Example response: `@ScalaWilliam, pay @Worker [here](https://git.work/work/pay/?id=abcd)`.

This will initiate steps required to immediately pay the **Worker User** the **Price** of work.
The sum of money the **Worker User** receives *may* be less than the **Price** due to money transfer fees.

When the transaction is complete, it becomes **Paid Work Item**.

Example response: `@Worker, @ScalaWilliam paid $20.00 for your work`.

If bonus is included then the bonus specified in **Open Work Item** is specified.

## Take away work

Turn a **Taken Work Item** into an **Open Work Item**.

If the user is taking longer than `X` days (let's say, 7) or longer than `bonus` time, the **Owner** can take away work.

It's advised to discuss with the **Worker** about this but the **Owner** still needs to be in full control of the project.

Example response: `@Worker, @ScalaWilliam took away the work`.

## Cancel work

An **Open Work Item** can be made into **Cancelled Work** by the **Work Item Owner**.

Example response: `@ScalaWilliam, work was cancelled`.

# Other interfaces

## Web interface

The homepage contains a list of **Open Work**. This list *should* be filterable and readable without
authentication to attract as many people as possible.

Each item in this list will display:
* Repository name or **Work Owner**.
* Issue Title
* Issue Price
* How long ago it was Issued
* Tags (eg language)
Clicking on the item will lead to a the item's page.

The filter shall allow you to *deselect* stuff rather than select stuff, so you eliminate stuff you are not interested in.
This filter shall be persisted between user sessions.

For filtering, categories and tagging we *may* use information from the Issue as well as information from the Project itself.

There is an Archive page of work in other stages.

The web interface shall be mobile compatible (not necessarily fully responsive) so that it is usable via a mobile phone like GitHub is.

It shall use [progressive enhancement](https://www.shopify.com/partners/blog/what-is-progressive-enhancement-and-why-should-you-care).

It will include static social media tags for discoverability.

### Work Item page
Each **Work Item** has a page of its own that links to the **Issue** directly and all the relevant details.

The details will include at minimum what is displayed on the **Open Work** page, but also the item state, and the log of what has been happening as a timeline.

Individualised social media tags will be included for discoverability.

## Event-based interface

We could potentially post new **Open Work** items to multiple targets, such as:
* Twitter, tagged with specific languages
* Slack, to specific language channels for instance
* LinkedIn, or some other data science ways of finding workers and targeting them
* Instagram/Pinterest potentially to hunt for web designers
* Google Advertising
 
These are beyond the scope of this project and more in the scope of targeted information distribution
so that we can get more workers in. When we do this of course we can use customised landing pages
to get an idea of what works and what doesn't, and where the best or worst people come from.

# Development

First, [Clone the repository](https://help.github.com/articles/cloning-a-repository/).

## Running locally

1. <a href="https://www.scalawilliam.com/essential-sbt/">Install SBT</a>
2. Inside the Git clone of this project, run: `sbt run`
3. Go to http://localhost:9000

## Developing

1. <a href="https://www.jetbrains.com/idea/">Install IntelliJ IDEA</a> Community Edition or Ultimate Edition.
2. Make sure to install the Scala plugin during set up.
3. Install the <a href="http://scalafmt.org/">scalafmt</a> plugin.
4. <a href="https://www.jetbrains.com/help/idea/2017.1/getting-started-with-sbt.html#import_project">Import the SBT project</a>.

## Editing the content

It's all in `dist/www`. You can probably use something like <a href="http://brackets.io/">Brackets</a> for that.

# Deployment

We use [git.watch](https://git.watch) to deploy automatically to a bare metal server.
On push to GitHub we trigger the [push script](push). Bare gives us instant deployment and a filesystem.

This project is unlikely to need scaling except for the website which we can always put behind a CloudFront, Fastly 
or something similar.

In the current deployment, we have an [nginx](https://www.nginx.com/resources/wiki/) instance 
[reverse-proxying](https://www.nginx.com/resources/glossary/reverse-proxy-server/) to the Play application instance.

# Technical choices

I chose this stack because of my experience and familiarity with it.

- [Scala](http://www.scala-lang.org/news/) and [Play framework](https://www.playframework.com/documentation/2.6.x/Migration26) because I'm experienced in it. See [ActionFPS](https://github.com/ScalaWilliam/ActionFPS) and [Git Watch](http://git.watch/) which also use Event Source.
- Build tool: [SBT](https://www.scalawilliam.com/essential-sbt/) default for Play and supports Docker.

# Licence
Open source & non-free.
