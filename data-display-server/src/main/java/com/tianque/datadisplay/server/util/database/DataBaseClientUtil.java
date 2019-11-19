/**
 * Project Name: datadisplay-server
 * File Name: DataBaseClientUtil.java
 * Package Name: com.tianque.datadisplay.server.util.database
 * Date: 2019年11月15日 下午2:10:07
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.util.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.commons.util.core.base.StringUtil;
import com.tianque.commons.util.exception.base.BusinessValidationException;
import com.tianque.datadisplay.server.enums.DatabaseTypeEnum;
import com.tianque.datadisplay.server.model.DatabaseInfo;
import com.tianque.datadisplay.server.model.DatabaseType;
import com.tianque.datadisplay.server.util.database.impl.MySqlOperationImpl;
import com.tianque.datadisplay.server.util.database.impl.OracleOperationImpl;

/**
 * @author : 孙留平
 * @since : 2019年11月15日 下午2:10:07
 * @see :
 */
public class DataBaseClientUtil {
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(DataBaseClientUtil.class);

	private DataBaseClientUtil() {
	}

	public static AbstractDatabaseOperation parseDatabaseOperation(
	        DatabaseInfo databaseInfo, DatabaseType databaseType) {
		LOGGER.info("根据数据库类型选择数据库客户端:{}:{}", databaseInfo, databaseType);
		if (null == databaseType
		        || StringUtil.isEmpty(databaseType.getTypeEname())) {
			LOGGER.error("数据库类型选择失败");
			return null;
		}

		if (null == databaseInfo) {
			LOGGER.error("数据库信息传入失败");
			return null;

		}

		String databaseTypeEname = databaseType.getTypeEname();

		AbstractDatabaseOperation abstractDatabaseOperation = null;

		if (DatabaseTypeEnum.ORACLE.toString()
		        .equalsIgnoreCase(databaseTypeEname)) {
			abstractDatabaseOperation = new OracleOperationImpl();
		} else if (DatabaseTypeEnum.MONGODB.toString()
		        .equalsIgnoreCase(databaseTypeEname)) {
			abstractDatabaseOperation = new OracleOperationImpl();
		} else if (DatabaseTypeEnum.MYSQL.toString()
		        .equalsIgnoreCase(databaseTypeEname)) {
			abstractDatabaseOperation = new MySqlOperationImpl();
		} else {
			throw new BusinessValidationException(
			        "暂不支持的数据库类型" + databaseTypeEname);
		}

		abstractDatabaseOperation.setDatabaseInfo(databaseInfo);

		return abstractDatabaseOperation;
	}
}
