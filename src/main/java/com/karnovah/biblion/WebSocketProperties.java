package com.karnovah.biblion;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "websocket")
public class WebSocketProperties {
	private String brokerPrefix;
	private String appPrefix;
	private String endpoint;
	private String[] allowedOrigins;

	public String getBrokerPrefix() {
		return this.brokerPrefix;
	}

	public void setBrokerPrefix(String brokerPrefix) {
		this.brokerPrefix = brokerPrefix;
	}

	public String getAppPrefix() {
		return this.appPrefix;
	}

	public void setAppPrefix(String appPrefix) {
		this.appPrefix = appPrefix;
	}

	public String getEndpoint() {
		return this.endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String[] getAllowedOrigins() {
		return this.allowedOrigins;
	}

	public void setAllowedOrigins(String[] allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}
}
