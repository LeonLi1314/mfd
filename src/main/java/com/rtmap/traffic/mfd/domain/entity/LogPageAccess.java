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
 * 页面访问日志
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Entity
@Table(name = "log_page_access")
public class LogPageAccess {
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "access_time", columnDefinition = "DATETIME")
    private Date accessTime;
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

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}