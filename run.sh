#!/usr/bin/env bash
mvn clean
mvn install
docker-compose up --build