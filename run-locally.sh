#! /bin/bash
./mvnw clean package
./mvnw -U spring-boot:run -pl hotel-spring-impl