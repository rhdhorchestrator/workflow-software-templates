package ${{ values.groupId }}.${{ values.artifactId }};

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class SampleAssessment {
    public WorkflowOptions execute(String sampleText) {
        WorkflowOptions workflowOptions = new WorkflowOptions();
        if (null != sampleText && !sampleText.isEmpty()) {
            workflowOptions.setCurrentVersion(new WorkflowOption("${{ values.workflowId }}", "${{ values.workflowId }}"));
            return workflowOptions;
        }
        return workflowOptions;
    }
}
