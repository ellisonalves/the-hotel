#! /bin/bash
./mvnw -U clean package
./mvnw spring-boot:run -pl hotel-spring-impl