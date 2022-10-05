Table of Contents
=================

* [Table of Contents](#table-of-contents)
  * [Pre-requisites](#pre-requisites)
  * [Commands](#commands)
  * [Profiles](#profiles)
  * [Paths](#paths)
  * [Logging](#logging)
  * [Dependencies](#dependencies)
    * [spring-boot-starter-web](#spring-boot-starter-web)
    * [spring-boot-starter-test](#spring-boot-starter-test)
  * [Actuator](#actuator)
  * [Spring Cloud Contract](#spring-cloud-contract)
  * [Spring Security](#spring-security)
  * [Containerizing Apps](#containerizing-apps)
  * [Kubernetes](#kubernetes)
    * [Kind Cluster](#kind-cluster)
    * [Approach 1 (Manifest Files)](#approach-1-manifest-files)
      * [Build Docker Image](#build-docker-image)
      * [Push Docker Image](#push-docker-image)
      * [Apply Manifest Files](#apply-manifest-files)
      * [Port Forward](#port-forward)
    * [Approach 2 (Skaffold)](#approach-2-skaffold)
      * [Skaffold](#skaffold)
      * [Remote Debug](#remote-debug)
    * [Approach 3 (Kustomize)](#approach-3-kustomize)
      * [Kustomize](#kustomize)
    * [Approach 4 (Scaffold + Kustomize)](#approach-4-scaffold--kustomize)
    
    
## Features

| | | |
|--|--|--|
|Spring |Spring Cloud Contract |✅ |
| |Devtools |✅ |
|Maven |Wrapper Script |✅ |
| |Enforcer Plugin |✅ |
| |Git Commit Id Plugin |✅ |
|Containerization |Dockerfile |✅ |
| |Buildpacks |✅ |
| |Jib |✅ |
|Kubernetes |Skaffold | ✅|
| |Kustomize | ✅|
|Prometheus |Micrometer | ✅|
  
## Pre-requisites

This project uses `lombok` dependency and requires `lombok` plugin to be installed from marketplace to resolve IDE compilation issues.

## Commands

| Command                                                                                | Purpose                                                                                                           |
|----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| ./mvnw clean install                                                                   | Build the project                                                                                                 |
| ./mvnw clean spring-boot:run                                                           | Start the application                                                                                             |
| ./mvnw clean spring-boot:run --debug                                                   | Start the application with debug logs for a selection of core loggers and logs a conditions report to the console |
| ./mvnw clean package                                                                   | Build the jar                                                                                                     |
| java -jar target/spring-boot-example-1.0-SNAPSHOT.jar                                  | Run packaged application                                                                                          |
| java -Djarmode=layertools -jar target/spring-boot-example-1.0-SNAPSHOT.jar list        | List layers from the jar that can be extracted                                                                    |
| ./mvnw spring-boot:build-image                                                         | Build docker image using buildpacks                                                                               |
| ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=spring-boot-example | Build docker image with provided image name                                                                       |
| docker build -t spring-boot-example .                                                  | Build docker image using Dockerfile                                                                               |
| ./mvnw compile jib:dockerBuild -Dimage=spring-boot-example                             | Build docker image using jib                                                                                      |
| docker run --name spring-boot-example -p 8080:8080 spring-boot-example:latest          | Run docker container                                                                                              |

## Profiles

Set jvm property `-Dspring.profiles.active=prod` to use `application-prod.properties`

## Paths

Check ``ExampleController`` class for list of supported API paths.

## Logging

Set jvm property `-Dlogging.level.com.springboot.example=debug` to override and enable debug logs for the class.

## Dependencies

### spring-boot-starter-web

Includes below dependencies:

- spring-webmvc
- spring-web
- tomcat-embed-[core, el, websocket]
- fasterxml jackson
- slf4j, log4j, logback
- yaml

### spring-boot-starter-test

Includes below dependencies:

- JUnit 4 & 5
- Spring Test & Spring Boot Test
- AssertJ
- Hamcrest
- Mockito
- JSONassert
- JsonPath

## Actuator

Get health status:

``http://localhost:8080/actuator/health``

Get readiness and liveness status:

```
http://localhost:8080/actuator/health/liveness
http://localhost:8080/actuator/health/readiness
```

Get build and git info:

``http://localhost:8080/actuator/info``

Get metrics for an endpoint:

``http://localhost:8080/actuator/metrics/http.server.requests?tag=uri:/``

Get prometheus metrics:

``http://localhost:8080/actuator/prometheus``

## Spring Cloud Contract

Spring cloud contracts are available in `test/resources/contracts` folder.

When you run maven build, the contract test class will be created in `generated-test-sources` folder and the stubs in `target/stubs` folder. 

`mvn package` will generate the `stubs` jar which can be deployed to any repository.

## Spring Security

Some actuator endpoints are secured with IP address restriction.

Accessing http://localhost:8080/actuator/config will fail with `Access Denied` error whereas http://127.0.0.1:8080/actuator/config will return response.

## Containerizing Apps

There are multiple ways to containerize your app.

| Approach                                                     | Image Size | Comments                                                                                                                                                                                                                                                                                        |
|--------------------------------------------------------------|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Paketo Buildpacks                                            | ~270 MB    |                                                                                                                                                                                                                                                                                                 |
| Jib                                                          | ~260 MB    | Jib builds optimized Docker and OCI images for your Java applications without a Docker daemon                                                                                                                                                                                                   |
| Dockerfile - jlink module approach with debian as base image | ~150 MB    | - jlink helps to create a custom runtime with only the required modules<br/>- Using debian as base image has following advantages<br/>1. presence of libc<br/>2. performance issues of Alpine for certian programming languages<br/>3. suitable for testing with tools available out of the box |

For Dockerfile approach, you can get the necessary JDK modules needed to run your application with below command:

```bash
jdeps --ignore-missing-deps -q -recursive --multi-release 11 --print-module-deps --class-path 'target/libs/*' target/spring-boot-example-1.0-SNAPSHOT.jar > jre-deps
.info
```

## Kubernetes

To run the spring boot application in your local kubernetes cluster, follow below steps.

### Kind Cluster

Run below script which will set up a local registry, create a kind kubernetes cluster and link with the registry.

```
./kind/kind-with-registry.sh

Creating cluster "kind" ...
 ✓ Ensuring node image (kindest/node:v1.21.1) 🖼
 ✓ Preparing nodes 📦
 ✓ Writing configuration 📜
 ✓ Starting control-plane 🕹️
 ✓ Installing CNI 🔌
 ✓ Installing StorageClass 💾
Set kubectl context to "kind-kind"
You can now use your cluster with:
```

### Approach 1 (Manifest Files)

#### Build Docker Image

Build the image by specifying the registry address in the image name:

```
# image name defined in pom
./mvnw spring-boot:build-image

# alternatively
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=localhost:5000/apps/spring-boot-example
```

#### Push Docker Image

Push the image to the local registry:

```
docker push localhost:5000/apps/spring-boot-example
```

#### Apply Manifest Files

Apply the manifest files to create the deployment and service components:

```
kubectl apply -f k8s
```

Check the status to ensure everything is working as expected:

```
kubectl get all

NAME                                           READY   STATUS    RESTARTS   AGE
pod/k8s-spring-boot-example-775fcbf645-mzth5   1/1     Running   0          13s

NAME                              TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)   AGE
service/k8s-spring-boot-example   ClusterIP   10.96.223.252   <none>        80/TCP    13s
service/kubernetes                ClusterIP   10.96.0.1       <none>        443/TCP   72s

NAME                                      READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/k8s-spring-boot-example   1/1     1            1           13s

NAME                                                 DESIRED   CURRENT   READY   AGE
replicaset.apps/k8s-spring-boot-example-775fcbf645   1         1         1       13s
```

#### Port Forward

To access the app, port forward to the service:

```
kubectl port-forward service/k8s-spring-boot-example 8080:80
```

### Approach 2 (Skaffold)

Skaffold makes some enhancements to our development workflow when using Kubernetes

- Build the app and create the container (buildpacks)
- Push the container to the registry (Docker)
- Apply the deployment and service YAMLs
- Stream the logs from the Pod to your terminal
- Automatically set up port forwarding

#### Skaffold

Run the following command to have Skaffold build and deploy our application to Kubernetes.

```
skaffold dev --port-forward
```

#### Remote Debug

To remote debug the application, run below skaffold command:

```
skaffold debug --auto-sync --port-forward 
```

Create a remote debug profile in your IDE. 

![scaffold-remote-debug-intellij](images/scaffold-remote-debug-intellij.png?raw=true)

Run in debug mode to attach to the remote port. Add a breakpoint to the code and make a request to start debugging.

### Approach 3 (Kustomize)

Kustomize allows us to customize deployments to different environments.

We can start with a base set of resources and then apply customizations on top of those.

Features

- Allows easier deployments to different environments/providers
- Allows you to keep all the common properties in one place
- Generate configuration for specific environments
- No templates, no placeholder spaghetti, no environment variable overload

#### Kustomize

Run below command to build QA customization with replica count as '2'

```
kustomize build kustomize/qa | kubectl apply -f -
```

### Approach 4 (Scaffold + Kustomize)

Run below command to use Kustomize files with Scaffold:

```
skaffold dev -f skaffold-kustomize.yaml -p qa --port-forward
```
