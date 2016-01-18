package com.rtmap.traffic.mfd.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订阅方式
 * 
 * @author liqingshan 2016-01-15
 *
 */
@Entity
@Table(name = "subscribe_mode")
public class SubscribeMode {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "subscribe_id", columnDefinition = "INT")
    private Integer subscribeId;
	@Column(name = "subscribe_platform")
    private String subscribePlatform;
	@Column(name = "send_mode")
    private String sendMode;
	@Column(name = "subscriber_mode")
    private String subscriberMode;

    public Integer getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(Integer subscribeId) {
        this.subscribeId = subscribeId;
    }

    public String getSubscribePlatform() {
        return subscribePlatform;
    }

    public void setSubscribePlatform(String subscribePlatform) {
        this.subscribePlatform = subscribePlatform == null ? null : subscribePlatform.trim();
    }

    public String getSendMode() {
        return sendMode;
    }

    public void setSendMode(String sendMode) {
        this.sendMode = sendMode == null ? null : sendMode.trim();
    }

    public String getSubscriberMode() {
        return subscriberMode;
    }

    public void setSubscriberMode(String subscriberMode) {
        this.subscriberMode = subscriberMode == null ? null : subscriberMode.trim();
    }
}