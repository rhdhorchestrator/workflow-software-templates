> **🚨 Deprecation Notice: 🚨**  
> From Orchestrator release version 1.7, Workflow Types will be retired. All workflows will act as infrastructure workflows, and no workflow will act as an assesment workflow. <br>
> Any reference to assessment workflows in this document will be obsolete.

# Creating an Advanced Serverless Workflow via a Software Template with Gitlab

## Overview

This software template allows the user to generate an advanced assessment workflow project from the user's input.

## Prerequisites

- An OCP/k8s cluster and an RHDH instance. 
- An account on [Quay.io](https://quay.io/repository/) 
- Setting up the Gitlab prerequisites as explained in the admin [environment setup documentation](GitlabAdminInfoDoc.md).  

## Steps

To run the template, the user will need to provide input parameters for configuring the serverless workflow.  

### Page 1: Workflow Software Project Configuration

On this page the user will provide input for the following parameters:

- Group Name: Gitlab Group Name
- Project Name: Gitlab Project name
- Description: Description added to the README file
- Workflow ID: A unique ID for the serverless workflow in SonataFlow. 
- Infrastructure Workflow ID: Workflow ID, the unique identifier of the infrastructure worklow available in the environment
- Owner: A drop down menu will reveal potential owners for this workflow
- System: An entity from the catalog.

### Page 2: Build Enviroment

On this page, the user will choose a CI/CD method for building and monitoring their serverless worklow application.

- Upon Choosing 'None', no CI method will be used; the template will create the software project repository and will not create a GitOps repository. 
- Upon Choosing 'Tekton with ArgoCD', the user will be prompted to enter some following input parameters to configure the GitOps repository:

#### Repository Configuration: 

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
To create a new Quay repository, some additional configuration needs to be made. The system administrator must follow the pre-requisite step in the [admin environment setup documentation](GitlabAdminInfoDoc.md).  

Upon choosing to create a new Quay repository, the user will be prompted for additional input parameters:

- Quay AuthToken : Quay organization bearer token used for authorization

To generate the Auth token, please follow the pre-requisite step in the [admin environment setup documentation](GitlabAdminInfoDoc.md).

- Quay Repository Visiblity: Visibility setting for the created repository, either public or private
- Quay Repository Kind: The created Quay repository kind, either image or application
- Quay Repository Description: The Quay repository description
- Quay Base URL: URL of your Quay instance (e.g https://quay.io)
- Quay Namespace: The Quay namespace of the published workflow. This should be the same as the Quay repository name.


### Page 3: Build Enviroment

The user can review their input parameters and click "Create" to run the template. After completed, a link to the created source code repository will be provided. A link to the GitOps repository will be provided only if the user selected a CI method. 

The user can follow the instructions on the GitOps repository to activate the ArgoCD application and deploy the created workflow on RHDH.