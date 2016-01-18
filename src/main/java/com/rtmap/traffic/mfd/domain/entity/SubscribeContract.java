package com.rtmap.traffic.mfd.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 订阅契约
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Entity
@Table(name = "subscribe_contract")
public class SubscribeContract {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "contract_id", columnDefinition = "INT")
    private Integer contractId;
	@Column(name = "current_airport", columnDefinition = "CHAR", length = 3)
    private String currentAirport ="PEK";
	@Column(name = "source_code")
    private String sourceCode ="PEK-WECHAT-SHAKE";
	@Column(name = "subscribe_id", columnDefinition = "INT")
    private Integer subscribeId = 1;
	@Column(name = "subscriber_id")
    private String subscriberId = "openId";
	@Column(name = "subscribe_module")
    private String subscribeModule = "FLIGHT";
	@Column(name = "subscribe_event")
    private String subscribeEvent = "DYNAMICS";
	@Column(name = "subscribe_keywords")
    private String subscribeKeywords;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;
    @Column(name = "delete_flag", columnDefinition = "CHAR", length = 1)
    private String deleteFlag = "N";
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", columnDefinition = "DATETIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "invalid_time", columnDefinition = "DATETIME")
    private Date invalidTime;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getCurrentAirport() {
        return currentAirport;
    }

    public void setCurrentAirport(String currentAirport) {
        this.currentAirport = currentAirport == null ? null : currentAirport.trim();
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode == null ? null : sourceCode.trim();
    }

    public Integer getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(Integer subscribeId) {
        this.subscribeId = subscribeId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscribeModule() {
        return subscribeModule;
    }

    public void setSubscribeModule(String subscribeModule) {
        this.subscribeModule = subscribeModule == null ? null : subscribeModule.trim();
    }

    public String getSubscribeEvent() {
        return subscribeEvent;
    }

    public void setSubscribeEvent(String subscribeEvent) {
        this.subscribeEvent = subscribeEvent == null ? null : subscribeEvent.trim();
    }

    public String getSubscribeKeywords() {
        return subscribeKeywords;
    }

    public void setSubscribeKeywords(String subscribeKeywords) {
        this.subscribeKeywords = subscribeKeywords == null ? null : subscribeKeywords.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }
}