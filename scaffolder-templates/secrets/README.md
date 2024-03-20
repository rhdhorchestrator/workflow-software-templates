## Install required resources
To enabled the GitOps automation you must create the required resources in the cluster.

Please run the following command:
```
git clone https://github.com/${{ parameters.orgName }}/${{ parameters.repoName }}-gitops.git
cd ${{ parameters.repoName }}-gitops/secrets
kubectl apply -f repository.yaml
```
