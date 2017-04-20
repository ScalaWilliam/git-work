<!-- navigation -->
*[Slides](https://docs.google.com/presentation/d/1o5J6twJ9vyvXOYP_qyf5fXrTT5rfl9VULBgo7Pq-gz4/edit#slide=id.p) ·
  [Glossary](glossary.md) ·
  [Analysis](analysis.md) ·
  [Solution](solution.md) ·
  [Functional specification](specification.md) ·
  [Development documentation](development.md)*
  
---

<!-- /navigation -->

# Specification

<!-- toc -->

- [User Command Interface](#user-command-interface)
- [Issue-Level Comment Command examples](#issue-level-comment-command-examples)
- [User Validation](#user-validation)
- [Commands and Flow](#commands-and-flow)
  * [Open work](#open-work)
  * [Offer work](#offer-work)
  * [Take Work](#take-work)
  * [Decline Work](#decline-work)
  * [Pay for work](#pay-for-work)
  * [Take away work](#take-away-work)
  * [Cancel work](#cancel-work)
- [Other interfaces](#other-interfaces)
  * [Web interface](#web-interface)
    + [Work Item page](#work-item-page)
  * [Event-based interface](#event-based-interface)

<!-- tocstop -->


# Web interface

This is the website at https://git.work/

It uses [progressive enhancement](https://www.shopify.com/partners/blog/what-is-progressive-enhancement-and-why-should-you-care).

Each page contains social media tags such as:
[1](https://gist.github.com/letanure/10170164), 
[2](https://gist.github.com/tgdev/8365308), 
[3](https://gist.github.com/hotmeteor/5055c1dab2f1043058c2).

## Homepage

The homepage contains a list of **Open Work Item**. It is readable without authentication.

Each item in the list contains the Issue Title and Work Item Price. The item hyperlinks to the **Work Item**'s page.

## Work Item page

This page corresponds to a single **Work Item**.
It contains the Issue Title, the Price and a Hyperlink to the original **Git Issue**.

# Event-based interface

It posts new **Open Work Items** to the [@Git_Work Twitter account](https://twitter.com/Git_Work).
 
# User Command Interface

Git Work is used via [GitHub Issues](https://help.github.com/articles/github-glossary/#issue) **comments**.

The **User** is the person making the **Comment**.

A **Command** is a **Comment** that includes the **Listener User Login**, such as `@git-work do this`.

A **Listener User** is a GitHub user account that periodically checks for new **Commands** using
the [GitHub Notifications API](https://developer.github.com/v3/activity/notifications/).

When a **Command** is received, the **Listener** responds with a **Comment** that includes the **User**'s **Login** in response.

In case of **GitHub**, **User GitHub Login** is not the same as the **User GitHub ID**.
Latter is permanent, former can be changed. [Example API Endpoint for User by ID](https://api.github.com/user/5359646).

All stored references are absolute and permanent identifiers.

# Issue-Level Comment Command examples

1. Open work: `@git-work $10.00 for this` or `@git-work $10.00 for this. 24h $10.00 bonus`
2. Offer work: `@git-work offer to @Worker`
3. Take work: `@git-work take`
4. Decline work: `@git-work decline`
5. Pay for work: `@git-work pay @Worker` or `@git-work pay with bonus @Worker`
6. Take away work: `@git-work take away`.

# User Validation

Commands are not executed until the **User** is **Validated**.

When the **User** is not **Validated** the bot responds with a
link to `https://git.work/validate/` to achieve **User Validation** using the **Validation Flow**.

Example message: `@ScalaWilliam, please [validate yourself](https://git.work/validate/) first.`.

**User Validation** requests authorization with the **User**'s GitHub
account and a **Validated** PayPal account for security reasons. It may also request Acceptance
of Contribution Licence Agreement or the like. Detail is TBD.

When successful, the **Validation Flow** executes the command that the **User** requested for,
and returns the **User** to the page once that **Command** has been executed.

# Commands and Flow

![](git-work.png)

_Made with draw.io_ 

## Open work

Includes the **Issue** in the system as an **Open Work Item** with a **Price** and a reference to the **Issue** and
the **Work Item Owner** ("**Client**") being the **User** of this **Command**.

This **Open Work Item** is visible in the index of **Open Work Items** and *may* be shown in the homepage.

**Time Bonus** is optional, and is executed in the **Pay for work** phase.
 
Example response: `@ScalaWilliam, work is [now open](https://git.work/work/?id=abcd).`.

## Offer work

For an **Open Work Item**, send an **Take Request** to the **Worker User** (target user of the command).
This turns this item into a **Offered Work Item**.

For a **Offered Work Item**, another **Take Request** cancels an existing one.

Otherwise the command is ignored.

Example response: `@ScalaWilliam, work is offered to @Worker`.

A **Worker User** who is not a **Validated User** is asked to go through **User Validation** as well.

Example message: `@Worker, please [validate yourself](https://git.work/validate) to take work from @ScalaWilliam`.

## Take Work 

An **Offered Work Item** can be turned into **Taken Work Item** only by the **Worker User**.

**Worker User** can request this work directly in the Issue rather than through **Git Work**.
We shall not include the automated taking functionality to ensure that the **Owner** is in control.
We also want to avoid having to take work away from a user when we know in advance they
are potentially a nuisance for instance or the **Client** already had a bad experience with them previously.

Example response: `@ScalaWilliam, @Worker took this work`.

## Decline Work

An **Offered Work Item** can be turned into an **Open Work Item** by the **Worker User**.

A **Taken Work Item** can be turned into an **Open Work Item** by the **Worker User** if he realises he cannot
complete the task.

Example response: `@ScalaWilliam, @Worker declined this work`.

## Pay for work

Only works for **Taken Work Item**. The **Target User** matches the **Worker User**.

When this is initiated, it becomes a **Paying Work Item**.

Example response: `@ScalaWilliam, pay @Worker [here](https://git.work/work/pay/?id=abcd)`.

This initiates the steps required to immediately pay the **Worker User** the **Price** of work.
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
