package org.wso2.carbon.rssmanager.core.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.rssmanager.core.dto.restricted.Workflow;

import java.util.List;

/**
 * Created by msffayaza on 10/18/14.
 */
public class DatabseCreationSimpleWorkflowExecutor  extends WorkflowExecutor {

    private static final Log log =
            LogFactory.getLog(DatabaseCreationWSWorkflowExecutor.class);

    @Override
    public String getWorkflowType() {
        return WorkflowConstants.WF_TYPE_SS_DATABASE_CREATION;
    }



    public void execute(Workflow workflow) throws WorkflowException {
        if (log.isDebugEnabled()) {
            log.info("Executing Application creation Workflow..");
        }
//        workflow.setStatus(WorkflowStatus.APPROVED);
//        complete(workFlowDTO);

    }

//
//    public void complete(WorkflowDTO workFlowDTO) throws WorkflowException {
//        if (log.isDebugEnabled()) {
//            log.info("Complete  Application creation Workflow..");
//        }
//
//        String status = null;
//        if ("CREATED".equals(workFlowDTO.getStatus().toString())) {
//            status = APIConstants.ApplicationStatus.APPLICATION_CREATED;
//        } else if ("REJECTED".equals(workFlowDTO.getStatus().toString())) {
//            status = APIConstants.ApplicationStatus.APPLICATION_REJECTED;
//        } else if ("APPROVED".equals(workFlowDTO.getStatus().toString())) {
//            status = APIConstants.ApplicationStatus.APPLICATION_APPROVED;
//        }
//
//        ApiMgtDAO dao = new ApiMgtDAO();
//
//        try {
//            dao.updateApplicationStatus(Integer.parseInt(workFlowDTO.getWorkflowReference()), status);
//        } catch (APIManagementException e) {
//            String msg = "Error occured when updating the status of the Application creation process";
//            log.error(msg, e);
//            throw new WorkflowException(msg, e);
//        }
//    }
//
//    @Override
//    public List<WorkflowDTO> getWorkflowDetails(String workflowStatus) throws WorkflowException {
//        return null;
//    }
}



