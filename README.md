# workflow-software-templates

This repository is a collection of software and documentation templates for the Backstage Orchestrator plugin. The templates are organized into two folders: `scaffolder-templates` for Software Templates and `documentation-templates` for general documentation templates. Contributions are welcome for new and existing templates!

## scaffolder-templates

[Backstage Software Templates](https://backstage.io/docs/features/software-templates/) are used to create new software components through Backstage. This concept is leveraged in the Orchestrator plugin to create templates that are intended as a starting point to build on for different use cases such as basic assessment or infrastructure workflows or assessment workflows using custom java code.

## documentation-templates

The documentation templates provide recommended structure and integration documentation with the Orchestrator deployment.

## Prerequisites
### GitHub secrets
In case of `Tekton` CI pipeline, the secrets `OPENSHIFT_SERVER` and `OPENSHIFT_TOKEN` are used by the GitHub action that deploys the Tekton
resources. Please provide organization-level configuration for these secrets and ensure that they can be managed by the newly created repositories according to the visibility options (currently set to `public`).

The value of the `OPENSHIFT_TOKEN` secret must provide the permissions to create resources in multiple namesapces, so we provide a simple procedure to
bind it to the `cluster-admin` role, but we recommend to configure a new role with only the required privileges instead.

You can follow these steps to generate the token:
```
[update current `oc project` to an existing namespace like `orchestrator`]
oc create sa orchestrator
oc adm policy add-cluster-role-to-user cluster-admin -z orchestrator
oc get $(oc get secret -o name | grep orchestrator-token) -o yaml | yq '.data.token' | sed 's/"//g' | base64 -d
```
Finally, put the output of the last command in the `OPENSHIFT_TOKEN` secret.

## Generated artifacts
The execution of the software template produces the following output:
* A workflow repository with a sample Serverless Workflow
  * Including manifests to configure the Tekton trigger and ArgoCD application
  * Includes a GH action to deploy the Tekton trigger and ArgoCD application: the action is automatically executed to start the deployment by the template
  * A webHook is automatically created to activate the Tekton trigger
* A config repository with initial configuration of a kustomize project to deploy the application
  * Uses properties file to allow setting user-specific configuration for the workflow (assuming that the workflow `application.properties` are using
  env variables to specify the values)
* Both the workflow and the config repos are defined as `Component` entities in Backstage