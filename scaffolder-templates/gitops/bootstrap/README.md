## Install Bootstrap Application for GitOps Automation

To enabled the GitOps automation you must create the required resources in the target cluster.

Before applying the manifests, there is a need to replace the `__REPLACE_SSH_PRIVATE_KEY__` string in ${{values.workflowId}}-argocd-repo.yaml with the SSH key to enable access to the Git repository.

Either manually edit the file or login to the target cluster and run the following command:

```
# Change the Git host to the one you are using, for example, github.com, gitlab.cee.redhat.com, gitlab.gitlab, etc.

git clone https://github.com/${{ values.orgName }}/${{ values.repoName }}.git
cd ${{ values.repoName }}/bootstrap

# Optionally, if edited the file manually
SSH_PRIVATE_KEY=$(oc get secrets -n orchestrator-gitops git-ssh-credentials -o jsonpath='{.data.id_rsa}') 
# For a gitlab client, use gitlab-ssh-credentials instead. 
sed -i "s/__REPLACE_SSH_PRIVATE_KEY__/$SSH_PRIVATE_KEY/" ${{values.workflowId}}-argocd-repo.yaml

kubectl apply -f .
```

**Note:** If you're not logged into the repository, you need to provide a personal access token (PAT) as part of the clone URL.

```
git clone https://<PAT>@github.com/${{ values.orgName }}/${{ values.repoName }}.git
```

Replace `<PAT>` with your personal access token. Ensure the token has the necessary permissions to access the repository.

If you encounter issues with the clone, refer to [GitHub's troubleshooting documentation](https://docs.github.com/en/repositories/creating-and-managing-repositories/troubleshooting-cloning-errors) for assistance.
