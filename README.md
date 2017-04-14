# git-work
> The marketplace for [GitHub Issues](https://help.github.com/articles/github-glossary/#issue)

# User Interface

Git Work is used via the [GitHub Issues](https://help.github.com/articles/github-glossary/#issue) **comments**.

The **User** is the person making the **Comment**.

A **Command** is a **Comment** that includes the **Listener User**'s ID, such as `@git-work do this`.

A **Listener User** is a GitHub user account that periodically checks for new **Commands** using
the [GitHub Notifications API](https://developer.github.com/v3/activity/notifications/).

When a **Command** is received, the **Listener** will respond with a **Comment** that includes the **User**'s **Login** in response.

In case of **GitHub**, **User GitHub Login** is not the same as the **User GitHub ID**.
Latter is permanent, former can be changed. [Example API Endpoint for User by ID](https://api.github.com/user/5359646).

All stored references shall be absolute and permanent identifiers as we must be able to target multiple Issue systems.

# Issue-Level Comment Command examples

1. Open work: `@git-work $10.00 for this`
2. Give work: `@git-work give to @Worker`
3. Take work: `@git-work take`
4. Decline work: `@git-work decline`
5. Pay for work: `@git-work pay @Worker`

# User Validation

Commands are not executed until the **User** is **Validated**.

When the **User** is not **Validated** the bot will respond with a
link to `https://git.work/validate/` to achieve **User Validation** using the **Validation Flow**.

**User Validation** requests authorization with the **User**'s GitHub
account and a **Validated** PayPal account for security reasons.

When successful, the **Validation Flow** executes the command that the **User** requested for,
and returns the **User** to the page once that **Command** has been executed.

# Commands and Flow

## Open work

Includes the **Issue** in the system as an **Open Work Item** with a **Price** and a reference to the **Issue** and
the **Work Item Owner** being the **User** of this **Command**.

This **Open Work Item** will be visible in the index of **Open Work Items** and *may* be shown in the homepage.

## Give work

For an **Open Work Item**, send an **Take Request** to the **Worker User** (target user of the command).
This turns this item into a **Given Work Item**.

For a **Given Work Item**, another **Take Request** will cancel an existing one.

Otherwise the command is ignored.

A **Worker User** who is not a **Validated User** will be asked to go through **User Validation** as well.

## Take Work 

A **Given Work Item** can be turned into **Taken Work Item** only by the **Worker User**.

## Decline Work

A **Given Work Item** can be turned into an **Open Work Item** by the **Worker User**.

A **Taken Work Item** can be turned into an **Open Work Item** by the **Worker User** if he realises he cannot
complete the task.

## Pay for work

Only works for **Taken Work Item**. The **Target User** *must* match the **Worker User**.

When this is initiated, it becomes a **Paying Work Item**.

This will initiate steps required to immediately pay the **Worker User** the **Price** of work.
The sum of money the **Worker User** receives *may* be less than the **Price** due to money transfer fees.

When the transaction is complete, it becomes **Paid Work Item**.

## Cancel work

**Open Work Item**, **Given Work Item**, **Taken Work Item** can be made into **Cancelled Work** by the **Work Item Owner**.

# Deployment
We use [git.watch](https://git.watch) to deploy automatically to a bare metal server.
On push to GitHub we trigger the [push script](push). Bare gives us instant deployment and a filesystem.
If the project reaches large enough scale we can consider moving to the cloud but for now
this is good enough.

# Unique Selling Point

See the <a href="https://github.com/ScalaWilliam/git-work/wiki/USP">USP Wiki page</a>.
