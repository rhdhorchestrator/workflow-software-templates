# Environment setup for Admins

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
