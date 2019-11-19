/**
 * Project Name: datadisplay-server
 * File Name: DatabaseOperation.java
 * Package Name: com.tianque.datadisplay.server.util.database
 * Date: 2019年11月13日 下午1:52:12
 * Copyright (c) 2019, qing121171@gmail.com All Rights Reserved.
 */
package com.tianque.datadisplay.server.util.database.intf;

import java.sql.Connection;

/**
 * @author : 孙留平
 * @since : 2019年11月13日 下午1:52:12
 * @see : 数据库连接接口
 */
public interface DatabaseOperationIntf {
	Connection getConnection();
}
