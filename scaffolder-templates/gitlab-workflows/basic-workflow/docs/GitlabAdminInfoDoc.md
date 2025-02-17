# Environment setup for Admins

## Gitlab configuration

To create the software project using Gitlab, some configuration is needed.

1. Have a working Gitlab instance, with a Gitlab Group. 
2. Create a PAT (Personal Acces Token) to make API calls to that group. Optional: have a dedicated gitlab user to be the owner of that group, and issue the PAT under their user.
3. Configure a [Gitlab runner](https://docs.gitlab.com/runner/) associated to that instance / group. These runners will create VMs to run Gitlab CI actions needed for this template. The docker runner is the preffered one.
4. Add a dynamic plugin to handle the Gitlab actions on RHDH.  
To add this plugin, add a new package to the dynamic plugin CR in the rhdh-operator namespace on the cluster
```
- disabled: false
  package: ./dynamic-plugins/dist/backstage-plugin-scaffolder-backend-module-gitlab-dynamic
```
5. Add the following spec to the backstage CR:
```
extraEnvs:
    envs:
      - name: NODE_TLS_REJECT_UNAUTHORIZED
        value: "0"
```

This will ensure there won't be a "self-signed certificate in certificate chain" error while publishing the workflow. 

6. Reload the operators and validate that all new specs are patched. 

## Prerequistes for creating a new Quay repository 

To enable creating a new Quay repository as part of the workflow, some additional configuration needs to be set: 

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

The generated token can be used as the input value for QuayAuthToken input parameter in the software template.