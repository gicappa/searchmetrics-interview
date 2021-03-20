# Searchmetrics interview

![Searchmetrics](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/sm-logo.png?raw=true)
## Coding task

### Context
We recommend not to spend more than 8 hours on this task. Please start with the most important aspects of the project to you.

If you are not able to fully implement the task - this is fine, but please explain what needs to be done in order to have a production ready application.

Please develop a service, that constantly checks the currency exchange rate from Bitcoin to US-Dollar (1 Bitcoin = x USD).

### Requirements
- The check period has to be configurable
- The service must have a REST API with the following endpoints:
- Get latest rate
- Get historical rates from startDate to endDate
- Please describe in a few sentences and/or with a diagram how you planned the project and architecture.
- It should be coded in Java

### Principles
* KISS - keep it simple, stupid
* Single responsibility / Separation of Concerns
* DRY - Don't repeat yourself
* Composition over inheritance
* YAGNI - you ain't gonna need it (no over-engineering)

only use patterns if they make sense and provide a benefit

### Additional Notes 
There are no restrictions in terms of technologies, but please take a look at our tech-radar (https://status.searchmetrics.com/tech_radar/)
If you have any questions, please feel free to reach out to us.

## Initial Modeling
When creating a new system there are always different needs pulling in different directions:

- the need to understand the business and derive a mental model out of it 
- the need of drafting an architecture/design deferring all the choices that are not strictly currently needed, sketching a first idea of a possible solution 
- the need to implement the simplest possible design to fulfil the initial requirements without anticipating any future requirements

## Emergent Architecture
I'm fully convinced of the importance of an emergent design and modeling, but to bootstrap a greenfield project several techniques/tools ma come to the rescue:

- A quick Event Storming to model the system 
- C4 model to sketch the architectures components  
- TDD to abide to the 4 rules of simple design to implement a version of the software that fulfil to the customer requirements without anticipate development choices.

This phase is generally quick and be subject to iteration and changes over the lifetime of the project.

### Event Storming
Event Storming allows to model the system starting from the (business) events happening in the system. 
The modeling is usually done using a long enough paper roll hanged on a wall, sticking post-it on it with different color codes to represent events, commands aggregates and read models. Eventually, the outcome is a good start to model the software. 

#### Event Storming Session for XChange Rate
![Event Storming](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/event-storming.png?raw=true)

This is an example of quick event storming session for the exercise.
### C4 Model
This model is lightweight way to represent different levels of an architecture (systems, containers, components, classes) introducing all the needed details and initial technical choices so to disseminate and discuss the solution with the project stakeholders.

#### System Context
Represents the higher level view of the system with a trader user able to call it and its capability to query an external rates provider.

![C4 System Context](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-system-context.png?raw=true)
#### Containers

Entering in more details, the container diagram represents the interaction between two subprocesses: the frontend that queries the backend (APIs) and the backend that fetch rate information from an external system. 
![C4 Containers](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-containers.png?raw=true)

#### Components

Drilling down in more details we see the internal of the API subsystem (the frontend has not being implemented) composed by a scheduled fetcher of bitcoins rates, an RESTful API provider and a storage system for the historical data. 

![C4 Components](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-components.png?raw=true)

### Classes
In the C4 models I usually avoid the class level of the diagram that is to volatile and doesn't give me aluable information to share with the stakeholders.

### Extreme Programming (XP) Techniques
Extreme programming provides a set of techniques to implement software fulfilling the agile manifesto values and principles.

I've done the exercise using TDD and always keeping in mind the SRP and the 4 rules of simple design :

* Passes the tests
* Reveals intention
* No duplication
* Fewest elements

I tried not to overengineer the solution but to be complete. The stack selection was mostly driven by my previous knowledge of the selected tools so to be effective.

I tried to use a TDD approach outside-in, with a double TDD cycle (similar to what is written in the Growing Object Oriented Software Guided By Tests book) creating the necessary acceptance automated tests first and using short-lived unit test to drive my design.

![TDD with acceptance tests](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/tdd-with-acceptance-tests.svg?raw=true)

I find useful to apply a port and adapter architecture when possible (here is barely visible only by the direction of the dependencies)

![Ports and adapter](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/ports-and-adapters-architecture.svg?raw=true)

### Continuous Integration
I've set up a simple SDLC using the github action as CI server.

# Taking it to Production
In production there are many aspects to be taken into account:

- Deployment: setting un a complete SDLC for the software is a must better with a continuous Deployment / Delivery system. It's not just a matter of tools but also of culture.
- Logging and monitoring: when a software is operational it is needed to check what is happening when it is not working properly.
- Documentation: without a proper documentation a product is hardly operable, usable, saleable. The documentation should be buildable from the CI and versioned

From the code POV it is missing:
- Front end: I would have done it using react / nextjs infrastructure 
- Persistence: obviously an in memory store is not an option for production. The selection of the database really depends on the functional and non-functional requirements. I'm a big fan of PostgreSQL on the RDBMs side and I would choose mongodb on a NOSQL world.
- Caching: for an high traffic website a caching system is needed to avoid hitting the database as a bottleneck and here there are plenty of options: once for all redis.
- Scaling: we should separate the api in a microservice like architecture to scale out horizontally the apis without replicating the fetch of the btc data. 

![Microservices](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-microservices.png?raw=true)
