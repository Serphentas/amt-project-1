#!/bin/bash
mvn clean package
docker build -t amt-long-du-zboub/amt-project-1 .