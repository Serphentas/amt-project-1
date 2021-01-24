#!/bin/bash
export GAMIF_KEY=$(python3 populate.py)
cd docker/topologies/master
docker-compose up
