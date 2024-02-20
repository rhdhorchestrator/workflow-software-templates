# documentation-templates

The templates available in [scaffolder-templates](https://github.com/parodos-dev/workflow-software-templates/tree/main/scaffolder-templates) are integrated in the Backstage Orchestrator plugin under `locations` in the following deployment manifest [values](https://github.com/parodos-dev/orchestrator-helm-chart/blob/main/charts/orchestrator/templates/rhdh-operator.yaml#L186).

## basic-workflow
This template allows the user to generate a basic workflow project from the user's inputs collected.
Based on the workflow type (assessment, infrastructure) the user selects, the corresponding workflow template is generated.

### basic-workflow as assessment
The assessment workflow project generated from the basic-workflow template is a simple workflow that evaluates the user's input as string by comparing it to the word `default` to print out the default workflow options or an empty workflow options list. Unlike infrastructure workflow, the output schema of the assessment workflow is crucial to get the workflow options rendered in the UI. In this basic assessment, there is no data output schema validation but the generated workflow provides a sample object for reference which is made of six mandatory fields (currentVersion, upgradeOptions, migrateOptions, newOptions, continuationOptions, otherOptions). See below an example:

```
"workflowOptions": {
    "currentVersion": {
        "id": "DefaultInfrastructureWorkflowTest",
        "name": "Default infrastructure workflow test"
    },
    "upgradeOptions": [],
    "migrationOptions": [
        {
            "id": "MigrationInfrastructure WorkflowTest",
            "name": "Migration infrastructure workflow test"
        }
    ],
    "newOptions": [],
    "continuationOptions": [],
    "otherOptions": []
}
```

### basic-workflow as infrastructure
The infrastructure workflow project generated from the basic-workflow template is a simple workflow that prints out the workflow's data input.

## complex-assessment-workflow
This template allows the user to generate a complex assessment workflow project from the user's inputs collected. It includes:
1. **custom java code** to perform complex operations on the user's inputs. In this case, it evaluates the user's input as string by comparing it to the word `default` to print out the default workflow options or an empty workflow options list from the java code.
2. **pre-check subflow** to validate whether the workflows in the returned assessment workflow options exist in the workflows service. If there are non-existed workflows in the options, then it removes them from the options and outputs the remaining valid ones to the user.
3. **data output schema validation** to ensure that the returned assessment workflow options list aligns with the expected schema to render options correctly in the UI.
4. **README file** with the prerequisites and instructions to run and test the generated assessment workflow.
