package org.wso2.carbon.rssmanager.core.workflow;

import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.rssmanager.core.dto.restricted.Workflow;
import org.wso2.carbon.rssmanager.core.internal.RSSManagerDataHolder;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCreationWSWorkflowExecutor extends WorkflowExecutor {
    private String serviceEndpoint;

    private String username;

    private String password;

    private String contentType;

    private static final Log log = LogFactory.getLog(DatabaseCreationWSWorkflowExecutor.class);

    @Override
    public String getWorkflowType() {
        return WorkflowConstants.WF_TYPE_SS_DATABASE_CREATION;
    }

    @Override
    public void execute(Workflow workflow) throws WorkflowException {
        if (log.isDebugEnabled()) {
            log.info("Executing Application creation Workflow..");
        }
        // super.execute(workflow);
            workflow.setStatus("CREATED");
            workflow.setDescribtion("WAITING FOR PRIVILEGE USER APPROVAL");

            try {

           String contentType = "application/soap+xml; charset=UTF-8";
                ServiceClient client = new ServiceClient(RSSManagerDataHolder.getContextService().getClientConfigContext(), null);
                Options options = new Options();
                options.setAction("http://workflow.createdb.ss.carbon.wso2.org/initiate");
                options.setTo(new EndpointReference(serviceEndpoint));

                if (contentType != null) {
                    options.setProperty(Constants.Configuration.MESSAGE_TYPE, contentType);
                } else {
                    options.setProperty(Constants.Configuration.MESSAGE_TYPE,
                            HTTPConstants.MEDIA_TYPE_APPLICATION_XML);
                }

                HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();

                if (username != null && password != null) {
                    auth.setUsername(username);
                    auth.setPassword(password);
                    auth.setPreemptiveAuthentication(true);
                    List<String> authSchemes = new ArrayList<String>();
                    authSchemes.add(HttpTransportProperties.Authenticator.BASIC);
                    auth.setAuthSchemes(authSchemes);
                    options.setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE,
                            auth);
                    options.setManageSession(true);
                }
                client.setOptions(options);

                String payload =
                        "<wor:CreateDBApprovalWorkFlowProcessRequest xmlns:wor=\"http://workflow.createdb.ss.carbon.wso2.org\">\n"
                                + "        <wor:DatabaseName>$1</wor:DatabaseName>\n"
                                + "        <wor:callBackURL>$2</wor:callBackURL>\n"
                                + "        <wor:description>$3</wor:description>\n"
                                + "        <wor:workflowExternalRef>$4</wor:workflowExternalRef>\n"
                               // + "        <wor:DBSInstanceName>$5</wor:DBSInstanceName>\n"
                                + "</wor:CreateDBApprovalWorkFlowProcessRequest>";


                payload = payload.replace("$1",workflow.getDbName());
                payload = payload.replace("$2", workflow.getCallbackURL());
                payload=payload.replace("$3", workflow.getDescribtion());
                payload=payload.replace("$4", workflow.getWfRefference());
           //     payload=payload.replace("$5",workflow.getRssInstanceName());

                client.fireAndForget(AXIOMUtil.stringToOM(payload));

            } catch (AxisFault axisFault) {
                axisFault.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();

            }


        }


//    @Override
//    public void complete(WorkflowDTO workFlowDTO) throws WorkflowException {
//        workFlowDTO.setUpdatedTime(System.currentTimeMillis());
//        ApiMgtDAO dao = new ApiMgtDAO();
//        try {
//            if(dao.getApplicationById(Integer.parseInt(workFlowDTO.getWorkflowReference())) != null) {
//
//                super.complete(workFlowDTO);
//                log.info("Application Creation [Complete] Workflow Invoked. Workflow ID : " + workFlowDTO.getExternalWorkflowReference() + "Workflow State : " + workFlowDTO.getStatus());
//
//                if (WorkflowStatus.APPROVED.equals(workFlowDTO.getStatus())) {
//                    String status = null;
//                    if ("CREATED".equals(workFlowDTO.getStatus().toString())) {
//                        status = APIConstants.ApplicationStatus.APPLICATION_CREATED;
//                    } else if ("REJECTED".equals(workFlowDTO.getStatus().toString())) {
//                        status = APIConstants.ApplicationStatus.APPLICATION_REJECTED;
//                    } else if ("APPROVED".equals(workFlowDTO.getStatus().toString())) {
//                        status = APIConstants.ApplicationStatus.APPLICATION_APPROVED;
//                    }
//
//
//                    try {
//                        dao.updateApplicationStatus(Integer.parseInt(workFlowDTO.getWorkflowReference()),
//                                status);
//                    } catch (APIManagementException e) {
//                        String msg = "Error occured when updating the status of the Application creation " +
//                                "process";
//                        log.error(msg, e);
//                        throw new WorkflowException(msg, e);
//                    }
//                }
//            }else {
//                String msg = "Application does not exist";
//                throw new WorkflowException(msg);
//            }
//        } catch (APIManagementException e) {
//            String msg = "Error occured when retrieving the Application creation with workflow ID :"+workFlowDTO.getWorkflowReference();
//            log.error(msg, e);
//            throw new WorkflowException(msg, e);
//        }
//    }

//    @Override
//    public List<Workflow> getWorkflowDetails(String workflowStatus) throws WorkflowException {
//        // TODO Auto-generated method stub
//        return null;
//    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}


