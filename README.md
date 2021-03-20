# Searchmetrics interview

![Searchmetrics](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/sm-logo.png?raw=true)

## Build the Java Application

Clone the repository:

```shell
$ git clone https://github.com/gicappa/searchmetrics-interview.git
```

It is possible to build the code using the maven wrapper `./mvnw` or a
preexisting maven installation (version 3.6+).

Launch the build with the command:

```shell
$ ./mvnw clean verify
```

The process starts until all the modules are built successfully, and the
terminal shows a message similar to the following one:

```shell
...
[INFO] Reactor Summary for xchange-rates 1.0-SNAPSHOT:
[INFO]
[INFO] xchange-rates ...................................... SUCCESS [  0.947 s]
[INFO] xchange-api ........................................ SUCCESS [ 21.525 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  23.845 s
[INFO] Finished at: 2021-03-20T03:25:24+01:00
[INFO] ------------------------------------------------------------------------
```

All the necessary jar files are generated.

### Build the and run Docker images

Once the Java part is successfully built, it is possible to build and
run the docker image using docker compose:

```shell
$ docker-compose up
```

This process can take some time since it needs to download and build the
docker image that will contain the final application of the exercise.

The server will start on the port 8080.

### Creating an uber-jar

It is also possible to build an `uber-jar` that is a jar that contains
the application and all its dependencies so to be easier to be installed
and launched as a standalone server.

To build the `uber-jar` package:

```shell
$ ./mvnw verify -Dquarkus.package.type=uber-jar
```
#### Build Result

At the end of the build process, it is created a uber-jar with all the needed dependencies ready to be launched:

* xchange-api/target/xchange-api-1.0-SNAPSHOT-runner.jar

### Launching the Uber JAR

Executing the following command from the terminal to launch the application:

```shell
gicappa @ gianka in ~/projects/interviews/searchmetrics-interview on git:main x
$ java -jar xchange-api/target/xchange-api-1.0-SNAPSHOT-runner.jar                                                                                                  [3:29:34]
 __  ____  __  _____   ___  __ ____  ______
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-03-20 03:29:47,954 INFO  [io.quarkus] (main) xchange-api 1.0-SNAPSHOT on JVM (powered by Quarkus 1.12.2.Final) started in 0.976s. Listening on: http://0.0.0.0:8080
2021-03-20 03:29:47,962 INFO  [io.quarkus] (main) Profile prod activated.
2021-03-20 03:29:47,963 INFO  [io.quarkus] (main) Installed features: [cdi, rest-client, rest-client-jackson, resteasy, resteasy-jackson, scheduler]
2021-03-20 03:29:50,609 INFO  [sea.fet.QuarkusBtcUsdFetcher] (executor-thread-1) BitcoinRate{symbol='BTC-USD', lastTradePrice='56391.2'}
```

Once the server has started, access the url: `http://localhost:8080/`.

### Testing the server
To test the server it is possible to open a browser or use curl with the addresses:

```shell
$ curl -v http://localhost:8080/rates/btc-usd/latest                                                                                                                [3:40:20]
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /rates/btc-usd/latest HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 200 OK
< Content-Length: 67
< Content-Type: application/json
<
* Connection #0 to host localhost left intact
{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:40:30.002741Z"}* Closing connection 0
```

and 

```shell
$ curl -v http://localhost:8080/rates/btc-usd/\?startDate\=2021-01-01\&endDate\=2021-03-31                                                                          [3:40:32]
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /rates/btc-usd/?startDate=2021-01-01&endDate=2021-03-31 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
>
< HTTP/1.1 200 OK
< Content-Length: 1225
< Content-Type: application/json
<
* Connection #0 to host localhost left intact
[{"btc":1.0,"usd":56391.2,"timestamp":"2021-03-20T02:40:20.003138Z"},{"btc":1.0,"usd":58584.6,"timestamp":"2021-03-20T02:40:25.003341Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:40:30.002741Z"},{"btc":1.0,"usd":58584.6,"timestamp":"2021-03-20T02:40:35.005605Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:40:40.005320Z"},{"btc":1.0,"usd":56391.2,"timestamp":"2021-03-20T02:40:40.969843Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:40:45.967893Z"},{"btc":1.0,"usd":56391.2,"timestamp":"2021-03-20T02:40:50.968561Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:40:55.969462Z"},{"btc":1.0,"usd":56391.2,"timestamp":"2021-03-20T02:41:00.967943Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:41:05.970215Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:41:10.936029Z"},{"btc":1.0,"usd":58584.6,"timestamp":"2021-03-20T02:41:15.936018Z"},{"btc":1.0,"usd":58584.6,"timestamp":"2021-03-20T02:41:20.934531Z"},{"btc":1.0,"usd":56391.2,"timestamp":"2021-03-20T02:41:25.934835Z"},{"btc":1.0,"usd":58584.6,"timestamp":"2021-03-20T02:41:30.935263Z"},{"btc":1.0,"usd":58584.6,"timestamp":"2021-03-20T02:41:35.933043Z"},{"btc":1.0,"usd":58910.0,"timestamp":"2021-03-20T02:41:40.900679Z"}]* Closing connection 0
```
### Configuration

The scheduling can be configured in the file `./xchange-api/src/main/resources/application.properties` setting a cron like expression on the property:

```properties
cron.expr=*/5 * * * * ?
```
# Application Design

## Let's start
When creating a new system, there are always different needs pulling in different directions:

- the need to understand the business and derive a mental model out of it 
- the need for drafting an architecture/design deferring all the choices that are not strictly currently needed, sketching the first idea of a possible solution 
- the need to implement the simplest possible design to fulfil the initial requirements without anticipating any future requirements

Even if they seem to be competing, they are all driven by the most important aspect of software development: *bring value to its stakeholders*. Furthermore, as soon as possible.  

The troubles of creating software are mainly coming from the misconception that knowledge can be transferred like water, from one glass to another: and it would be enough to talk to a client, gather his needs and write the software.
As I see it, this is very far from reality. 

_Writing software is the side effect of a learning process that explore solutions in an iterative way._ 

## Emergent Architecture
I'm fully convinced of the importance of an emergent design and modeling, but to bootstrap a greenfield project several techniques/tools ma come to the rescue:

- A quick Event Storming to model the system 
- C4 model to sketch the architecture's components  
- TDD to abide by the 4 rules of simple design to implement a version of the software that fulfils the customer requirements without anticipating development choices.

This phase is generally quick and is subject to iteration and changes over the lifetime of the project. The goal is to produce a result as soon as possible to receive feedback (from stakeholders, clients, market, etc.) and measure how much value has been provided. 

### Event Storming
Event Storming is a technique that allows modelling the software from the (business) events happening in the system. 
The modelling is usually done using a long enough paper roll hanged on a wall, sticking post-its on it with different colour codes to represent events, commands aggregates and read models. Eventually, the outcome is a good start to model the software. 

#### Event Storming Session for XChange Rate
![Event Storming](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/event-storming.png?raw=true)

This is an example of a tiny event storming session for the current exercise.

### C4 Model
The C4 model is a lightweight approach to diagramming software architecture, based upon abstractions that reflect how software architects and developers think about and build software. It provides four main diagrams (systems, containers, components, classes) to describe several aspects of the architecture using a small set of abstractions.

#### System Context
Represents the higher-level view of the system with a trader user able to call it and its capability to query an external rates provider.

![C4 System Context](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-system-context.png?raw=true)
#### Containers

Entering in more details, the container diagram represents the interaction between two subprocesses: the frontend that queries the backend (APIs) and the backend that fetch rate information from an external system. 
![C4 Containers](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-containers.png?raw=true)

#### Components

Drilling down in more details we see the internal of the API subsystem (the frontend has not being implemented) composed by a scheduled fetcher of bitcoins rates, a RESTful API provider and a storage system for the historical data. 

![C4 Components](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-components.png?raw=true)

### Classes
In the C4 model, I usually avoid the class level of the diagram that is too volatile and doesn't give me valuable information to share with the stakeholders.

### Extreme Programming (XP) Techniques
Extreme programming provides a set of techniques to implement software fulfilling the agile manifesto values and principles.

I've done the exercise using TDD and always keeping in mind the SRP and the 4 rules of simple design :

* Passes the tests
* Reveals intention
* No duplication
* Fewest elements

I tried not to overengineer the solution but to be complete. The stack selection was mostly driven by my previous knowledge of the selected tools so to be effective.

I tried to use a TDD approach outside-in, with a double TDD cycle (similar to what is written in the Growing Object-Oriented Software Guided By Tests book) creating the necessary acceptance of automated tests first and using short-lived unit tests to drive the design.

![TDD with acceptance tests](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/tdd-with-acceptance-tests.svg?raw=true)

I find useful to apply a port and adapter architecture when possible (here is barely visible only by the direction of the dependencies)

![Ports and adapter](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/ports-and-adapters-architecture.svg?raw=true)

### Continuous Integration
I've set up a simple SDLC using the Github action as CI server.

# Gap from Production
In production there are many aspects to be taken into account:

- Deployment: setting un a complete SDLC for the software is a must better with a Continuous Deployment / Delivery system. It's not just a matter of tools but also of culture.
- Logging and monitoring: when software products are operational, it is needed to check what is happening when it is not working properly.
- Documentation: without proper documentation, a product is hardly operable, usable, saleable. The documentation should be buildable from the CI and versioned

From the code POV it is missing:
- Front end: I would have done it using react / nextjs infrastructure
- Backend Code: I would generalize the BTC-USD rate to a more generic Rate model that could host any kind of exchange. I tend to avoid generalization to avoid anticipating abstraction I may not need. Often I implement few concrete cases before introducing a generalization.
- RESTful APIs: they need to be improved to be production-ready (versioning, anti throttling, auth/auth, ...).
- Persistence: obviously an in-memory store is not an option for production. The selection of the database really depends on the functional and non-functional requirements. I'm a big fan of PostgreSQL (SQL) and I would choose MongoDB (NoSQL) since they both are general purpose products.
- Caching: for a high traffic website a caching system is needed to avoid hitting the database as a bottleneck and here there are plenty of options (i.e. Redis that could operate also as a database).
- Scaling: we should separate the API component in a tiny microservice architecture to scale out horizontally the APIs without replicating the fetch of the BTC data. 

![Microservices](https://github.com/gicappa/searchmetrics-interview/blob/main/docs/images/c4-microservices.png?raw=true)

# Coding task

## Context
We recommend not to spend more than 8 hours on this task. Please start with the most important aspects of the project to you.

If you are not able to fully implement the task - this is fine, but please explain what needs to be done in order to have a production ready application.

Please develop a service, that constantly checks the currency exchange rate from Bitcoin to US-Dollar (1 Bitcoin = x USD).

## Requirements
- The check period has to be configurable
- The service must have a REST API with the following endpoints:
- Get latest rate
- Get historical rates from startDate to endDate
- Please describe in a few sentences and/or with a diagram how you planned the project and architecture.
- It should be coded in Java

## Principles
* KISS - keep it simple, stupid
* Single responsibility / Separation of Concerns
* DRY - Don't repeat yourself
* Composition over inheritance
* YAGNI - you ain't gonna need it (no over-engineering)

only use patterns if they make sense and provide a benefit

## Additional Notes
There are no restrictions in terms of technologies, but please take a look at our tech-radar (https://status.searchmetrics.com/tech_radar/)
If you have any questions, please feel free to reach out to us.

