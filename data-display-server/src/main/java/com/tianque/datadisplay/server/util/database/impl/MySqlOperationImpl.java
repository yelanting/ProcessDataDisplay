/**
 * Project Name: datadisplay-server
 * File Name: MySqlOperationImpl.java
 * Package Name: com.tianque.datadisplay.server.util.database.impl
 * Date: 2019年11月13日 下午1:53:19
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
 * @since : 2019年11月13日 下午1:53:19
 * @see :
 */
public class MySqlOperationImpl extends AbstractDatabaseOperation
        implements DatabaseOperationIntf {
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(MySqlOperationImpl.class);

	@Override
	public Connection getConnection() {
		DatabaseInfo databaseInfo = this.getDatabaseInfo();
		String url = String.format(
		        "jdbc:mysql://%s:%d/%s?useSSL=false&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true",
		        databaseInfo.getDatabaseIp(), databaseInfo.getPort(),
		        databaseInfo.getInstanceName());

		databaseInfo.setConnectUrl(url);
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			LOGGER.error("加载MYsql驱动失败,失败原因:{}", e.getMessage());
			throw new BusinessValidationException("创建数据库连接失败");
		}

		LOGGER.info("连接数据库的信息为:{}", databaseInfo);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url,
			        databaseInfo.getUsername(), databaseInfo.getPassword());
		} catch (SQLException e) {
			LOGGER.error("获取MYSQL连接失败,失败原因:{}", e.getMessage());
			throw new BusinessValidationException("获取mysql的连接失败");
		}

		this.setConnection(connection);
		return connection;
	}

	public static void main(String[] args) {
		MySqlOperationImpl mySqlOperationImpl = new MySqlOperationImpl();

		DatabaseInfo databaseInfo = new DatabaseInfo();
		databaseInfo.setDatabaseIp("localhost");
		databaseInfo.setPort(3306);
		databaseInfo.setDatabaseName("测试本地数据库");
		databaseInfo.setInstanceName("autotest_platform_administrator");
		databaseInfo.setUsername("root");
		databaseInfo.setPassword("Admin@1234");
		mySqlOperationImpl.setDatabaseInfo(databaseInfo);
		mySqlOperationImpl
		        .executeQueryWithJsonObjectResult("select * from sys_user;");

	}
}
