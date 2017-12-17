#!/usr/bin/env bash
kill -9 $(lsof -ti tcp:8080)

mvn clean install

java -jar target/searcher-0.0.1-SNAPSHOT.jar