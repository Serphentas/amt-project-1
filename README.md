# Codemad

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
  - Update its account information

This is provided with the use of Java EE APIs according to an MVC pattern, along with CodeceptJS to test the UI.

## Usage

The easiest way to run our app is with the bundled scripts:

```
./build-image.sh
./run-image.sh
```

Should you wish to pull our [online image](https://github.com/orgs/AMT-Long-Du-Zboub/packages/container/package/amt-project-1), then do:

```
docker pull ghcr.io/amt-long-du-zboub/amt-project-1
docker run -p 9080:9080 ghcr.io/amt-long-du-zboub/amt-project-1
```

Nightly builds can be use in a similar fashion using the `nightly` tag on Docker commands.

## Repository structure

- `Docker` - files required for Docker deployments
- `e2e` - UI tests (CodeceptJS)
- `src` - app sources

## Workflow

Unstable/nightly code belongs in [devs](https://github.com/AMT-Long-Du-Zboub/amt-project-1/tree/devs) while stable code is in [master](https://github.com/AMT-Long-Du-Zboub/amt-project-1/tree/master).

Code is merged into `master` using PRs following associated issues, and a similar procedure is used for `devs` when dealing with feature branches.

## Authors

* Bouyiatiotis Stéphane
* Danai Moïn
* Gomes Da Costa Joshua
* Wonjamouna Rosy-Laure
