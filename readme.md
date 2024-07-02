# Company Occupancy Management

## Todo list

- [x] SSL
- [x] OpenApi
- [x] Basic health check endpoint
- [x] Basic http config
- [x] Challenge task
- [x] End-to-end tests - TDD
- [x] Integration tests - TDD
- [x] Unit tests - TDD
- [x] GHA test
- [x] GHA native build
- [x] GHA Surefire test report
- [x] Docker Hub upload
- [x] Kubernetes / minikube+docker+win limitation
- [x] Postman json
- [x] Detailed readme.md

## Build environment

| Technology   | Version      |
|--------------|--------------|
| Java         | 21           |
| Runtime      | GraalVM 21   |
| Spring Boot  | 3.3          |
| Maven        | 3.9          |
| Dev machine  | Win 10       |
| Docker image | ubuntu:jammy |

## Start with IntelliJ IDEA

Copy and paste the environment variables

```
SERVER_PORT=8081;SERVER_SSL_ENABLED=true;SERVER_SSL_KEY_STORE=classpath:keystore/dev.p12;SERVER_SSL_KEY_STORE_PASSWORD=password;spring.profiles.active=
```

## Test with IntelliJ IDEA

Copy and paste the environment variables

```
SERVER_PORT=8082;SERVER_SSL_ENABLED=true;SERVER_SSL_KEY_STORE=classpath:keystore/dev.p12;SERVER_SSL_KEY_STORE_PASSWORD=password;spring.profiles.active=
```

## Create Win native runnable application

```
./mvnw clean native:compile -Pnative -Dspring.profiles.active=native -Dserver.port=8083 -Dserver.ssl.enabled=true -Dserver.ssl.key-store=classpath:keystore/dev.p12 -Dserver.ssl.key-store-password=password
```

## Start Win native runnable application

```
./target/room_occupancy_manager.exe --spring.profiles.active=native --server.port=8084 --server.ssl.key-store=classpath:keystore/dev.p12 --server.ssl.key-store-password=password --native.reflection-configuration-generator.enabled=false
```

## Start Kubernetes with Win + Minikube + Docker desktop + Kustomize

(Currently, Minikube on Windows+Docker has issues properly handling Ingress hosts)

### Setup namespace

[setup-namespace.yaml](k8s%2Fsetup-namespace.yaml)

### Start the latest version by Docker Hub #latest tag
Hardcoded environment variables are only for demo, KMS is required for production

[start.sh](k8s%2Flatest--all-in-one-start%2Fstart.sh)

### Health check with `minikube tunnel`

https://localhost:8085/health/200ok

### Delete the started app

[delete.sh](k8s%2Flatest--all-in-one-start%2Fdelete.sh)

## Postman collection

[room_occupancy_manager.postman_collection.json](room_occupancy_manager.postman_collection.json)

# Links:

### GitHub Action runs

https://github.com/balazskrizsan/room_occupancy_manager/actions

### Docker Hub

https://hub.docker.com/repository/docker/kbalazsworks/room_occupancy_manager_native/general

### Surefire test report

Failure: https://github.com/balazskrizsan/room_occupancy_manager/actions/runs/9747555341/job/26900567477

Success: https://github.com/balazskrizsan/room_occupancy_manager/actions/runs/9751975436/job/26914758365

# Notes:

### kbalazsworks_common

Copy-paste code from my Maven lib; it has AWS AIM auth token limitation
