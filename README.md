# Searchmetrics interview

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

## Inception
When creating a new system there are always different needs pulling in different directions: 
- the need to understand the business and derive a mental model out of it 
- the need of drafting an architecture/design deferring all the choices that are not strictly currently needed, skecthing a first idea of a possible solution 
- the need to implement the implement the simplest possible design to fulfil the initial requirements without anticipating any future requirements 

Some of the techniques/tools that come to the rescue when starting a new software endeavour, are:
- Event Storming/DDD to model the system creating 
- C4 model to sketch the architecures components  
- DDD/BDD/TDD to abide to the 4 rules of simple design to implement a version of the software that fulfil to the customer requirements without anticipate development choices. 

### Event Storming
A technique I use to start the modeling of a system is the Event Storming. The idea is to model the system starting from  the events happening at business level (even if not direclty involving a software component). 
The modeling is done using a long enough paper roll hanged on a wall and using post-it with different color codes to represent events, commands aggregates and so on. 

#### Event Storming Session for XChange Rate 
![Event Storming](https://github.com/gicappa/searchmetrics-interview/blob/master/docs/images/event-storming.png?raw=true)

### C4 Model
This model is lightweight way to represent different levels of an architecture (systems, containers, components, classes) introducing all the needed details and initial technical choices so to disseminate and discuss the solution with the project stakeholders.

#### System Context
![C4 System Context](https://github.com/gicappa/searchmetrics-interview/blob/master/docs/images/c4-system-context.png?raw=true)
#### Containers
![C4 Containers](https://github.com/gicappa/searchmetrics-interview/blob/master/docs/images/c4-containers.png?raw=true)
#### Components 
![C4 Components](https://github.com/gicappa/searchmetrics-interview/blob/master/docs/images/c4-components.png?raw=true)
### Classes
In the C4 models I usually avoid the class level of the diagram that is to volatile and doesn't give me aluable information to share with the stakeholders.

### Extreme Programming (XP) Techniques
Extreme programming provides a set of techniques to implement software fulfilling the agile manifesto values and principles.

Over the time many techniques arised together with XP  