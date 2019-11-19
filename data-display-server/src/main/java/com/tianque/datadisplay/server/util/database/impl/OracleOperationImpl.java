/**
 * Project Name: datadisplay-server
 * File Name: OracleOperationImpl.java
 * Package Name: com.tianque.datadisplay.server.util.database.impl
 * Date: 2019年11月13日 下午1:53:42
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.util.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.commons.util.exception.base.BusinessValidationException;
import com.tianque.datadisplay.server.model.DatabaseInfo;
import com.tianque.datadisplay.server.util.database.AbstractDatabaseOperation;
import com.tianque.datadisplay.server.util.database.intf.DatabaseOperationIntf;

/**
 * @author : 孙留平
 * @since : 2019年11月13日 下午1:53:42
 * @see :
 */
public class OracleOperationImpl extends AbstractDatabaseOperation
        implements DatabaseOperationIntf {
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(OracleOperationImpl.class);

	@Override
	public Connection getConnection() {
		DatabaseInfo databaseInfo = this.getDatabaseInfo();
		String url = String.format("jdbc:oracle:thin:@//%s:%d/%s",
		        databaseInfo.getDatabaseIp(), databaseInfo.getPort(),
		        databaseInfo.getInstanceName());

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			LOGGER.error("加载Oracle驱动失败,失败原因:{}", e.getMessage());
			throw new BusinessValidationException("创建数据库连接失败");
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,
			        databaseInfo.getUsername(), databaseInfo.getPassword());
		} catch (SQLException e) {
			LOGGER.error("获取Oracle连接失败,失败原因:{}", e.getMessage());
			throw new BusinessValidationException("获取Oracle的连接失败");
		}

		this.setConnection(connection);
		return connection;
	}

	public static void main(String[] args) {
		OracleOperationImpl oracleOperationImpl = new OracleOperationImpl();

		DatabaseInfo databaseInfo = new DatabaseInfo();
		databaseInfo.setDatabaseIp("192.168.110.233");
		databaseInfo.setPort(1521);
		databaseInfo.setDatabaseName("测试本地数据库");
		databaseInfo.setInstanceName("tianque");
		databaseInfo.setUsername("zhejianggrid5");
		databaseInfo.setPassword("zhejianggrid");
		oracleOperationImpl.setDatabaseInfo(databaseInfo);
		oracleOperationImpl.executeQuery("select * from issue_test");
	}
}
