
*[Slides](https://docs.google.com/presentation/d/1o5J6twJ9vyvXOYP_qyf5fXrTT5rfl9VULBgo7Pq-gz4/edit#slide=id.p) ·
  [Glossary](analysis.md) ·
  [Analysis](analysis.md) ·
  [Solution](solution.md) ·
  [Functional specification](specification.md) ·
  [Development documentation](development.md)*

---

# Analysis

## Key Actors

The project is based around the following **key actors**:
* **Client**: offers, manages and pays for work items.
* **Worker**: accepts and performs work items.

A **Client** may also take on the work of a **Worker**.

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

## User Stories

### Client

> As a **Client** who has a project in Git,
I want help with my project on a task, without hiring. I want to have a predictable project velocity. I only want 
to **pay for the good work done**, not time, nor bad work. I want my workers to communicate project problems to me 
instead of being heroes. I don’t want to be responsible for the personal development of my workers. I 
split work appropriately and in an incremental fashion.

### Worker

> As a **Worker** who is familiar with Git,
I want to get paid for the amount of work I do and **not for the hours I work**. I want to have a clear specification
to work against. I do not want to be **controlled** by an employer and go through tiring interview processes. I want
to avoid politics, meetings and other things that distract me from my profession. I want to get honest and clear
feedback and guide my own career. I am willing to communicate, not be a hero and work incrementally.

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

# Competition

## [Codemill](http://codemill.io)

## [BountySource](https://www.bountysource.com/)

## [Upwork](https://www.upwork.com/)

