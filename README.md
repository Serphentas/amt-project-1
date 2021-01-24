# Codemad

![](img/homepage.jpg)

## 0 Table of contents

- [1 Introduction](#1-introduction)
- [2 Usage](#2-usage)
- [3 Repository structure](#3-repository-structure)
- [4 Workflow](#4-workflow)
- [5 Added features](#5-added-features)
- [6 Authors](#6-authors)

## 1 Introduction

The purpose of Codemad is to provide a simplified version of StackOverflow, with the following features:

- Anonymous users can:
  - Browse questions and their answers & comments
  - Sign up on the website
  - Search for questions
- Authenticated users can:
  - Ask questions
  - Answer to questions
  - Comment on answer & questions
  - Vote on questions, answers, and comments
  - Update their account information

This is provided with the use of Java EE APIs according to an MVC pattern, along with CodeceptJS to test the UI.

## 2 Usage

### Cleanup

In any case of problems, you can ensure all your images and containers are up to date:

```
docker kill codemad_db codemad_app
docker rm -f codemad_db codemad_app
docker rmi -f serphentas/amt-project-1-db serphentas/amt-project-1-app
```

### Release

The easiest way to run our app is with the bundled scripts:

```
./run.sh
```

Should you wish to use our development release, run:

```
./run_devs.sh
```

and access the website at [http://localhost:9080](http://localhost:9080).

### Development

Ensure you have a gamification API key:

```
export GAMIF_KEY=$(python3 populate.py)
```

Ensure that `serverName` and the hostnames for gamification endpoints are up to date in [server.xml](src/main/liberty/config/server.xml). Usually, for dev purposes you'll want `127.0.0.1` for your database hostname, same for gamification.

Then, run this from the repository root:

```
mvn liberty:dev
```

#### Debug

One fat-ass fool-proof command for devs:

```
docker rm -f codemad_db codemad_app && docker rmi -f serphentas/amt-project-1-app serphentas/amt-project-1-db && mvn clean package && docker build -t serphentas/amt-project-1-app . && cd docker/images/mariadb/ && docker build -t serphentas/amt-project-1-db . && cd ../../.. && ./run.sh
```

### Run the tests

Simply run this from the repository root, to use the coverage test from Intellij
This script allow to develop too.

```
./run-integration.sh
```

For the e2e tests, you will need to install Codecept first, in the e2e folder
```
npm install codeceptjs puppeteer --save-dev
```
then you can use this command in the e2e folder
```
npx codeceptjs run --steps
```

Remember to have the website running at [http://localhost:9080](http://localhost:9080).

## 3 Repository structure

- `.github/workflows` - GitHub Actions workflow definitions
- `docker` - files required for Docker deployments
  - `images` - service-specific Dockerfiles and dependencies
  - `topologies` - stable and unstable docker-compose YAML files
- `e2e` - UI tests (CodeceptJS)
- `load-tests` - load tests (JMeter)
- `src` - application sources
  - `main`
    - `java/stackoverflow`
      - `application` - business logic
      - `domain` - model definitions
      - `infrastructure/persistence` - JDBC-based and in-memory data repositories
      - `ui/web` - servlets
    - `liberty/config` - OpenLiberty configuration and dependencies
    - `webapp` - front-end resources (JSP files and UI toolkits)
  - `test` - JUnit files

## 4 Workflow

Unstable/development code belongs in [devs](https://github.com/AMT-Long-Du-Zboub/amt-project-1/tree/devs) while stable code is in [master](https://github.com/AMT-Long-Du-Zboub/amt-project-1/tree/master).

## 5 Added features

- Vote: can vote on comments and answers
- Added integration tests for JdbcCommentRepository
- e2e: initial tests work again

### Remaining bits

- Cove more use cases with CodeceptJS
- Complete JUnit tests
- Use Mockups for simple unit testing

## 6 Authors

* Bouyiatiotis Stéphane
* Danai Moïn
* Gomes Da Costa Joshua
* Wonjamouna Rosy-Laure
