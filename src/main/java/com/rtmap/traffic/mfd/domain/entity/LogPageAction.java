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
 * 页面动作日志
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Entity
@Table(name = "log_page_action")
public class LogPageAction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "log_id", columnDefinition = "INT")
    private Integer logId;
	@Column(name = "current_airport", columnDefinition = "CHAR", length = 3)
    private String currentAirport;
	@Column(name = "open_id")
    private String openId;
	@Column(name = "request_url")
    private String requestUrl;
	@Column(name = "module")
    private String module;
	@Column(name = "action")
    private String action;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_time", columnDefinition = "DATETIME")
    private Date requestTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_time", columnDefinition = "DATETIME")
    private Date receivedTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "response_time", columnDefinition = "DATETIME")
    private Date responseTime;
    @Column(name = "duration", columnDefinition = "INT")
    private Integer duration;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getCurrentAirport() {
        return currentAirport;
    }

    public void setCurrentAirport(String currentAirport) {
        this.currentAirport = currentAirport == null ? null : currentAirport.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}