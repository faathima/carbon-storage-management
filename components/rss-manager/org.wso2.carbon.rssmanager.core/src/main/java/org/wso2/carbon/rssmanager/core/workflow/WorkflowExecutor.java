package org.wso2.carbon.rssmanager.core.workflow;

import org.wso2.carbon.registry.core.utils.UUIDGenerator;
import org.wso2.carbon.rssmanager.core.dao.impl.WorkflowDAOImpl;
import org.wso2.carbon.rssmanager.core.dao.util.EntityManager;
import org.wso2.carbon.rssmanager.core.dto.WorkflowInfo;
import org.wso2.carbon.rssmanager.core.dto.restricted.Workflow;

import java.io.Serializable;
import java.util.List;

/**
 * Created by msffayaza on 10/15/14.
 */
public abstract class WorkflowExecutor implements Serializable {
    protected String callbackURL;

    public abstract String getWorkflowType();

    public void execute(WorkflowInfo workflowInfo) throws WorkflowException{

    }


//    public void complete(WorkflowDTO workflowDTO) throws WorkflowException{
//        ApiMgtDAO apiMgtDAO = new ApiMgtDAO();
//        try {
//            apiMgtDAO.updateWorkflowStatus(workflowDTO);
//        } catch (APIManagementException e) {
//            throw new WorkflowException("Error while updating workflow", e);
//        }
//    }


   // public abstract List<WorkflowDTO> getWorkflowDetails(String workflowStatus) throws WorkflowException;
//
    public String generateUUID(){
        String UUID = UUIDGenerator.generateUUID();
        return UUID;
    }



    public String getCallbackURL() {
        return callbackURL;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }
}

