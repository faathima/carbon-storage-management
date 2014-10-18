package org.wso2.carbon.rssmanager.core.dto.restricted;

import org.wso2.carbon.rssmanager.core.jpa.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;


@Entity
@Table(name="RM_WORKFLOW")
public class Workflow extends AbstractEntity<Integer, Workflow> {

    @Id
    @TableGenerator(name="WORKFLOW_TABLE_GEN", table="SERVER_INSTANCE_SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
            valueColumnName="SEQ_COUNT", pkColumnValue="EMP_SEQ")
    @Column(name="WF_ID", columnDefinition="INTEGER")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="WORKFLOW_TABLE_GEN")
    private Integer id;

/*
    @Column(name= "WF_CREATED_TIME")
    private long createdTime;

    @Column(name="WF_UPDATED_TIME")
    private long updatedTime;*/

    @Column(name ="WF_STATUS")
    private String status;

    //  @Column(name="CALLBACKURL")
    //   privat
  //  private String workflowExternalReference;

    @Column(name = "TENANT_ID")
    private Integer tenantId;



    @Transient
    private String databaseName;

  //  @Transient
   // private String rssInstance;

    @Transient
    private String type;

//    @Transient
  //  private String rssInstanceName;

    public Workflow(){}

    //public Workflow(){}

    /*public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

   /* public String getCallbackURL() {
        return callbackURL;
    }*/

    /*public void setCallbackURL(String callbackURL) {
        this.callbackURL = callbackURL;
    }*/

  /*  public String getWorkflowExternalReference() {
        return workflowExternalReference;
    }

    public void setWorkflowExternalReference(String workflowExternalReference) {
        this.workflowExternalReference = workflowExternalReference;
    }
*/
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }


    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /*public String getRssInstance() {
        return rssInstance;
    }

    public void setRssInstance(String rssInstance) {
        this.rssInstance = rssInstance;
    }
*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

 /*   public String getRssInstanceName() {
        return rssInstanceName;
    }

    public void setRssInstanceName(String rssInstanceName) {
        this.rssInstanceName = rssInstanceName;
    }*/
}
