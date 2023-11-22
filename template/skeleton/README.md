# ${{ values.artifactId }}

${{ values.description }}

## Prerequisites
* Java 11+ is installed.
* Apache Maven 3.8.6 or later is installed.
* [Quarkus CLI](https://quarkus.io/guides/cli-tooling) or [Knative Workflow CLI](https://kiegroup.github.io/kogito-docs/serverlessworkflow/latest/testing-and-troubleshooting/kn-plugin-workflow-overview.html) 0.21.3 or later is installed.
* (Optional) Docker is installed.
* (Optional) Podman is installed.
* Kubernetes CLI is installed.
* Visual Studio Code with [Red Hat Java Extension](https://marketplace.visualstudio.com/items?itemName=redhat.java) and 
[Red Hat Serverless Workflow Editor](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-extension-serverless-workflow-editor) 
is installed to edit your workflows.

## References
* [About OpenShift Serverless Logic](https://openshift-knative.github.io/docs/docs/latest/serverless-logic/about.html)
* [SonataFlow Guides](https://kiegroup.github.io/kogito-docs/serverlessworkflow/latest/index.html)

## Building ${{ values.artifactId }}
```bash
mvn clean package
```

## Running ${{ values.artifactId }} in Quarkus dev mode
```bash
mvn quarkus:dev
```
## Building
```bash
kn-workflow quarkus build --image dev.local/${{ values.artifactId }}
```



## Testing with curl
Assuming you stored in [input.json](./input.json) the input parameters (if any), run the following to create an instance of
the ${{ values.artifactId }} workflow:
```bash
curl -v -X POST -H "Content-Type: application/json" http://localhost:8080/${{ values.artifactId }} -d@input.json
```

**TODO**:
* Dev UI
* Data Index
* DB
* `kn-workflow` to build, push and deploy
* Dev guide: 
  * UT and IT
  * Subflows
  * Configuration properties
    * How CM are built

<hr/>
<hr/>

**DEFAULT README FROM QUARKUS ARCHETYPE**

**TO BE REVIEWED**

<hr/>
<hr/>

# ${{ values.artifactId }}

${{ values.description }}

More information on the chosen CI method can be found [here](https://${{ values.sourceControl }}/${{ values.orgName }}/${{ values.repoName }}/blob/main/CI.md).

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/${{ values.artifactId }}-${{ values.version }}-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
