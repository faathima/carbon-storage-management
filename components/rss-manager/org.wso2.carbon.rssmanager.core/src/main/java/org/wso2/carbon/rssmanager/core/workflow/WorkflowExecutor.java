package org.wso2.carbon.rssmanager.core.workflow;

import org.wso2.carbon.registry.core.utils.UUIDGenerator;
import org.wso2.carbon.rssmanager.core.RSSTransactionManager;
import org.wso2.carbon.rssmanager.core.config.RSSManagementRepository;
import org.wso2.carbon.rssmanager.core.dao.DatabaseDAO;
import org.wso2.carbon.rssmanager.core.dao.RSSDAO;
import org.wso2.carbon.rssmanager.core.dao.RSSDAOFactory;
import org.wso2.carbon.rssmanager.core.dao.exception.RSSDAOException;
import org.wso2.carbon.rssmanager.core.dao.impl.DatabaseDAOImpl;
import org.wso2.carbon.rssmanager.core.dao.impl.DatabaseUserDAOImpl;
import org.wso2.carbon.rssmanager.core.dao.impl.WorkflowDAOImpl;
import org.wso2.carbon.rssmanager.core.dao.util.EntityManager;
import org.wso2.carbon.rssmanager.core.dto.restricted.Database;
import org.wso2.carbon.rssmanager.core.dto.restricted.RSSInstance;
import org.wso2.carbon.rssmanager.core.dto.restricted.Workflow;
import org.wso2.carbon.rssmanager.core.exception.RSSManagerException;
import org.wso2.carbon.rssmanager.core.internal.RSSManagerDataHolder;
import org.wso2.carbon.rssmanager.core.jpa.persistence.internal.JPAManagerUtil;
import org.wso2.carbon.rssmanager.core.jpa.persistence.internal.PersistenceManager;
import org.wso2.carbon.rssmanager.core.manager.AbstractRSSManager;
import org.wso2.carbon.rssmanager.core.manager.RSSManager;
import org.wso2.carbon.rssmanager.core.manager.adaptor.EnvironmentAdaptor;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by msffayaza on 10/15/14.
 */
public abstract class WorkflowExecutor implements Serializable {
    protected String callbackURL;
    public abstract String getWorkflowType();
    RSSManagementRepository repositoryConfig;

    /* Initializing RSS transaction manager wrapper */
    RSSTransactionManager rssTxManager =
            new RSSTransactionManager(RSSManagerDataHolder.getInstance().
                    getTransactionManager());

    /* Initializing entity manager used in RSS DAO */
    DataSource dataSource =
            RSSDAOFactory.resolveDataSource(repositoryConfig.getDataSourceConfig());

    Set<String> unitNames = PersistenceManager.getPersistentUnitNames();
    String unitName = unitNames.iterator().next();

    EntityManager entityManager = new EntityManager(rssTxManager, dataSource,
            new JPAManagerUtil(PersistenceManager.getEMF(unitName)));

    DatabaseDAOImpl dao = new DatabaseDAOImpl(entityManager);

    public void execute(Workflow workflow, Database database)  throws WorkflowException{

    }

    public void complete(Workflow workflow, Database database) throws WorkflowException{
       // DatabaseDAO dao = this.getRSSDAO().getDatabaseDAO();

        if(WorkflowConstants.WORKFLOW_APPROVED.equals(workflow.getStatus())){
            database=workflow.getDatabaseId();
            database.setStatus("APPROVED");
//            String rssInstance=database.getRssInstanceName();
//            RSSInstance rssInstance1=database.getRssInstance();
//            String instanceType=database.getType();
//            String databaseName=database.getName();
//             String environmentName=database.getEntityName();
//            RSSInstance rssInstance2=database.getRssInstance();
//            try {
//                environmentAdaptor.removeDatabase(environmentName,rssInstance,databaseName,instanceType);
//            } catch (RSSManagerException e) {
//                e.printStackTrace();
//            }
//
//            //  abstractRSSManager.removeDatabase(AtomicBoolean isInTx,rssInstance,databaseName,instanceType,rssInstance2);
//            System.out.println(database.getName());
//            System.out.println(database.getId());
//            System.out.println(database.getRssInstance());
//            System.out.println(database.getRssInstanceName());
//            System.out.println(database.getStatus());
//            System.out.println(database.getTenantId());
//            System.out.println(database.getUrl());

//            try {
//                environmentAdaptor.removeDatabase(environmentName,rssInstance,databaseName,instanceType);
//            } catch (RSSManagerException e) {
//                e.printStackTrace();
//            }


//            try {
//                this.getRSSDAO().getDatabaseDAO().updateDatabse(database);
//            } catch (RSSDAOException e) {
//                e.printStackTrace();
//            }

            try {
                dao.updateDatabse(database);
            } catch (RSSDAOException e) {
                e.printStackTrace();
            }


        }

        else if(WorkflowConstants.WORKFLOW_REJECT.equals(workflow.getStatus())){
            database=workflow.getDatabaseId();
//            abstractRSSManager.removeDatabase(AtomicBoolean isInTx, String rssInstanceName, String databaseName,
//                    RSSInstance rssInstance, String instanceType);
//            try {
//                this.getRSSDAO().getDatabaseDAO().removeDatabase(database);
//            } catch (RSSDAOException e) {
//                e.printStackTrace();
//            }

        }

    }

    public abstract List<Workflow> getWorkflowDetails(String workflowStatus) throws WorkflowException;

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

