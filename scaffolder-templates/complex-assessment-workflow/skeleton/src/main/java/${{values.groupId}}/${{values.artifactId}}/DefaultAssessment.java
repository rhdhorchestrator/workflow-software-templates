package ${{ values.groupId }}.${{ values.artifactId }};

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class DefaultAssessment {
    public WorkflowOptions execute(String userInput) {
        WorkflowOptions workflowOptions = new WorkflowOptions();
        if (userInput.toLowerCase().contains("default")) { // basic check on user input to mimic an assessment
            workflowOptions.setCurrentVersion(new WorkflowOption("${{ values.defaultInfrastructureWorkflowId }}", "${{ values.defaultInfrastructureWorkflowName }}"));
            return workflowOptions;
        }
        return workflowOptions;
    }
}
