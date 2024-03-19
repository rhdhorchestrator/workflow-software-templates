# kustomize deployment of ${{ values.workflowId }}
## Configure the deployment
Add the deployment variables to [config.properties](./base/config.properties) and [secret.properties](./base/secret.properties) to
configure the deployment environment.

## Install
Install the deployment in the default namespace:
```bash
kustomize build base | oc apply -f -
```

## Uninstall
Remove the deployment resources:
```bash
kustomize build base | oc delete -f -
```
