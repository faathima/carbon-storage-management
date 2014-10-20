package org.wso2.carbon.rssmanager.core.workflow;

import org.wso2.carbon.registry.core.exceptions.RegistryException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.rssmanager.core.dto.restricted.Workflow;

import javax.cache.Cache;
import javax.cache.Caching;


/**
 * Created by msffayaza on 10/18/14.
 */
public class WorkflowExecutorFactory {

    private static final Log log = LogFactory.getLog(WorkflowExecutorFactory.class);

    private static WorkflowExecutorFactory instance;

    private WorkflowExecutorFactory() {
    }

    public static WorkflowExecutorFactory getInstance() {
        if (instance == null) {
            instance = new WorkflowExecutorFactory();
        }
        return instance;
    }


    public TenantWorkflowConfigHolder getWorkflowConfigurations() throws WorkflowException{

        String tenantDomain = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantDomain();

        int tenantId = PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();

            TenantWorkflowConfigHolder configHolder = new TenantWorkflowConfigHolder(tenantDomain,tenantId);
            try {
                configHolder.load();

                return configHolder;
            } catch (WorkflowException e) {
                handleException("Error occurred while creating workflow configurations for tenant " + tenantDomain, e);
            }

        return null;
    }

    private static void handleException(String msg) throws WorkflowException {
        log.error(msg);
        throw new WorkflowException(msg);
    }

    private static void handleException(String msg, Exception e) throws WorkflowException {
        log.error(msg, e);
        throw new WorkflowException(msg, e);
    }

    public WorkflowExecutor getWorkflowExecutor(String workflowExecutorType) throws WorkflowException {
        TenantWorkflowConfigHolder holder = null;
        try {
            holder = this.getWorkflowConfigurations();
            if (holder != null) {
                return holder.getWorkflowExecutor(workflowExecutorType);
            }
        } catch (WorkflowException e) {
            handleException("Error while creating WorkFlowDTO for " + workflowExecutorType);
        }
        return null;
    }

    /**
     * Create a DTO object related to a given workflow type.
     * @param wfType Type of the workflow.
     */
    public Workflow createWorkflowDTO(String wfType) {
        Workflow workflow = null;
        if(WorkflowConstants.WF_TYPE_SS_DATABASE_CREATION.equals(wfType)){

        }

        workflow.setType(wfType);
        return workflow;
    }
}
