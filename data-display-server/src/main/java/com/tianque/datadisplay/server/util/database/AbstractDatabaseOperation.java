/**
 * Project Name: datadisplay-server
 * File Name: DatabaseOperationBase.java
 * Package Name: com.tianque.datadisplay.server.util.database
 * Date: 2019年11月13日 下午2:04:33
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.util.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.commons.util.exception.base.BusinessValidationException;
import com.tianque.datadisplay.server.model.DatabaseInfo;

/**
 * @author : 孙留平
 * @since : 2019年11月13日 下午2:04:33
 * @see :
 */
public abstract class AbstractDatabaseOperation {
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(AbstractDatabaseOperation.class);
	private DatabaseInfo databaseInfo;
	private Connection connection;
	private PreparedStatement preparedStatement;

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

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the preparedStatement
	 */
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	/**
	 * @param preparedStatement
	 *            the preparedStatement to set
	 */
	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public void close() {
		if (null != this.getConnection()) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != this.getPreparedStatement()) {
			try {
				this.preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void execute(String sql) {
		try {
			this.setPreparedStatement(
			        this.getConnection().prepareStatement(sql));
			int result = this.getPreparedStatement().executeUpdate();
			LOGGER.info("执行sql[{}]成功，影响:{}行", sql, result);
		} catch (SQLException e) {
			LOGGER.error("执行sql:{}失败，失败原因:{}", sql, e.getMessage());
			throw new BusinessValidationException("执行sql失败");
		} finally {
			this.close();
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			this.setPreparedStatement(
			        this.getConnection().prepareStatement(sql));
			ResultSet resultSet = this.getPreparedStatement().executeQuery();

			int count = 0;
			while (resultSet.next()) {
				LOGGER.debug("数据:{}", resultSet);
				count++;
			}

			LOGGER.info("一共查询出:{}条数据", count);
			return resultSet;
		} catch (SQLException e) {
			LOGGER.error("查询sql:{}失败:{}", sql, e.getMessage());
			throw new BusinessValidationException("执行sql失败");
		} finally {
			this.close();
		}
	}

	public List<HashMap<String, Object>> executeQueryWithJsonObjectResult(
	        String sql) {
		try {
			this.setPreparedStatement(
			        this.getConnection().prepareStatement(sql));
			ResultSet resultSet = this.getPreparedStatement().executeQuery();

			List<HashMap<String, Object>> executeResultList = new ArrayList<>();

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCounts = resultSetMetaData.getColumnCount();
			LOGGER.info("一共：{}列", columnCounts);
			try {
				int index = 0;
				while (resultSet.next()) {
					HashMap<String, Object> eachDetail = new HashMap<>();
					for (int i = 1; i <= columnCounts; i++) {
						eachDetail.put(resultSetMetaData.getColumnName(i),
						        resultSet.getObject(i));
					}
					executeResultList.add(eachDetail);
				}

				LOGGER.debug("得到的查询结果为：{}", executeResultList);
				return executeResultList;
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error("执行sql失败,失败原因:{}", e.getMessage());
				throw new BusinessValidationException("执行sql失败，出异常");
			}
		} catch (SQLException e) {
			LOGGER.error("查询sql:{}失败:{}", sql, e.getMessage());
			throw new BusinessValidationException("执行sql失败");
		} finally {
			this.close();
		}
	}
}
