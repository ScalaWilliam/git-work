## [Git Work](https://git.work/) - the marketplace for [GitHub Issues](https://help.github.com/articles/github-glossary/#issue)

*See: [Git Work on Google Slides](https://docs.google.com/presentation/d/1o5J6twJ9vyvXOYP_qyf5fXrTT5rfl9VULBgo7Pq-gz4/edit#slide=id.p)*.

<!-- toc -->

- [The Problem](#the-problem)
- [Key Actors](#key-actors)
- [User Stories](#user-stories)
- [Explanation](#explanation)
- [Specification](#specification)
- [Development](#development)
- [Licence](#licence)

<!-- tocstop -->

# The Problem

> The "Why"

I have several open source and personal projects, all of which are iteratively developed fully within
 [Git](https://www.quora.com/How-can-I-explain-what-Git-is-does-to-someone-who-is-not-a-programmer/answer/Jake-Boxer)
 [repository management services](https://medium.com/flow-ci/github-vs-bitbucket-vs-gitlab-vs-coding-7cf2b43888a1)
 such as GitHub.
The number of things I want to do exceeds the time I have for doing them, so I am seeking freelance services.

Freelance services' full lifecycle of is composed of multiple parts:
recruitment, hiring, negotiation, management, development and payment.
From my professional experience, these parts are disjoint but I won't get into the reasons why.
When highly coupled parts are disjoint, you typically get inefficiency and I am not a fan of inefficiency.

Git Work fuses this lifecycle into a remarkably simple flow: for example, interviews are reduced to
doing the *actual* work... and if you succeed, getting paid for it!
This is contrary to my experience of interviews where you do very little of the actual work
and have just blown 4 hours of your day having a chat.

The core principle of Git Work is "**Interprocedural Optimization**", where parts that should 
belong together do indeed belong together. This principle may have a profound effect on
developing projects to a level of quality we've not yet seen.

# Key Actors

These are the key categories of actors.

* **Worker**: accepts and performs work items.
* **Client**: offers, manages and pays for work items.

They are not mutually exclusive because a Client may take on work of the Worker.

A **Worker** may belong to professions such as:
* Software architect
* Software engineer
* Quality assurance
* Business analyst
* Software tester
* Technical writer
* Web designer
* Web developer
* Graphics designer
* DevOps engineer
* Visualisation engineer
* Data scientist

# User Stories

> The "Who"

> As a **Client** who has a project running in 
[Git](https://www.quora.com/How-can-I-explain-what-Git-is-does-to-someone-who-is-not-a-programmer/answer/Jake-Boxer),
I want help with my project on a task, without hiring. I want to have a predictable project velocity. I only want 
to **pay for the good work done**, not time, nor bad work. I want my workers to communicate project problems to me 
instead of being heroes. I don’t want to be responsible for the personal development of my workers. I am willing to
split work appropriately and in incremental fashion.

> As a **Worker** who is familiar with
[Git](https://www.quora.com/How-can-I-explain-what-Git-is-does-to-someone-who-is-not-a-programmer/answer/Jake-Boxer),
I want to get paid for the amount of work I do and **not for the hours I work**. I want to have a clear specification
to work against. I do not want to be **controlled** by an employer and go through tiring interview processes. I want
to avoid politics, meetings and other things that distract me from my profession. I want to get honest and clear
feedback and guide my own career. I am willing to communicate, not be a hero and work incrementally.

# Explanation
> The "What" (1/2) 

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

# Specification

> The "What" (2/2)

See [documentation/specification.md](documentation/specification.md).

# Development

> The "How"

See [documentation/development.md](documentation/development.md).

# Licence
* Non-free, but open source.
* Copyright (2016) Apt Elements Ltd ([William Narmontas](https://www.scalawilliam.com/)).
