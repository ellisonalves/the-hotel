#! /bin/bash
./mvnw -T 1C -U clean package
./mvnw spring-boot:run
