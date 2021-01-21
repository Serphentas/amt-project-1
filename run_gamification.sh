#!/bin/bash
docker run -e MYSQL_HOST="$MYSQL_HOST" -p 8080:8080 serphentas/amt-project-2