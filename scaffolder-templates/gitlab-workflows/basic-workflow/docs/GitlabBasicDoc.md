# Creating a Basic Serverless Workflow via a Software Template with Gitlab

## Overview
This template allows the user to generate a basic workflow project from the user's input.
The workflow will be generated corresponding to the workflow type (assessment, infrastructure) the user selects.

### basic-workflow as assessment
An assessment workflow project is generated from the Github Basic Workflow or the Gitlab Basic Workflow templates. This is a simple workflow that evaluates the user's input as string by comparing it to the word `default` to print out the default workflow options or an empty workflow options list.
Unlike the infrastructure workflow, the output schema of the assessment workflow is crucial to get the workflow options rendered in the UI. In this basic assessment, there is no data output schema validation but the generated workflow provides a sample object for reference which is made of six mandatory fields (currentVersion, upgradeOptions, migrateOptions, newOptions, continuationOptions, otherOptions). See below an example:

```
"workflowOptions": {
    "currentVersion": {
        "id": "DefaultInfrastructureWorkflowTest",
        "name": "Default infrastructure workflow test"
    },
    "upgradeOptions": [],
    "migrationOptions": [
        {
            "id": "MigrationInfrastructureWorkflowTest",
            "name": "Migration infrastructure workflow test"
        }
    ],
    "newOptions": [],
    "continuationOptions": [],
    "otherOptions": []
}
```

### basic-workflow as infrastructure
An infrastructure workflow project is generated from the Github Basic Workflow or the Gitlab Basic Workflow templates. This template prints out the workflow's data input.

## Prerequisites

- An OCP/k8s cluster on which to run RHDH. 
- An account on [Quay.io](https://quay.io/repository/) 
- Setting up the Gitlab prerequisites according to the following:

### Prerequisite - Gitlab configuration

To create the software project using Gitlab, some configuration is needed. 

1. Have a working Gitlab instance, with a Gitlab Group. 
2. Create a PAT (Personal Acces Token) to make API calls to that group. Optional: have a dedicated gitlab user to be the owner of that group, and issue the PAT under their user.
3. Configure a [Gitlab runner](https://docs.gitlab.com/runner/) associated to that instance / group. These runners will create VMs to run Gitlab CI actions needed for this template. The docker runner is the preffered one.
4. Add a dynamic plugin to handle the Gitlab actions on RHDH.  
To add this plugin, add a new package to the dynamic plugin CR in the rhdh-operator namespace on the cluster
```
- disabled: false
  package: ./dynamic-plugins/dist/backstage-plugin-scaffolder-backend-module-github-dynamic
```
5. Add the following spec to the backstage CR:
```
extraEnvs:
    envs:
      - name: NODE_TLS_REJECT_UNAUTHORIZED
        value: "0"
```
6. Reload the operators and validate that all new specs are patched. 

## Steps

To run the template, the user will need to provide input parameters for configuring the serverless workflow.

### Page 1: Workflow Software Project Configuration

On this page the user will provide input for the following parameters:

- Group Name: Gitlab Group Name
- Project Name: Gitlab Project name
- Description: Description added to the README file
- Workflow ID: This is a unique ID for the serverless workflow in SonataFlow. 
- Owner: A drop down menu will reveal potential owners for this workflow
- System: An entity from the catalog. 

## Page 2: Workflow Type

On this page, the user will choose a the workflow type (infrastructure / assessment). 
Upon choosing 'Assesment', the user will be prompted to enter another parameter: 
- Infrastructure Workflow ID: Workflow ID, the unique identifier of the infrastructure worklow available in the environment 

### Page 3: Build Enviroment

On this page, the user will choose a CI/CD method for building and monitoring their serverless worklow application.
Upon Choosing 'Tekton with ArgoCD', the user will be prompted to enter some following input parameters.

#### Repository Configuration

- Workflow Namespace: Deployment namespace for workflow applications
- GitOps Namespace: Deployment namespace for ArgoCD and Tekton resources
- Gitlab Instance Name: The name or host of your gitlab instance

#### PostreSQL Configuration:

- PostgreSQL Secret Name: Name of the secret in which the PostgreSQL secrets are stored. Shall be in the same namespace as the workflow
- PostgreSQL User key from secret: The key name in which the PostgreSQL *user* is stored
- PostgreSQL Password key from secret: The key name in which the PostgreSQL *password* is stored
- PostgreSQL K8s Service Name: Name of the service running the PostgreSQL instance
- PostgreSQL K8s Service Namespace: Namespace of the service running the PostgreSQL instance
- PostgreSQL Port: Port on which the PostgreSQL instance is running

#### Quay Configuration: 

- Quay Organization Name: The Quay Organization Name of the published workflow
- Quay Repository Name: A name for the Quay Repository of the published workflow, either existing or to be created

The user can choose an existing Quay repository for pushing the built images, or creating a new one.
To create a new Quay repository, some additional configuration needs to be made: 

1. The [following dynamic plugin](https://www.npmjs.com/package/@janus-idp/backstage-scaffolder-backend-module-quay) needs to be added to RHDH. 
To add this plugin, add a new package to the dynamic plugin CR in the rhdh-operator namespace on the cluster
```
- disabled: false
  package: ./dynamic-plugins/dist/backstage-plugin-scaffolder-backend-module-quay
```
2. Create or locate an Organization on Quay
3. Create an OAuth Application in the organization
4. Configure 'Create Repositories Access' and grant the OAuth app permission to create new repositories: ![image without spacing](assets/QuayOAuth.png)
5. Generate an Access Token to use

Upon choosing to create a new one, they will be prompted for additional input parameters

- Quay AuthToken : Quay organization bearer token used for authorization: 
- Quay Repository Visiblity: Visibility setting for the created repository, either public or private
- Quay Repository Kind: The created Quay repository kind, either image or application
- Quay Repository Description: The Quay repository description
- Quay Base URL: URL of your Quay instance (e.g https://quay.io)
- Quay Namespace: The Quay namespace of the published workflow. This should be the same as the Quay repository name.


### Page 4: Review

The user can review their input parameters and click "Create" to run the template. After completed, links to the created source code repository and GitOps repository will be provided.

The user can follow the instructions on the GitOps repository to activate the ArgoCD application and deploy the created workflow on RHDH.