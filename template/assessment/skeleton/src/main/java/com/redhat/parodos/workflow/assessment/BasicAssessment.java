package com.redhat.parodos.workflow.assessment;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BasicAssessment {
    public WorkflowOptions execute(String userInput) {
        WorkflowOptions workflowOptions = new WorkflowOptions();
        if (userInput.toLowerCase().contains("default")) { // basic check on user input to mimic an assessment
            workflowOptions.setCurrentVersion(new WorkflowOption("${{ values.workflowId }}DefaultInfrastructureWorkflow", "${{ values.description }} default infrastructure workflow"));
            workflowOptions.setUpgradeOptions(new ArrayList<>());
            workflowOptions.setMigrationOptions(new ArrayList<>());
            workflowOptions.setNewOptions(new ArrayList<>());
            workflowOptions.setContinuationOptions(new ArrayList<>());
            workflowOptions.setOtherOptions(new ArrayList<>());
            return workflowOptions;
        }
        return workflowOptions;
    }
}
