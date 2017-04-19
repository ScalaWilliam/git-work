

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
2. Offer work: `@git-work offer to @Worker`
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

![](git-work.png)

_Made with draw.io_ 

## Open work

Includes the **Issue** in the system as an **Open Work Item** with a **Price** and a reference to the **Issue** and
the **Work Item Owner** ("**Client**") being the **User** of this **Command**.

This **Open Work Item** will be visible in the index of **Open Work Items** and *may* be shown in the homepage.

**Time Bonus** is optional, and is executed in the **Pay for work** phase.
 
Example response: `@ScalaWilliam work is [now open](https://git.work/work/?id=abcd).`.

## Offer work

For an **Open Work Item**, send an **Take Request** to the **Worker User** (target user of the command).
This turns this item into a **Offered Work Item**.

For a **Offered Work Item**, another **Take Request** will cancel an existing one.

Otherwise the command is ignored.

Example response: `@ScalaWilliam work is offered to @Worker`.

A **Worker User** who is not a **Validated User** will be asked to go through **User Validation** as well.

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
