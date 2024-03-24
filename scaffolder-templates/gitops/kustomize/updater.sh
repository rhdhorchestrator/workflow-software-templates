#!/bin/bash

WORKFLOW_ID=$1
IMAGE_TAG=$2
folder="base"

files=$(find "$folder" -type f -name "0*.yaml" -print0 | sort -z | xargs -0 -n1 basename)
yaml_entries=""
for file in $files; do
    yaml_entries+="  - $file\n"
done

# Note: replace <sed -i ...>  with <sed -i '' ...> on MacOS
sed "s|__RESOURCES__|$yaml_entries|" base/kustomization-template.yaml > base/kustomization.yaml
sed -i "s|__IMAGE_TAG__|$IMAGE_TAG|" base/kustomization.yaml

cat base/kustomization.yaml
