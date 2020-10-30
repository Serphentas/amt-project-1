#!/bin/bash
mvn clean package
docker build -t amt-project-1-app .