# Company Occupancy Management

### Todo list:
- [x] SSL
- [ ] OpenApi
- [ ] basic health check endpoint
- [ ] basic http config
- [ ] challenge task
- [ ] e2e tests
- [ ] integration tests
- [ ] unit tests
- [ ] GHA test
- [ ] GHA native build
- [ ] GHA error report
- [ ] Docker Hub upload
- [ ] k8s yamls
- [ ] detailed readme.md

### Start with IntelliJ IDEA:
Copy and paste the environment variables
Run
```
SERVER_PORT=8081;SERVER_SSL_ENABLED=true;SERVER_SSL_KEY_STORE=classpath:keystore/dev.p12;SERVER_SSL_KEY_STORE_PASSWORD=password;spring.profiles.active=
```
Test run
```
SERVER_PORT=8082;SERVER_SSL_ENABLED=true;SERVER_SSL_KEY_STORE=classpath:keystore/dev.p12;SERVER_SSL_KEY_STORE_PASSWORD=password;spring.profiles.active=
```
