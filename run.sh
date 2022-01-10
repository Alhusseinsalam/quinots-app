#!/bin/bash

mvn clean package;

java -jar target/quinots-app-0.1.0.jar \
        --spring.profiles.active=prod \
        --dburl='jdbc:mysql://localhost:6009/testDB2' \
        --dbuser='root' \
        --dbpass='root' \
        --jwtsecret='test';