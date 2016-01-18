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
 * 微信消息通知中心
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Entity
@Table(name = "msg_wechat_center")
public class MsgWechatCenter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "msg_id", columnDefinition = "INT")
    private Integer msgId;
	@Column(name = "current_airport", columnDefinition = "CHAR", length = 3)
    private String currentAirport;
	@Column(name = "source_code")
    private String sourceCode;
	@Column(name = "open_id")
    private String openId;
	@Column(name = "msg_module")
    private String msgModule;
	@Column(name = "msg_event")
    private String msgEvent;
	@Column(name = "msg_time")
    private Date msgTime;
	@Column(name = "msg_title")
    private String msgTitle;
	@Column(name = "msg_content")
    private String msgContent;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", columnDefinition = "DATETIME")
    private Date updateTime;
    @Column(name = "read_flag", columnDefinition = "CHAR", length = 1)
    private String readFlag;
    @Column(name = "delete_flag", columnDefinition = "CHAR", length = 1)
    private String deleteFlag;

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getMsgModule() {
        return msgModule;
    }

    public void setMsgModule(String msgModule) {
        this.msgModule = msgModule == null ? null : msgModule.trim();
    }

    public String getMsgEvent() {
        return msgEvent;
    }

    public void setMsgEvent(String msgEvent) {
        this.msgEvent = msgEvent == null ? null : msgEvent.trim();
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag == null ? null : readFlag.trim();
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }
}