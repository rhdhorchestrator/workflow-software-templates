## Install Bootstrap Application for GitOps Automation

To enabled the GitOps automation you must create the required resources in the target cluster.

Before applying the manifests, there is a need to replace the `__REPLACE_SSH_PRIVATE_KEY__` string in ${{values.workflowId}}-argocd-repo.yaml with the SSH key to enable access to the Git repository.

Either manually edit the file or login to the target cluster and run the following command:
```
git clone https://github.com/${{ values.orgName }}/${{ values.repoName }}.git
cd ${{ values.repoName }}/bootstrap

# Optionally, if edited the file manually
SSH_PRIVATE_KEY=$(oc get secrets -n orchestrator-gitops git-ssh-credentials -o jsonpath='{.data.id_rsa}' | base64 -d)
sed -i "s/__REPLACE_SSH_PRIVATE_KEY__/$SSH_PRIVATE_KEY/" ${{values.workflowId}}-argocd-repo.yaml

kubectl apply -f .
```