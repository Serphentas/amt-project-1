# Codemad

![](img/homepage.jpg)

## Table of contents

- [Introduction](#introduction)
- [Usage](#usage)
- [Repository structure](#repository-structure)
- [Workflow](#workflow)
- [Authors](#authors)

## Introduction

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

## Usage

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

Simply run this from the repository root:

```
mvn liberty:dev
```

### Run the Tests

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

and access the website at [http://localhost:9080](http://localhost:9080).

## Repository structure

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

## Workflow

Unstable/development code belongs in [devs](https://github.com/AMT-Long-Du-Zboub/amt-project-1/tree/devs) while stable code is in [master](https://github.com/AMT-Long-Du-Zboub/amt-project-1/tree/master).

## Added features

- Vote: can vote on comments and answers
- Added integration tests for JdbcCommentRepository

## Remaining bits

- Cove more use cases with CodeceptJS
- Complete JUnit tests
- Use Mockups for simple unit testing

## Authors

* Bouyiatiotis Stéphane
* Danai Moïn
* Gomes Da Costa Joshua
* Wonjamouna Rosy-Laure
