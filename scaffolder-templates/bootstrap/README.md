## Install bootstrap application
To enabled the GitOps automation you must create the required resources in the cluster.

Login to the target cluster and run the following command:
```
git clone https://github.com/${{ parameters.orgName }}/${{ parameters.repoName }}-gitops.git
cd ${{ parameters.repoName }}-gitops/bootstrap
kubectl apply -f .
```
