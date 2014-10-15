package org.wso2.carbon.rssmanager.core.workflow;

import org.wso2.carbon.registry.core.utils.UUIDGenerator;
import org.wso2.carbon.rssmanager.core.dto.WorkflowInfo;

import java.io.Serializable;

/**
 * Created by msffayaza on 10/15/14.
 */
public abstract class WorkflowExecutor implements Serializable {
    protected String callbackURL;
    private String serviceEndpoint;

   /* private String username;

    private String password;

    private String contentType;*/



    public void execute(WorkflowInfo workflowInfo) throws Exception{
        // Workflow workflow= new Workflow();
        //EntityManager entityManager;
        //WorkflowDAOImpl workflowDAO = new WorkflowDAOImpl()
        try {


        } catch (Exception e ) {
            throw new Exception();
        }
    }


    public void complete() throws Exception{

        try {

        } catch (Exception e) {
            throw new Exception();
        }
    }



    /**
     * Method generates and returns UUID
     * @return UUID
     */
    public String generateUUID(){
        String UUID = UUIDGenerator.generateUUID();
        return UUID;
    }


    public String getCallbackURL() {
        return callbackURL;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }


}
