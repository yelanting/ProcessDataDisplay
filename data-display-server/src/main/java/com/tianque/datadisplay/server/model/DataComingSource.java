/**
 * Project Name: datadisplay-server
 * File Name: DataComingSource.java
 * Package Name: com.tianque.datadisplay.server.model
 * Date: 2019年11月11日 下午9:16:02
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.model;

import com.tianque.datadisplay.server.model.base.BaseDomain;

/**
 * @author : 孙留平
 * @since : 2019年11月11日 下午9:16:02
 * @see : 数据来源信息
 */
public class DataComingSource extends BaseDomain {
	/**
	 * @Fields serialVersionUID : 序列号
	 */
	private static final long serialVersionUID = 8794767135127794486L;
	private String sourceName;
	private String executeSql;
	private String dataComingInfo;
	private Long databaseInfoId;

	/**
	 * 非数据库字段
	 */
	private DatabaseInfo databaseInfo;

	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * @param sourceName
	 *            the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return the executeSql
	 */
	public String getExecuteSql() {
		return executeSql;
	}

	/**
	 * @param executeSql
	 *            the executeSql to set
	 */
	public void setExecuteSql(String executeSql) {
		this.executeSql = executeSql;
	}

	/**
	 * @return the dataComingInfo
	 */
	public String getDataComingInfo() {
		return dataComingInfo;
	}

	/**
	 * @param dataComingInfo
	 *            the dataComingInfo to set
	 */
	public void setDataComingInfo(String dataComingInfo) {
		this.dataComingInfo = dataComingInfo;
	}

	/**
	 * @return the databaseInfoId
	 */
	public Long getDatabaseInfoId() {
		return databaseInfoId;
	}

	/**
	 * @param databaseInfoId
	 *            the databaseInfoId to set
	 */
	public void setDatabaseInfoId(Long databaseInfoId) {
		this.databaseInfoId = databaseInfoId;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * @return
	 */
	@Override
	public String toString() {
		return "DataComingSource [sourceName=" + sourceName + ", executeSql="
		        + executeSql + ", dataComingInfo=" + dataComingInfo
		        + ", databaseInfoId=" + databaseInfoId + "]";
	}

	/**
	 * @return the databaseInfo
	 */
	public DatabaseInfo getDatabaseInfo() {
		return databaseInfo;
	}

	/**
	 * @param databaseInfo
	 *            the databaseInfo to set
	 */
	public void setDatabaseInfo(DatabaseInfo databaseInfo) {
		this.databaseInfo = databaseInfo;
	}

}
