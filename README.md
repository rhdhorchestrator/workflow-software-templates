# workflow-software-templates

This repository is a collection of software and documentation templates for the Backstage Orchestrator plugin. The templates are organized into two folders: `scaffolder-templates` for Software Templates and `documentation-templates` for general documentation templates. Contributions are welcome for new and existing templates!

## scaffolder-templates

[Backstage Software Templates](https://backstage.io/docs/features/software-templates/) are used to create new software components through Backstage. This concept is leveraged in the Orchestrator plugin to create templates that are intended as a starting point to build on for different use cases such as basic assessment or infrastructure workflows or assessment workflows using custom java code.

## documentation-templates

The documentation templates provide recommended structure and integration documentation with the Orchestrator deployment.

## pre-requisites
### GitHub secrets
In case of `Tekton` CI pipeline, the secrets `K8S_CLUSTER_URL` and `K8S_SECRET` are used by the GitHub action that deploys the Tekton
resources. Please provide organization-level configuration for these secrets and ensure that they can be managed by the newly created repositories according to the visibility options (currently set to `public`).

The value of the `K8S_SECRET` secret must comply with the specification provided in [Service account approach](https://github.com/Azure/k8s-set-context/tree/releases/v1?tab=readme-ov-file#service-account-approach) for the `azure/k8s-set-context` action.

You can follow these steps to generate the secret:
```
[update current `oc project` to an existing namespace like `orchestrator`]
oc create sa orchestrator
oc adm policy add-cluster-role-to-user cluster-admin -z orchestrator
oc get $(oc get secret -o name | grep orchestrator-token) -o yaml > token.yaml
```
Finally, the `K8S_SECRET` must be set to the full content of `token.yaml`.

### Tekton resources
The Tekton trigger requires to install the pipeline resources that are referenced by the `TriggerTemplate`.

Follow this section to install the required [Prerequisites](https://github.com/parodos-dev/workflows-tekton-pipeline?tab=readme-ov-file#prerequisites).

The next step is to create the [docker credentials](https://github.com/parodos-dev/workflows-tekton-pipeline?tab=readme-ov-file#installing-docker-credentials)
to push the image artifacts on Quay and [Define the SSH credentials](https://github.com/parodos-dev/workflows-tekton-pipeline?tab=readme-ov-file#define-the-ssh-credentials)
to push the deployment configuration to the source repository.

Once this is done, you can create the Tekton pipeline resources by applying the associated manifests: 
```bash
git clone https://github.com/parodos-dev/workflows-tekton-pipeline.git
cd workflows-tekton-pipeline
kustomize build kustomize/base | oc apply -f -
```

**Note**: do not apply the manifests in the `kustomize/run` folder, as they refer to the same trigger resources that are automatically added by this software template.
