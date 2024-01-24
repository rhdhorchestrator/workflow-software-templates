# Assessment workflow
The assessment workflow is used to perform some checks on user's input(s) and to return a list of suitable infrastructure workflows aka workflow options. 
Its common use case is to assess inputs (ie: a link to their project code and/or an application identifying code), and based on a logic determined by the enterprise returns a list of infrastructure workflows.

The goal in this example is to show how an assessment operation can be implemented in order to perform some check(s) on a user's input(s) and to return suitable workflow options.

## Assessment flow
In this example, the assessment flow consists of:
- **Start**
  - get the project code repository (repositoryUrl) from the user
- **Assessment**
  - check whether the repositoryUrl is a java project or not and return workflow options
    - _For simplicity's sake, the java project check is simulated by verifying the presence of the keyword `java` in the repositoryUrl_
  - print workflow options grouped into six categories (current version, upgrade options, migrate options, new options, continuation options, other options) to the user
- **PreCheck**
  - validate whether the workflows in the returned assessment options exist
  - if there are non-existed workflows in the options, then remove them from the options and output the remaining valid ones
- **End**

**Note**:
This example assumes that the workflow options returned upon assessment are available.
The list of workflows provided into `resources/infrastructures` are purely illustrative.

## How to run

```bash
mvn clean quarkus:dev
```

Example of POST to trigger the flow:
```bash
curl -XPOST -H "Content-Type: application/json" http://localhost:8080/assessment -d '{"repositoryUrl": "_YOUR_JAVA_REPOSITORY_"}'
```

Response:
```
{
    "id": "c9a0ce80-8cd2-49d2-81e1-05606e52c9c9",
    "workflowdata": {
        "workflowOptions": {
            "currentVersion": {
                "id": "ocpOnbarding",
                "name": "Ocp Onboarding"
            },
            "upgradeOptions": [],
            "migrationOptions": [
                {
                    "id": "move2kube",
                    "name": "Move2Kube"
                }
            ],
            "newOptions": [
                {
                    "id": "vmOnboarding",
                    "name": "Vm Onboarding"
                }
            ],
            "continuationOptions": [],
            "otherOptions": [
                {
                    "id": "training",
                    "name": "Training"
                }
            ]
        }
    }
}
```