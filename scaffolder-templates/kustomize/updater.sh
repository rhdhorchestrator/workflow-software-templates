#!/bin/bash

WORKFLOW_ID=$1
IMAGE_TAG=$2
folder="kustomize/base"

files=$(find "$folder" -type f -name "0*.yaml" -print0 | sort -z | xargs -0 -n1 basename)
yaml_entries=""
for file in $files; do
    yaml_entries+="  - $file\n"
done

# Note: replace <sed -i ...>  with <sed -i '' ...> on MacOS
sed "s|__RESOURCES__|$yaml_entries|" kustomize/base/kustomization-template.yaml > kustomize/base/kustomization.yaml
sed -i "s|__IMAGE_TAG__|$IMAGE_TAG|" kustomize/base/kustomization.yaml

cat kustomize/base/kustomization.yaml
