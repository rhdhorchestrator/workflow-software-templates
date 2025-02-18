---
id: github-convert-workflow-to-template
title: Convert serverless workflow to a template
description: Create a software template out of an existing serverless workflow.
---

# Creating a Software Template for Running a Serverless Workflow

## Overview

This template allows the user to generate another software template from a serverless workflow deployed for the Orchestrator.

## Prerequisites

- An OCP/k8s cluster and an RHDH instance. 
- A GitHub account
- An account on Quay.io 

## Steps

To run the template, the user will need to provide the ID of a serverless workflow and GitHub repository for storing the generated output (the software template).

### Page 1: Workflow Identifier To Generate The Template For


On this page the user will provide input for the following parameters:

- Workflow ID: A unique ID for the serverless workflow in SonataFlow. The allowed pattern for the string will be enforced. 

### Page 2: Repository Location
On this page the user will provide input for the following parameters:

- Host: github.com
- Owner: the organization on GitHub owning the repository
- Repository: the repository name
  
Choose one from
- Submit a pull request to the same repository
  - create a branch in the existing repository and file a new pull-request out of it
  - Additional input needs to be provided:
    - Branch Name: branch to be created
    - Target Branch Name: to pull the new changes into (i.e. the `main`)
- Create a new repository within the specified organization
  - to create a brand new repository under the `owner` organization

### Page 4: Review

The user can review their input parameters and click "Create" to run the template. After completed, a link to the created source code repository will be provided.
