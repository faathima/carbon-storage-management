package org.wso2.carbon.rssmanager.core.dto.restricted;

import org.wso2.carbon.rssmanager.core.environment.Environment;
import org.wso2.carbon.rssmanager.core.jpa.persistence.entity.AbstractEntity;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;
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
    @TableGenerator(name = "WORKFLOW_TABLE_GEN", table = "SERVER_INSTANCE_SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "EMP_SEQ")
    @Column(name = "ID", columnDefinition = "INTEGER")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "WORKFLOW_TABLE_GEN")
    private Integer id;


    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "DATABASE_ID", nullable = false)
    private Database databaseId;
/*
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Timestamp createdTime;

    @Column(name = "UPDATED_TIME")
    private long updatedTime;*/

    @Column(name = "WF_STATUS")
    private String status;

    @Column(name = "DESCRIBTION")
    private String describtion;

    @Column(name = "TENANT_ID")
    private Integer tenantId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ENVIRONMENT_ID", nullable = false)
    private Environment environment;

    @Column(name = "WF_REFERENCE")
    private String wfRefference;

    @Transient
    private String rssInstance;

    @Transient
    private String type;

    @Transient
    private String rssInstanceName;

    @Transient
    private String dbName;

    public Workflow() {
    }

   /* public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getRssInstance() {
        return rssInstance;
    }

    public void setRssInstance(String rssInstance) {
        this.rssInstance = rssInstance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRssInstanceName() {
        return rssInstanceName;
    }

    public void setRssInstanceName(String rssInstanceName) {
        this.rssInstanceName = rssInstanceName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public String getWfRefference() {
        return wfRefference;
    }

    public void setWfRefference(String wfRefference) {
        this.wfRefference = wfRefference;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Database getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Database databaseId) {
        this.databaseId = databaseId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
