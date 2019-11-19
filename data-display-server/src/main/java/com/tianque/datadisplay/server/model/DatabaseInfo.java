/**
 * Project Name: datadisplay-server
 * File Name: DatabaseInfo.java
 * Package Name: com.tianque.datadisplay.server.model
 * Date: 2019年11月12日 下午12:11:28
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.model;

import com.tianque.datadisplay.server.model.base.BaseDomain;

/**
 * @author : 孙留平
 * @since : 2019年11月12日 下午12:11:28
 * @see : 数据库信息
 */
public class DatabaseInfo extends BaseDomain {
	private static final long serialVersionUID = -3797597230857295660L;
	private String databaseName;
	private String databaseIp;
	private String connectUrl;
	private Long databaseTypeId;
	private String instanceName;
	private Integer port;
	private String username;
	private String password;

	/**
	 * 非数据库字段
	 */
	private DatabaseType databaseType;

	/**
	 * @return the databaseTypeId
	 */
	public Long getDatabaseTypeId() {
		return databaseTypeId;
	}

	/**
	 * @param databaseTypeId
	 *            the databaseTypeId to set
	 */
	public void setDatabaseTypeId(Long databaseTypeId) {
		this.databaseTypeId = databaseTypeId;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName
	 *            the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the connectUrl
	 */
	public String getConnectUrl() {
		return connectUrl;
	}

	/**
	 * @param connectUrl
	 *            the connectUrl to set
	 */
	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}

	/**
	 * @return the databaseIp
	 */
	public String getDatabaseIp() {
		return databaseIp;
	}

	/**
	 * @param databaseIp
	 *            the databaseIp to set
	 */
	public void setDatabaseIp(String databaseIp) {
		this.databaseIp = databaseIp;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the instanceName
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * @param instanceName
	 *            the instanceName to set
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "DatabaseInfo [databaseName=" + databaseName + ", databaseIp="
		        + databaseIp + ", connectUrl=" + connectUrl
		        + ", databaseTypeId=" + databaseTypeId + ", instanceName="
		        + instanceName + ", port=" + port + ", username=" + username
		        + ", password=" + password + "]";
	}

	/**
	 * @return the databaseType
	 */
	public DatabaseType getDatabaseType() {
		return databaseType;
	}

	/**
	 * @param databaseType
	 *            the databaseType to set
	 */
	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}

}
